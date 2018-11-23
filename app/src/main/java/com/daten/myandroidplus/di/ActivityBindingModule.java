package com.daten.myandroidplus.di;

import com.daten.myandroidplus.ui.activity.AddQuestionActivity;
import com.daten.myandroidplus.ui.activity.LoginActivity;
import com.daten.myandroidplus.ui.activity.MainActivity;
import com.daten.myandroidplus.ui.activity.RegisterActivity;
import com.daten.myandroidplus.ui.activity.SplashActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBindingModule {
    @ActivityScoped
    @ContributesAndroidInjector
    abstract SplashActivity splashActivity();

    @ActivityScoped
    @ContributesAndroidInjector
    abstract LoginActivity loginActivity();

    @ActivityScoped
    @ContributesAndroidInjector
    abstract RegisterActivity registerActivity();

    @ActivityScoped
    @ContributesAndroidInjector(modules = MainModule.class)
    abstract MainActivity mainActivity();

    @ActivityScoped
    @ContributesAndroidInjector
    abstract AddQuestionActivity addQuestionActivity();
}
