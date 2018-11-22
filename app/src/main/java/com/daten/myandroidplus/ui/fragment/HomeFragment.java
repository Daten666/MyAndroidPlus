package com.daten.myandroidplus.ui.fragment;

import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.daten.myandroidplus.R;
import com.daten.myandroidplus.adapter.HomePagerAdapter;
import com.daten.myandroidplus.di.ActivityScoped;

import javax.inject.Inject;

@ActivityScoped
public class HomeFragment extends BaseMainFragment {

    private Toolbar.OnMenuItemClickListener mOnMenuItemClickListener = new Toolbar.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem item) {
//            Intent intent = new Intent(getContext(), AddQuestionActivity.class);
//            startActivity(intent);
            return true;
        }
    };

    @Inject
    public HomeFragment() {}

    @Override
    protected void init() {
        super.init();
        mToolBar.inflateMenu(R.menu.home_menu);
        mToolBar.setOnMenuItemClickListener(mOnMenuItemClickListener);
    }

    @Override
    PagerAdapter getPagerAdapter() {
        return new HomePagerAdapter(getChildFragmentManager(),
                getResources().getStringArray(R.array.home_category));
    }
}
