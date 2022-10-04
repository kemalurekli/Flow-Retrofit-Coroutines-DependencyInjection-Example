package com.keuapps.flowexample.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.keuapps.flowexample.UiState
import com.keuapps.flowexample.data.remote.CryptoModel
import com.keuapps.flowexample.data.repository.CryptoRepositoryInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.lang.Thread.sleep
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor(
    private val repository: CryptoRepositoryInterface
) :ViewModel() {
    private val _sharedFlow = MutableSharedFlow<UiState<List<CryptoModel>>>()
    val sharedFlow = _sharedFlow.asSharedFlow()

    private fun getDataFromAPI(){
        viewModelScope.launch {
            _sharedFlow.emit(repository.getData())
        }
    }

    init {
        viewModelScope.launch {
            sharedFlow.collect { state ->
                when(state) {
                    is UiState.Loading ->{
                        println("Yükleniyor.")
                    }
                    is UiState.Failure -> {
                        println("Hata Oluştu!")
                    }
                    is UiState.Success -> {
                        state.data.forEach {
                            println(it.currency)
                        }
                    }
                }
            }
        }
        getDataFromAPI()
    }
}