package com.daten.myandroidplus.app;

import com.avos.avoscloud.AVOSCloud;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVUser;
import com.daten.myandroidplus.BuildConfig;
import com.daten.myandroidplus.MyEventBusIndex;
import com.daten.myandroidplus.data.model.Question;
import com.daten.myandroidplus.data.model.User;
import com.daten.myandroidplus.di.DaggerAppComponent;

import org.greenrobot.eventbus.EventBus;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

public class AndroidPlusApp extends DaggerApplication {
    private static final String APP_ID = "mXf3cjfxtfxynDVpCKi1Xniu-gzGzoHsz";
    private static final String APP_KEY = "AGpc3hgzhsJnJWWJLRA21ynz";

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化Lean Cloud实体类
        EventBus.builder().addIndex(new MyEventBusIndex()).installDefaultEventBus();
        initSubClasses();
        AVOSCloud.initialize(this,APP_ID,APP_KEY);  //初始化LeanCloud
        AVOSCloud.setDebugLogEnabled(BuildConfig.DEBUG);    //设置DEBUG模式
    }

    private void initSubClasses() {
        AVObject.registerSubclass(User.class);
        AVObject.registerSubclass(Question.class);
        AVUser.alwaysUseSubUserClass(User.class);
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.create();
    }
}
