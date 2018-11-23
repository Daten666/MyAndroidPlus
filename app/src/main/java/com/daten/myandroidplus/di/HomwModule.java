package com.daten.myandroidplus.di;

import com.daten.myandroidplus.ui.fragment.DynamicFragment;
import com.daten.myandroidplus.ui.fragment.HotQuestionFragment;
import com.daten.myandroidplus.ui.fragment.QuestionFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
abstract class HomwModule {

    @ChildFragmentScope
    @ContributesAndroidInjector
    abstract QuestionFragment questionFragment();

    @ChildFragmentScope
    @ContributesAndroidInjector
    abstract DynamicFragment dynamicFragment();

    @ChildFragmentScope
    @ContributesAndroidInjector
    abstract HotQuestionFragment hotQuestionFragment();
}
