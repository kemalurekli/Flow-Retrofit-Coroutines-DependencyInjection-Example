package com.keuapps.flowexample.data.repository

import com.keuapps.flowexample.data.remote.CryptoModel
import com.keuapps.flowexample.UiState
import com.keuapps.flowexample.data.remote.CryptoAPI
import javax.inject.Inject

class CryptoRepository@Inject constructor(
    private val cryptoAPI: CryptoAPI
) : CryptoRepositoryInterface {
    override suspend fun getData(): UiState<List<CryptoModel>> {
        return try {
            val response = cryptoAPI.getCryptos()
            if (response.isSuccessful){
                response.body()?.let {
                    return@let UiState.Success(it)
                } ?: UiState.Failure("Error")
            }else{
                UiState.Failure("Error")
            }
        }catch (e : Exception){
            UiState.Failure("Check Your Connection!")
        }
    }
}