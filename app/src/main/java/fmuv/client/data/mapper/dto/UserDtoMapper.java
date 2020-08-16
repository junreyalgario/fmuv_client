package fmuv.client.data.mapper.dto;

import java.util.ArrayList;
import java.util.List;

import fmuv.client.data.mapper.ObjectMapper;
import fmuv.client.data.remote.dto.User;
import fmuv.client.domain.model.UserModel;

public class UserDtoMapper extends ObjectMapper<UserModel, User> {

    @Override
    public UserModel transform(User user) {
        UserModel userModel = null;
        if (user != null) {
            userModel = new UserModel(
                    user.getPassengerId(),
                    user.getFName(),
                    user.getLName(),
                    user.getGender(),
                    user.getContact(),
                    user.getEmail()
            );
        }

        return userModel;
    }

    @Override
    public List<UserModel> transformList(List<User> list) {
        List<UserModel> userModels = new ArrayList<>();
        for(User user: list) {
            if (user != null) {
                userModels.add(transform(user));
            }
        }
        return userModels;
    }
}
