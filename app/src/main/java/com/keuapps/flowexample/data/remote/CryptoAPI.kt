package com.keuapps.flowexample.data.remote

import com.keuapps.flowexample.UiState
import retrofit2.Response
import retrofit2.http.GET

interface CryptoAPI {

    @GET("kemalurekli/flow-example/crypto.json")
    suspend fun getCryptos(

    ) : Response<List<CryptoModel>>
}