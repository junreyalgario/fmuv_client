package fmuv.client.ui.mapper;

import fmuv.client.domain.model.UserModel;
import fmuv.client.ui.auth.RegistrationForm;

public class UserModelMapper {

    public UserModel transform(RegistrationForm registrationForm) {
        UserModel userModel = null;
        if (registrationForm != null) {
            userModel = new UserModel();
            userModel.setFName(registrationForm.fname);
            userModel.setLName(registrationForm.lname);
            userModel.setGender(registrationForm.gender);
            userModel.setEmail(registrationForm.email);
            userModel.setContact(registrationForm.contact);
            userModel.setPassword(registrationForm.password);
        }

        return userModel;
    }

}
