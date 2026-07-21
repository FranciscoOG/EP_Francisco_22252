package com.example.ep_francisco_22252.viewmodel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import androidx.lifecycle.viewModelScope
import com.example.ep_francisco_22252.data.appdb.AppDB
import com.example.ep_francisco_22252.network.RetrofitClient

class airViewModel(private val db: AppDB) : ViewModel() {
    private val _uiState = MutableStateFlow(airUIState())
    val uiState: StateFlow<airUIState> = _uiState

    init {
        loadOdsData()
    }

    private fun loadOdsData() {
        viewModelScope.launch {
            _uiState.update { it.copy(Loading = true, errorMessage = null) }
            try {
                val getods = RetrofitClient.service.getAir()
                if (getods.isNotEmpty()) {
                    db.airdao().clearAir()
                    db.airdao().saveAir(getods)
                }

                db.airdao().getAllOds().collect { listaOds ->
                    _uiState.update { it.copy(odsList = listaOds, Loading = false) }
                }
            } catch (e: Exception) {
                db.airdao().getAllOds().collect { listaCache ->
                    if (listaCache.isNotEmpty()) {
                        _uiState.update { it.copy(odsList = listaCache, Loading = false) }
                    } else {
                        _uiState.update { it.copy(Loading = false, errorMessage = "Erro de rede e sem dados locais.") }
                    }
                }
            }
        }
    }
}