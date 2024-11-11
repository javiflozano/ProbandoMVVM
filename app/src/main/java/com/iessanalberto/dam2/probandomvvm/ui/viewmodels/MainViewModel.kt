package com.iessanalberto.dam2.probandomvvm.ui.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.iessanalberto.dam2.probandomvvm.ui.states.MainUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MainViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(MainUiState()) // Variables de estado en el viewmodel (solo se pueden ver/modificar desde aquí)
    val uiState: StateFlow<MainUiState> = _uiState.asStateFlow() // Variables de estado para ver, pero no modificar en cualquier lugar

    var otroTexto by mutableStateOf("")
        private set

    fun onMiTextoChanged(texto: String){
        _uiState.update {
            currentState -> currentState.copy(miTexto = texto)
        }
    }

    fun addCounter(){
        _uiState.update {
                currentState -> currentState.copy(contador = _uiState.value.contador.inc())
        }
    }

    fun onOtroTextoChanged(texto: String){
        otroTexto = texto
    }
}