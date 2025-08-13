package com.mr.anonym.discounts.di.modules

import android.content.Context
import com.mr.anonym.data.local.instance.DataStoreInstance
import com.mr.anonym.data.local.instance.SharedPreferencesInstance
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferencesInstance =
        SharedPreferencesInstance(context)

    @Provides
    @Singleton
    fun provideDataStoreInstance( @ApplicationContext context: Context ): DataStoreInstance =
        DataStoreInstance(context)
}