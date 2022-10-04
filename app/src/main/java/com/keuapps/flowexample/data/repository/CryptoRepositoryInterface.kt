package com.keuapps.flowexample.data.repository

import com.keuapps.flowexample.data.remote.CryptoModel
import com.keuapps.flowexample.UiState

interface CryptoRepositoryInterface {
    suspend fun getData () : UiState<List<CryptoModel>>
}