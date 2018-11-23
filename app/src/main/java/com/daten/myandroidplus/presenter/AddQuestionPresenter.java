package com.daten.myandroidplus.presenter;

import com.daten.myandroidplus.contract.AddQuestionContract;
import com.daten.myandroidplus.data.QuestionDataRepository;
import com.daten.myandroidplus.data.QuestionDataSource;
import com.daten.myandroidplus.data.SaveCallback;
import com.daten.myandroidplus.di.ActivityScoped;

import javax.inject.Inject;

@ActivityScoped
public class AddQuestionPresenter implements AddQuestionContract.Presenter {
    private AddQuestionContract.View mView;
    private QuestionDataSource mQuestionDataSource;

    @Inject
    public AddQuestionPresenter(QuestionDataSource questionDataSource) {
        mQuestionDataSource = questionDataSource;
    }
    @Override
    public void publishQuestion(String title, String des) {
        mQuestionDataSource.addQuestion(title, des, new SaveCallback() {
            @Override
            public void onSaveSuccess() {
                mView.onPublishSuccess();
            }

            @Override
            public void onSaveFailed(String errorMsg) {
                mView.onPublishFailed();
            }
        });
    }

    @Override
    public void takeView(AddQuestionContract.View view) {
        mView = view;
    }

    @Override
    public void dropView() {
        mView = null;
    }
}
