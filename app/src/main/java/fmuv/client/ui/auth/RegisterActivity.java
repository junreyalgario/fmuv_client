package fmuv.client.ui.auth;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import fmuv.client.BR;
import fmuv.client.R;
import fmuv.client.databinding.ActivityRegisterBinding;
import fmuv.client.ui.base.BaseActivity;
import fmuv.client.ui.base.ValidationListener;
import fmuv.client.ui.util.SpinnerUtil;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Junrey Algario algario.devs@gmail.com 2020.7.1
 */

public class RegisterActivity extends BaseActivity<RegisterViewModel>
    implements ValidationListener {

    private ActivityRegisterBinding dataBinding;
    private RegistrationForm registrationForm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        registrationForm = new RegistrationForm(this);
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_register);
        dataBinding.setLifecycleOwner(this);
        dataBinding.setRegistrationForm(registrationForm);

        attachToolbar("Create Account");
        setGenderSpinner();
        attachResponseStatusObserver();
    }

    @Override
    public RegisterViewModel createViewModel() {
        return ViewModelProviders.of(this).get(RegisterViewModel.class);
    }

    private void setGenderSpinner() {
        List<String> gender = new ArrayList<>();
        gender.add("Male");
        gender.add("Female");

        SpinnerUtil.Builder(this, dataBinding.genderSpinner)
                .setList(gender)
                .setHint("Select Gender")
                .setItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        registrationForm.setGender(parent.getItemAtPosition(position).toString());
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                }).build();
    }

    public void onClickRegister(View view) {
        if (!registrationForm.hasEmptyField()) {
            viewUtil.btnLoading(dataBinding.btnRegister, "Saving ...", View.VISIBLE);
            viewModel.register(registrationForm);
        }
    }

    @Override
    public void onValidationError(int propertyId) {
        if (propertyId == BR.email) {
            dataBinding.email.setError("Invalid email.");
        } else if (propertyId == BR.contact) {
            dataBinding.contact.setError("Invalid contact number.");
        } else if (propertyId == BR.password) {
            dataBinding.password.setError("Password must be 8 characters long.");
        } else if (propertyId == BR.cpassword) {
            dataBinding.confirmPassword.setError("Confirm password does not match password.");
        }
    }

    @Override
    public void onEmpty(int propertyId) {
        if (propertyId == BR.fname) {
            dataBinding.fName.setError("First name is required.");
        }
        if (propertyId == BR.lname) {
            dataBinding.lName.setError("Last name is required.");
        }
        if (propertyId == BR.email) {
            dataBinding.email.setError("Email is required.");
        }
        if (propertyId == BR.contact) {
            dataBinding.contact.setError("Contact number is required.");
        }
        if (propertyId == BR.password) {
            dataBinding.password.setError("Password is required");
        }
        if (propertyId == BR.cpassword) {
            dataBinding.confirmPassword.setError("Please confirm password.");
        }
    }

    // ResponseModel observer

    @Override
    public void onResponseOk() {
        viewUtil.showMessage(
                "Registration Success",
                "Thank you for registering for Find me UV. Use your account's email and password to login.",
                (dialog, which) -> onBackPressed()
        );
    }

    @Override
    public void onForbidden(Throwable e) {
        viewUtil.showMessage(
                "Registration Failed",
                "Email is already exists.",
                null
        );
    }

    @Override
    protected void doCommonOnRequestDone() {
        viewUtil.btnLoading(dataBinding.btnRegister, "Register", View.GONE);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.right_enter, R.anim.right_leave);
    }

}
