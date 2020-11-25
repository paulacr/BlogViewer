package com.paulacr.blogviewer.di

import android.app.Application
import com.paulacr.blogviewer.feature.authors.AuthorsListActivity
import com.paulacr.data.network.NetworkModule
import com.paulacr.data.repository.DataModule
import dagger.BindsInstance
import dagger.Component

@Component(modules = [NetworkModule::class, DataModule::class])
interface ApplicationComponent {

    fun inject(activity: AuthorsListActivity)

    @Component.Builder
    interface Builder {
        fun build(): ApplicationComponent

        @BindsInstance
        fun application(application: Application): Builder
    }
}
