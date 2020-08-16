package fmuv.client.ui.base;

import retrofit2.HttpException;

public class HttpResponse {

     private OnHttpSuccessListener httpSuccessListener;
     private OnHttpErrorListener httpErrorListener;

    public void setHttpSuccessListener(OnHttpSuccessListener httpSuccessListener) {
        this.httpSuccessListener = httpSuccessListener;
    }

    public void setHttpErrorListener(OnHttpErrorListener httpErrorListener) {
        this.httpErrorListener = httpErrorListener;
    }

    public void onSuccess(int code) {
        if (httpSuccessListener != null) {
            if (code == 201) {
                httpSuccessListener.onCreated();
            }else{
                httpSuccessListener.onResponseOk();
            }
        }
    }

    public void onError(Throwable e) {
        if (e instanceof HttpException && httpErrorListener != null) {

            HttpException httpException = (HttpException)e;

            if (httpException.code() == 403) {
                httpErrorListener.onForbidden(e);
            }else if (httpException.code() == 404) {
                httpErrorListener.onNotFound(e);
            }else if (httpException.code() == 409) {
                httpErrorListener.onConflict(e);
            }else if (httpException.code() == 500) {
                httpErrorListener.onServerError(e);
            }else if (httpException.code() == 503) {
                httpErrorListener.onServerUnavailable(e);
            }else{
                httpErrorListener.onFailed(e);
            }
        }
    }

    interface OnHttpErrorListener {

        void onForbidden(Throwable e);

        void onNotFound(Throwable e);

        void onConflict(Throwable e);

        void onServerError(Throwable e);

        void onServerUnavailable(Throwable e);

        void onFailed(Throwable e);

    }

    interface OnHttpSuccessListener {

        void onResponseOk();

        void onCreated();

    }

}
