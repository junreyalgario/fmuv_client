package fmuv.client.domain.repository;

import fmuv.client.domain.model.UserModel;
import io.reactivex.rxjava3.core.Observable;

/**
 * @author Junrey Algario algario.devs@gmail.com 2020.8.8
 */


public interface SessionRepository {

    Boolean setCookie(String cookie);

    String getCookie();

    Boolean setToken(String token);

    String getToken();

    Boolean setUser(UserModel user);

    UserModel getUser();

    Boolean setData(String key, String value);

    Boolean clearData();

    Boolean clearAuthSession();

    Boolean setLogoutStatus(boolean status);

    Boolean getLogoutStatus(boolean status);

    Observable<Integer> logout();

}
