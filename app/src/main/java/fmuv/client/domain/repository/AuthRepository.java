package fmuv.client.domain.repository;

import fmuv.client.domain.model.UserModel;
import io.reactivex.rxjava3.core.Observable;

/**
 * @author Junrey Algario algario.devs@gmail.com 2020.8.8
 */


public interface AuthRepository {

    Observable<Integer> login(String username, String password);

    Observable<Integer> register(UserModel userModel);

    Observable<Integer> verifyEmail();

    Observable<Integer> forgotPassword();

}
