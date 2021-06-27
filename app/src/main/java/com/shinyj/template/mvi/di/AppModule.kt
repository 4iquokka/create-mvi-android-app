package com.shinyj.template.mvi.di

import android.content.Context
import com.shinyj.template.mvi.BaseApplication
import com.shinyj.template.mvi.preferences.UserPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideApplication(
        @ApplicationContext application: Context
    ): BaseApplication {
        return application as BaseApplication
    }

    @Singleton
    @Provides
    fun provideUserPreferences(
        application : BaseApplication
    ) : UserPreferences {
        return UserPreferences(application)
    }

}