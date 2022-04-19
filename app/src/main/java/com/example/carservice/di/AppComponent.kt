package com.example.carservice.di

import com.example.carservice.data.repositories.Api
import com.example.carservice.data.utils.Constants
import com.example.carservice.ui.home.HomeFragment
import dagger.Component
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Component(modules = [ServiceModule::class])
interface AppComponent {
    fun inject(fragment: HomeFragment)

    val getServiceModule: Api
}

@Module
object ServiceModule {

    @Provides
    fun provideCarService(): Api {
        val logging = HttpLoggingInterceptor()
        val httpClient = OkHttpClient.Builder()

        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        httpClient.addInterceptor(logging).networkInterceptors().add(Interceptor {
            val requestBuilder: Request.Builder = it.request().newBuilder()
            requestBuilder.header(Constants.API_KEY, Constants.CATS_KEY)
            it.proceed(requestBuilder.build())
        })

        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())
            .build()
            .create(Api::class.java)
    }

}