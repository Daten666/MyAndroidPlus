package com.daten.myandroidplus.presenter;

import com.daten.myandroidplus.contract.RecentQuestionContract;
import com.daten.myandroidplus.data.LoadCallback;
import com.daten.myandroidplus.data.QuestionDataSource;
import com.daten.myandroidplus.data.model.Question;
import com.daten.myandroidplus.di.ChildFragmentScope;

import java.util.List;

import javax.inject.Inject;

@ChildFragmentScope
public class RecentQuestionPresenter implements RecentQuestionContract.Presenter {

    private static final String TAG = "RecentQuestionPresenter";
    private RecentQuestionContract.View mView;
    private QuestionDataSource mDataRepository;

    @Inject
    public RecentQuestionPresenter(QuestionDataSource dataRepository) {
        mDataRepository = dataRepository;
    }

    @Override
    public void loadRecentQuestions() {
        mDataRepository.getRecentQuestionList(new LoadCallback<Question>() {
            @Override
            public void onLoadSuccess(List<Question> list) {
                if (mView != null) {
                    mView.onLoadRecentQuestionSuccess(list);
                }
            }

            @Override
            public void onLoadFailed(String errorMsg) {
                if (mView != null) {
                    mView.onLoadRecentQuestionFailed();
                }
            }
        });
    }

    @Override
    public void loadMoreRecentQuestions() {

    }

    @Override
    public void takeView(RecentQuestionContract.View view) {
        mView = view;
    }

    @Override
    public void dropView() {
        mView = null;
    }
}
