package com.zhuravlevmikhail.a65appshomeproject.diContainer.modules.data

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.zhuravlevmikhail.a65appshomeproject.common.AppConst.GEOCODE_BASE_URL
import com.zhuravlevmikhail.a65appshomeproject.data.retrofit.YandexGeocodeApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideGson(): Gson {
        return GsonBuilder().create()
    }
    
    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = BODY
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor())
            .build()
    }

    @Singleton
    @Provides
    fun provideLogInterceptor() : HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = BODY
        return interceptor
    }

    @Singleton
    @Provides
    fun provideGeoCodeApi(gson: Gson, client: OkHttpClient): YandexGeocodeApi {
        return Retrofit.Builder()
            .baseUrl(GEOCODE_BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()
            .create(YandexGeocodeApi::class.java)
    }
}