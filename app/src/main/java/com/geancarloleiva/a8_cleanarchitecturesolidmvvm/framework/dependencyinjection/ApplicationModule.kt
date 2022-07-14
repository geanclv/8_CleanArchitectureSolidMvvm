package com.geancarloleiva.a8_cleanarchitecturesolidmvvm.framework.dependencyinjection

import android.app.Application
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule (val app: Application){

    @Provides
    fun providesApp(): Application {
        return app
    }
}