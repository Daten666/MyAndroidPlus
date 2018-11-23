package com.daten.myandroidplus.ui.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.daten.myandroidplus.R;
import com.daten.myandroidplus.adapter.BaseLoadingListAdapter;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;

public abstract class BaseRefreshableListFragment<T> extends BaseFragment {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.error)
    Button mError;
    @BindView(R.id.refreshable_root)
    FrameLayout mRefreshableRoot;

    private int mScrollState = 0;
    private static final int SCROLL_UP = 1;
    private static final int SCROLL_DOWN = 2;

    BaseLoadingListAdapter<T> mAdapter;

    /**
     * 子类必须实现该方法创建返回ViewPager的Adapter
     * @return ViewPager的Adapter
     */
    protected abstract BaseLoadingListAdapter<T> onCreateAdapter();
    //开始刷新数据
    protected abstract void startRefresh();
    //开始加载更多数据
    protected abstract void startLoadMoreData();

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_base_refreshable_list;
    }

    @Override
    protected void init() {
        super.init();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = onCreateAdapter();
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addOnScrollListener(mOnScrollListener);

        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        mSwipeRefreshLayout.setOnRefreshListener(mOnRefreshListener);
        mSwipeRefreshLayout.setRefreshing(true);
    }

    private SwipeRefreshLayout.OnRefreshListener mOnRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            startRefresh();
        }
    };

    private RecyclerView.OnScrollListener mOnScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            if (isEnableScrollEvent() && dy != 0) {
                postScrollEvent(dy);//发布事件
            }
        }

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            if (mRecyclerView == null || mRecyclerView.getAdapter().getItemCount() == 0) {
                return;
            }
            if (newState == RecyclerView.SCROLL_STATE_IDLE && shouldLoadMore()) {
                startLoadMoreData();
            }
        }
    };

    private boolean shouldLoadMore() {
        LinearLayoutManager layoutManager = (LinearLayoutManager) mRecyclerView.getLayoutManager();
        return layoutManager.findLastVisibleItemPosition() == mRecyclerView.getAdapter().getItemCount() - 1;
    }

    /**
     * 发布滚动事件
     * @param dy
     */
    private void postScrollEvent(int dy) {
        if (dy > 0) {//RecyclerView向上滚动
            if (mScrollState != SCROLL_UP) {
                //EventBus.getDefault().post(new ScrollEvent(ScrollEvent.Direction.UP));
                mScrollState = SCROLL_UP;
            }
        } else {//RecyclerView向下滚动
            if (mScrollState != SCROLL_DOWN) {
                //EventBus.getDefault().post(new ScrollEvent(ScrollEvent.Direction.DOWN));
                mScrollState = SCROLL_DOWN;
            }
        }
    }

    public boolean isEnableScrollEvent() {
        return true;
    }

    @OnClick(R.id.error)
    public void onViewClicked() {
        mError.setVisibility(View.GONE);
        mSwipeRefreshLayout.setRefreshing(true);
        startRefresh();
    }

    protected BaseLoadingListAdapter<T> getAdapter() {
        return mAdapter;
    }
}
