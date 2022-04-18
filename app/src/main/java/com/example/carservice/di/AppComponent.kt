package com.example.carservice.di

import com.example.carservice.data.utils.Network
import dagger.Component
import dagger.Module
import dagger.Provides

@Component(modules = [ServiceModule::class])
interface AppComponent {
    fun getServiceModule(): Network
}

@Module
object ServiceModule {

    @Provides
    fun provideCarService(): Network {
        return Network
    }

}