package fmuv.client.view_model;

import android.util.Log;
import android.view.View;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import fmuv.client.BR;

public class LoginViewModel extends BaseObservable {

    private String username = "joyce@gmail.com";
    private String password = "12345678";
    private boolean status = false;

    @Bindable
    public String getUsername() {
        return username;
    }

    @Bindable
    public String getPassword() {
        return password;
    }

    @Bindable
    public boolean isStatus() {
        return status;
    }

    public void setUsername(String username) {
        if (!this.username.equals(username)) {
            this.username = username;
            notifyPropertyChanged(BR.username);
        }
    }

    public void setPassword(String password) {
        if (!this.password.equals(password)) {
            this.password = password;
            notifyPropertyChanged(BR.password);
        }
    }

    public void setStatus(boolean status) {
        if (this.status != status) {
            this.status = status;
            notifyPropertyChanged(BR.status);
        }
    }

}
