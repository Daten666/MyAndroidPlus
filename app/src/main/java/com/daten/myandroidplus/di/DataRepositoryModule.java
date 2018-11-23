package com.daten.myandroidplus.di;

import com.daten.myandroidplus.data.QuestionDataRepository;
import com.daten.myandroidplus.data.QuestionDataSource;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;

@Module
public interface DataRepositoryModule {
    @Singleton
    @Binds
    QuestionDataSource bindsQuestionDataSource(QuestionDataRepository questionDataRepository);
}
