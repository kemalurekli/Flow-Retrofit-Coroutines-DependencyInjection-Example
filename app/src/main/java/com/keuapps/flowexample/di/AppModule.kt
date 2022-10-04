package com.keuapps.flowexample.di

import com.keuapps.flowexample.data.remote.CryptoAPI
import com.keuapps.flowexample.data.repository.CryptoRepository
import com.keuapps.flowexample.data.repository.CryptoRepositoryInterface
import com.keuapps.flowexample.util.Util.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun injectRetrofitAPI () : CryptoAPI {
        return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).
        baseUrl(BASE_URL).build().create(CryptoAPI::class.java)
    }
    @Singleton
    @Provides
    fun injectNormalRepo(api: CryptoAPI) = CryptoRepository(api) as CryptoRepositoryInterface

}