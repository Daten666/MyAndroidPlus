package com.daten.myandroidplus.ui.fragment;

import android.view.View;

import com.daten.myandroidplus.adapter.BaseLoadingListAdapter;
import com.daten.myandroidplus.adapter.QuestionListAdapter;
import com.daten.myandroidplus.contract.RecentQuestionContract;
import com.daten.myandroidplus.data.model.Question;
import com.daten.myandroidplus.presenter.RecentQuestionPresenter;

import java.util.List;

import javax.inject.Inject;

public class QuestionFragment extends BaseRefreshableListFragment<Question> implements RecentQuestionContract.View {

    private static final String TAG = "QuestionFragment";

    @Inject
    RecentQuestionPresenter mQuestionPresenter;
    @Override
    protected BaseLoadingListAdapter<Question> onCreateAdapter() {
        return new QuestionListAdapter(getContext());
    }

    @Override
    protected void init() {
        super.init();
        mQuestionPresenter.takeView(this);
        mQuestionPresenter.loadRecentQuestions();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mQuestionPresenter.dropView();
    }

    @Override
    protected void startRefresh() {
        mQuestionPresenter.loadRecentQuestions();
    }

    @Override
    protected void startLoadMoreData() {
        mQuestionPresenter.loadMoreRecentQuestions();
    }

    @Override
    public void onLoadRecentQuestionSuccess(List<Question> list) {
        mError.setVisibility(View.GONE);
        mSwipeRefreshLayout.setRefreshing(false);
        getAdapter().replaceData(list);
    }

    @Override
    public void onLoadRecentQuestionFailed() {
        mSwipeRefreshLayout.setRefreshing(false);
        mError.setVisibility(View.VISIBLE);
    }

    @Override
    public void onLoadMoreRecentQuestionSuccess() {
        getAdapter().notifyDataSetChanged();
    }

    @Override
    public void onLoadMOreRecentQuestionFailed() {
        getAdapter().notifyDataSetChanged();
    }
}
