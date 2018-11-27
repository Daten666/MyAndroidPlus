package com.daten.myandroidplus.ui.activity;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.daten.myandroidplus.R;
import com.daten.myandroidplus.app.Constant;
import com.daten.myandroidplus.app.GlideApp;
import com.daten.myandroidplus.data.LoadCallback;
import com.daten.myandroidplus.data.model.User;

import java.util.List;

import butterknife.BindView;

public class ProfileActivity extends BaseActivity {

    private static final String TAG = "ProfileActivity";

    @BindView(R.id.tool_bar)
    Toolbar mToolBar;
    @BindView(R.id.profile_background)
    ImageView mProfileBackground;
    @BindView(R.id.avatar)
    ImageView mAvatar;
    @BindView(R.id.profile_info)
    LinearLayout mProfileInfo;
    @BindView(R.id.collapse_toolbar_layout)
    CollapsingToolbarLayout mCollapseToolbarLayout;
    @BindView(R.id.app_bar_layout)
    AppBarLayout mAppBarLayout;
    @BindView(R.id.slogan)
    TextView mSlogan;
    @BindView(R.id.user_share)
    Button mMyShare;
    @BindView(R.id.user_questions)
    Button mMyQuestions;
    @BindView(R.id.user_answers)
    Button mMyAnswers;
    @BindView(R.id.user_favour_articles)
    Button mMyFavourArticles;
    @BindView(R.id.user_favour_questions)
    Button mMyFavourQuestions;
    @BindView(R.id.user_favour_answers)
    Button mMyFavourAnswers;

    private User mProfileUser;
    private String mUserId;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_profile;
    }

    @Override
    protected void init() {
        super.init();
        setSupportActionBar(mToolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mUserId = getIntent().getStringExtra(Constant.EXTRA_USER_ID);
        if (User.getCurrentUser().getObjectId().equals(mUserId)) {
            mProfileUser = User.getCurrentUser(User.class);
            updateUserInfo();
        } else {
            loadProfileUser();
            changeToOtherUser();
        }

        mAppBarLayout.addOnOffsetChangedListener(mOnOffsetChangedListener);

        setStatusBarTransparent();
    }

    private void changeToOtherUser() {
        mMyShare.setText(R.string.his_share);
        mMyQuestions.setText(R.string.his_questions);
        mMyAnswers.setText(R.string.his_answers);
        mMyFavourQuestions.setText(R.string.his_favour_questions);
        mMyFavourAnswers.setText(R.string.his_favour_answers);
        mMyFavourArticles.setText(R.string.his_favour_articles);
    }

    private void loadProfileUser() {
        User.getUser(new LoadCallback<User>() {
            @Override
            public void onLoadSuccess(List<User> list) {
                Log.d(TAG, "onLoadSuccess: ");
                mProfileUser = list.get(0);
                updateUserInfo();
            }

            @Override
            public void onLoadFailed(String errorMsg) {

            }
        }, mUserId);
    }

    private void updateUserInfo() {
        getSupportActionBar().setTitle(mProfileUser.getUsername());
        mSlogan.setText(mProfileUser.getSlogan());
        GlideApp.with(this)
                .load(mProfileUser.getAvatar())
                .placeholder(R.mipmap.ic_launcher_round)
                .transform(new CircleCrop()).into(mAvatar);
    }

    private AppBarLayout.OnOffsetChangedListener mOnOffsetChangedListener = new AppBarLayout.OnOffsetChangedListener() {
        @Override
        public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
            int maxScroll = appBarLayout.getTotalScrollRange();
            float percentage = (float) Math.abs(verticalOffset) / (float) maxScroll;
            mProfileInfo.setAlpha(1 - percentage);
        }
    };
}
