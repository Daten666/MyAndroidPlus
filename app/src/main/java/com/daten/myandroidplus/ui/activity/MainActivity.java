package com.daten.myandroidplus.ui.activity;

import android.animation.ObjectAnimator;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;

import com.daten.myandroidplus.R;
import com.daten.myandroidplus.event.ScrollEvent;
import com.daten.myandroidplus.ui.fragment.ArticleFragment;
import com.daten.myandroidplus.ui.fragment.HomeFragment;
import com.daten.myandroidplus.ui.fragment.MeFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;

import butterknife.BindView;
import dagger.Lazy;

public class MainActivity extends BaseActivity {

    @BindView(R.id.fragment_frame)
    FrameLayout mFragmentFrame;
    @BindView(R.id.bottom_navigation)
    BottomNavigationView mBottomNavigation;

    @Inject
    Lazy<HomeFragment> homeFragmentProvider;

    @Inject
    Lazy<ArticleFragment> articleFragmentProvider;

    @Inject
    Lazy<MeFragment> meFragmentProvider;

    @Override
    protected void init() {
        super.init();
        //监听底部导航条切换事件
        mBottomNavigation.setOnNavigationItemSelectedListener(mOnOnNavigationItemSelectedListener);
        mBottomNavigation.setSelectedItemId(R.id.main_home);
        EventBus.getDefault().register(this);

    }

    //底部导航条切换监听器
    private BottomNavigationView.OnNavigationItemSelectedListener
    mOnOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switchPage (item.getItemId());//通过三个Fragment的Provider获取Fragment对象
            return true;
        }
    };

    /**
     * 页面切换
     * @param itemId
     */
    private void switchPage(int itemId) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        switch (itemId) {
            case R.id.main_home:
                fragmentTransaction.replace(R.id.fragment_frame, homeFragmentProvider.get());
                break;
            case R.id.main_article:
                fragmentTransaction.replace(R.id.fragment_frame, articleFragmentProvider.get());
                break;
            case R.id.main_me:
                fragmentTransaction.replace(R.id.fragment_frame, meFragmentProvider.get());
                break;
        }
        fragmentTransaction.commit();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onScrollChange(ScrollEvent scrollEvent) {
        if (scrollEvent.getDirection() == ScrollEvent.Direction.UP) {
            hideNavigationView();
        } else {
            showNavigationView();
        }
    }

    private void showNavigationView() {
        animationNavigationView(mBottomNavigation.getHeight(), 0);
    }

    private void hideNavigationView() {
        animationNavigationView(0, mBottomNavigation.getHeight());
    }

    private void animationNavigationView(int from, int to) {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(mBottomNavigation, "translationY",
                from, to);
        objectAnimator.setDuration(500);
        objectAnimator.setInterpolator(new LinearInterpolator());
        objectAnimator.start();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //EventBus.getDefault().unregister(this);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_main;
    }
}
