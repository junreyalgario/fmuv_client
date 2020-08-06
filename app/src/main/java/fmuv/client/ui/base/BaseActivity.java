package fmuv.client.ui.base;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import fmuv.client.R;
import fmuv.client.utils.ViewUtil;

/**
 * @author Junrey Algario algario.devs@gmail.com 2020.8.1
 */

public abstract class BaseActivity<ViewModel extends BaseViewModel> extends AppCompatActivity {

    protected ViewModel viewModel;

    /**
     * Keyboard popup listener properties
     */
    private View.OnLayoutChangeListener rootLayoutChangeListener;
    private int rootHeight = 0;
    private boolean rootLayoutChangeListenerAttached = false;
    private ViewGroup rootLayout;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = createViewModel();
        baseViewModelObserver();
    }

    protected abstract ViewModel createViewModel();

    protected void initializeBaseActivity() {
        rootLayout = findViewById(R.id.rootLayout);
    }


    /**
     * Keyboard popup listener methods
     */

    protected void onShowKeyboard() {}
    protected void onHideKeyboard() {}

    protected void attachKeyboardListeners() {

        if (!rootLayoutChangeListenerAttached) {
            rootLayoutChangeListener = new View.OnLayoutChangeListener() {
                @Override
                public void onLayoutChange(View rootView, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                    if (rootHeight < rootLayout.getHeight()) {
                        rootHeight = rootLayout.getHeight();
                    }else {
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

    /**
     * Base view model observers for common live data
     */

    private void baseViewModelObserver(){

        // Request success
        viewModel.onResponseReady().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer responseCode) {
                doCommonOnRequestDone();
                if (responseCode == 200) {
                    onResponseOk();
                }else if(responseCode == 403) {
                    onForbidden();
                }else if(responseCode == 404) {
                    onNotFound();
                }else if(responseCode == 409) {
                    onConflict();
                }else if(responseCode == 500) {
                    onServerError();
                }else if(responseCode == 503) {
                    onServerUnavailable();
                }
            }
        });

        // Request failed
        viewModel.onRequestFailed().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean failed) {
                doCommonOnRequestDone();
                onRequestFailed();
            }
        });
    }

    // Response success
    protected void onResponseOk() {}

    // The client did not have permission to access the requested resource.
    protected void onForbidden() {}

    // Conflict/Duplication of data
    protected void onConflict() {}

    // Server error
    protected void onServerError() {
        ViewUtil.showMessage(this, "System Message", "500 Internal Server Error Oh no! Something bad happened. Please come back later when we fixed that problem. Thanks.");
    }

    // The server was unavailable.
    protected void onServerUnavailable() {
        ViewUtil.showMessage(this, "System Message", "Service is not available at the moment. Try again later. Thanks.");
    }

    // Resource not found
    protected void onNotFound() {}

    // Http client request failed
    protected void onRequestFailed() {
        ViewUtil.showMessage(this, "System Message", "Something went wrong there. Try again.");
    }

    // Common things to do for every kind of response
    protected void doCommonOnRequestDone() {}


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (rootLayoutChangeListenerAttached) {
            rootLayout.removeOnLayoutChangeListener(rootLayoutChangeListener);
        }
    }

}
