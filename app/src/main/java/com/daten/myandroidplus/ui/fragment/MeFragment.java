package com.daten.myandroidplus.ui.fragment;

import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.avos.avoscloud.AVUser;
import com.daten.myandroidplus.R;
import com.daten.myandroidplus.data.model.User;
import com.daten.myandroidplus.di.ActivityScoped;

import javax.inject.Inject;

import butterknife.BindView;

@ActivityScoped
public class MeFragment extends BaseFragment {

    @BindView(R.id.tool_bar)
    Toolbar mToolBar;
    @BindView(R.id.avatar)
    ImageView mAvatar;
    @BindView(R.id.user_name)
    TextView mUserName;
    @BindView(R.id.slogan)
    TextView mSlogan;
    private User mUser;

    @Inject
    public MeFragment() {}

    @Override
    protected void init() {
        super.init();
        mUser = AVUser.getCurrentUser(User.class);
        mUserName.setText(mUser.getUsername());
        if (mUser.getSlogan() != null) {
            mSlogan.setText(mUser.getSlogan());
        }
//        GlideApp.with(this)
//                .load(mUser.getAvatar())
//                .transform(new CircleCrop())
//                .transition(new DrawableTransitionOptions().crossFade())
//                .placeholder(R.mipmap.ic_launcher_round)
//                .into(mAvatar);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_me;
    }
}
