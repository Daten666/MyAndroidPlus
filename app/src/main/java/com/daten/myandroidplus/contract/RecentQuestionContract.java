package com.daten.myandroidplus.contract;

import com.daten.myandroidplus.data.model.Question;

import java.util.List;

public interface RecentQuestionContract {

    interface View extends BaseView {
        void onLoadRecentQuestionSuccess(List<Question> list);
        void onLoadRecentQuestionFailed();

        void onLoadMoreRecentQuestionSuccess();
        void onLoadMOreRecentQuestionFailed();
    }

    interface Presenter extends BasePresenter<View> {
        void loadRecentQuestions();
        void loadMoreRecentQuestions();
    }
}
