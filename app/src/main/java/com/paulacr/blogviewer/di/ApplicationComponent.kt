package com.paulacr.blogviewer.di

import android.app.Application
import com.paulacr.blogviewer.authors.MainActivity
import com.paulacr.data.network.NetworkModule
import com.paulacr.data.repository.DataModule
import dagger.BindsInstance
import dagger.Component

@Component(modules = [NetworkModule::class, DataModule::class])
interface ApplicationComponent {

    fun inject(activity: MainActivity)

    @Component.Builder
    interface Builder {
        fun build(): ApplicationComponent

        @BindsInstance
        fun application(application: Application): Builder
    }
}
