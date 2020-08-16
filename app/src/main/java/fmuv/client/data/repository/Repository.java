package fmuv.client.data.repository;

import fmuv.client.App;
import fmuv.client.data.mapper.DTOModel;
import fmuv.client.data.remote.retrofit.RetrofitService;
import fmuv.client.domain.repository.AuthRepository;
import fmuv.client.domain.repository.SessionRepository;

/**
 * @author Junrey Algario algario.devs@gmail.com 2020.8.3
 */

public class Repository {

    private static Repository instance;

    private Repository(){
        // Not publicly instantiable
    }

    public static Repository getInstance() {
        if (instance == null) {
            instance = new Repository();
        }
        return instance;
    }

    public static SessionRepository session() {
        return new SessionRepositoryImpl(App.getInstance(), RetrofitService.create());
    }

    public static AuthRepository auth() {
        return new AuthRepositoryImpl(
                RetrofitService.create(),
                Repository.session(),
                new DTOModel()
        );
    }

}
