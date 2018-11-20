package com.daten.myandroidplus.presenter;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.SignUpCallback;
import com.daten.myandroidplus.contract.RegisterContract;
import com.daten.myandroidplus.data.model.User;
import com.daten.myandroidplus.di.ActivityScoped;

import javax.inject.Inject;

@ActivityScoped
public class RegisterPresenter implements RegisterContract.Presenter {

    private RegisterContract.View mView;

    @Inject
    public RegisterPresenter() {}

    @Override
    public void register(String userName, String password) {
        final User user = new User();
        user.setUsername(userName);
        user.setPassword(password);
        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(AVException e) {
                //通知View层更新UI
            if (e == null) {
                mView.onRegisterSuccess();
            } else {
                if (e.getCode() == AVException.USERNAME_TAKEN) {
                    mView.onUserNameTaken();
                } else {
                    mView.onRegisterFailed();
                }
            }
            }
        });
    }

    @Override
    public void takeView(RegisterContract.View view) {
        mView = view;
    }

    @Override
    public void dropView() {
        mView = null;
    }
}
