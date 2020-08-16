package fmuv.client.ui.auth;

import android.util.Patterns;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import fmuv.client.BR;
import fmuv.client.ui.base.ValidationListener;

public class RegistrationForm extends BaseObservable {

    public String fname = "Junrey";
    public String lname = "Algario";
    public String gender = "";
    public String contact = "09955426109";
    public String email = "junrey.algario@gmail.com";
    public String password = "12345678";
    public String cpassword = "12345678";
    private ValidationListener validation;

    public RegistrationForm(ValidationListener validation) {
        this.validation = validation;
    }

    // Setters

    public void setFname(String fname) {
        this.fname = fname;
        notifyPropertyChanged(BR.fname);
    }

    public void setLname(String lname) {
        this.lname = lname;
        notifyPropertyChanged(BR.lname);
    }

    public void setGender(String gender) {
        this.gender = gender;
        notifyPropertyChanged(BR.gender);
    }

    public void setContact(String contact) {
        this.contact = contact;
        notifyPropertyChanged(BR.contact);

        if (!Patterns.PHONE.matcher(contact).matches()) {
            validation.onValidationError(BR.contact);
        }
    }

    public void setEmail(String email) {
        this.email = email;
        notifyPropertyChanged(BR.email);

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            validation.onValidationError(BR.email);
        }
    }

    public void setPassword(String password) {
        this.password = password;
        notifyPropertyChanged(BR.password);

        if (password.length() < 8) {
            validation.onValidationError(BR.password);
        }
    }

    public void setCpassword(String cpassword) {
        this.cpassword = cpassword;
        notifyPropertyChanged(BR.cpassword);

        if (!cpassword.equals(password)) {
            validation.onValidationError(BR.cpassword);
        }
    }

    // Getters
    @Bindable
    public String getFname() {
        return fname;
    }

    @Bindable
    public String getLname() {
        return lname;
    }

    @Bindable
    public String getGender() {
        return gender;
    }

    @Bindable
    public String getContact() {
        return contact;
    }

    @Bindable
    public String getEmail() {
        return email;
    }

    @Bindable
    public String getPassword() {
        return password;
    }

    @Bindable
    public String getCpassword() {
        return cpassword;
    }

    public boolean hasEmptyField() {
        boolean hasEmpty = false;

        if (fname.isEmpty()) {
            validation.onEmpty(BR.fname);
            hasEmpty = true;
        }
        if (lname.isEmpty()) {
            validation.onEmpty(BR.lname);
            hasEmpty = true;
        }
        if (gender.isEmpty()) {
            validation.onEmpty(BR.gender);
            hasEmpty = true;
        }
        if (contact.isEmpty()) {
            validation.onEmpty(BR.contact);
            hasEmpty = true;
        }
        if (email.isEmpty()) {
            validation.onEmpty(BR.email);
            hasEmpty = true;
        }
        if (password.isEmpty()) {
            validation.onEmpty(BR.password);
            hasEmpty = true;
        }
        if (cpassword.isEmpty()) {
            validation.onEmpty(BR.cpassword);
            hasEmpty = true;
        }

        return hasEmpty;
    }
}
