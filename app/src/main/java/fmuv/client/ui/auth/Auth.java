package fmuv.client.ui.auth;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import fmuv.client.BR;

/**
 * @author Junrey Algario algario.devs@gmail.com 2020.8.3
 */

public class Auth extends BaseObservable {

    public String username = "joyce@gmail.com";
    public String password = "12345678";
    public boolean errAuth = false;
    public boolean errUsername = false;
    public boolean enabledLogin = false;

    @Bindable
    public String getUsername() {
        return username;
    }

    @Bindable
    public String getPassword() {
        return password;
    }


    @Bindable
    public boolean isErrUsername() {
        return errUsername;
    }

    @Bindable
    public boolean isErrAuth() {
        return errAuth;
    }

    @Bindable
    public boolean isEnabledLogin() {
        return enabledLogin;
    }

    public void setUsername(String username) {
        this.username = username;
        notifyPropertyChanged(BR.username);
        setErrUsername(!android.util.Patterns.EMAIL_ADDRESS.matcher(username).matches());
        setErrAuthToFalse();
    }

    public void setPassword(String password) {
        this.password = password;
        notifyPropertyChanged(BR.password);
        setErrAuthToFalse();
    }


    public void setErrUsername(boolean errUsername) {
        this.errUsername = errUsername;
        notifyPropertyChanged(BR.errUsername);
    }

    public void setErrAuth(boolean errAuth) {
        this.errAuth = errAuth;
        notifyPropertyChanged(BR.errAuth);
        setEnabledLogin(!errAuth && !errUsername);
    }

    public void setEnabledLogin(boolean enabledLogin) {
        this.enabledLogin = enabledLogin;
        notifyPropertyChanged(BR.enabledLogin);
    }

    private void setErrAuthToFalse() {
        if (errAuth) {
            setErrAuth(false);
        }
        setEnabledLogin(!errAuth && !errUsername);
    }

}
