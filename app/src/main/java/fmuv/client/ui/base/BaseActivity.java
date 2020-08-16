package fmuv.client.ui.base;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import fmuv.client.R;
import fmuv.client.ui.util.ViewUtil;

/**
 * @author Junrey Algario algario.devs@gmail.com 2020.8.1
 */

public abstract class BaseActivity<ViewModel extends BaseViewModel> extends AppCompatActivity
    implements HttpResponse.OnHttpErrorListener, HttpResponse.OnHttpSuccessListener {

    protected ViewModel viewModel;

    // Keyboard popup listener properties
    private View.OnLayoutChangeListener rootLayoutChangeListener;
    private int rootHeight = 0;
    private boolean rootLayoutChangeListenerAttached = false;
    private ViewGroup rootLayout;

    protected Toolbar toolbar;
    protected ViewUtil viewUtil;
    private HttpResponse httpResponse;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = createViewModel();
        viewUtil = new ViewUtil(this);

        if (viewModel != null) {
            httpResponse = new HttpResponse();
            httpResponse.setHttpSuccessListener(this);
            httpResponse.setHttpErrorListener(this);
        }
    }

    protected abstract ViewModel createViewModel();

    protected void initializeBaseActivity() {
        rootLayout = findViewById(R.id.rootLayout);
    }


    // Keyboard popup listener methods
    protected void onShowKeyboard() {}
    protected void onHideKeyboard() {}

    protected void attachToolbar(String title) {
        toolbar = (Toolbar) findViewById(R.id.app_toolbar);
        setSupportActionBar(toolbar);

        ((TextView)findViewById(R.id.toolbar_title)).setText(title);
        ((ImageButton)findViewById(R.id.toolbar_back_btn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    protected void attachKeyboardListeners() {

        if (!rootLayoutChangeListenerAttached) {
            rootLayoutChangeListener = new View.OnLayoutChangeListener() {
                @Override
                public void onLayoutChange(View rootView, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                    if (rootHeight < rootLayout.getHeight()) {
                        rootHeight = rootLayout.getHeight();
                    }else{
                        if (rootHeight > rootLayout.getHeight()) {
                            onShowKeyboard();
                        }else{
                            onHideKeyboard();
                        }
                    }
                }
            };
            rootLayout.addOnLayoutChangeListener(rootLayoutChangeListener);
            rootLayoutChangeListenerAttached = true;
        }
    }

    // Base view model observers for common live data
    protected void attachResponseStatusObserver(){

        // Request success
        viewModel.onResponseReady().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer responseCode) {
                httpResponse.onSuccess(responseCode);
                doCommonOnRequestDone();
            }
        });

        // Request failed
        viewModel.onRequestFailed().observe(this, new Observer<Throwable>() {
            @Override
            public void onChanged(Throwable throwable) {
                httpResponse.onError(throwable);
                doCommonOnRequestDone();
            }
        });
    }


    // Common things to do for every kind of response
    protected void doCommonOnRequestDone() {}

    // Http success listener

    @Override
    public void onResponseOk() {

    }

    @Override
    public void onCreated() {

    }

    // Http error listener

    @Override
    public void onForbidden(Throwable e) {

    }

    @Override
    public void onNotFound(Throwable e) {

    }

    @Override
    public void onConflict(Throwable e) {

    }

    @Override
    public void onServerError(Throwable e) {
        viewUtil.showMessage(
                "System Message",
                "500 Internal Server Error Oh no! Something bad happened. Please come back later when we fixed that problem. Thanks.",
                null
        );
    }

    @Override
    public void onServerUnavailable(Throwable e) {
        viewUtil.showMessage(
                "System Message",
                "Service is not available at the moment. Try again later. Thanks.",
                null
        );
    }

    @Override
    public void onFailed(Throwable e) {
        viewUtil.showMessage(
                "System Message",
                "Something went wrong there. Try again.",
                null
        );
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (rootLayoutChangeListenerAttached) {
            rootLayout.removeOnLayoutChangeListener(rootLayoutChangeListener);
        }
    }

}
