package com.example.amphibians.ui.screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.amphibians.AmphibianInfoApplication
import com.example.amphibians.data.AmphibianInfoRepository
import com.example.amphibians.network.AmphibianInfo
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

/*
sealed interface
- 정해진 갯수의 상태를 알고 있는 경우 유용함
- when 문에서 default를 생략 가능함
 */
sealed interface AmphibiansUiState {
    data class Success(val amphibians: List<AmphibianInfo>): AmphibiansUiState
    object Loading : AmphibiansUiState
    object Error : AmphibiansUiState
}

class AmphibianViewModel(private val amphibianInfoRepository: AmphibianInfoRepository) : ViewModel() {
    var amphibiansUiState: AmphibiansUiState by mutableStateOf(AmphibiansUiState.Loading)
        private set

    init {
        getAmphibiansInfo()
    }

    fun getAmphibiansInfo() {
        viewModelScope.launch {
            amphibiansUiState = try {
                AmphibiansUiState.Success(amphibianInfoRepository.getAmphibianInfo())
            } catch (e: IOException) {
                AmphibiansUiState.Error
            } catch (e: HttpException) {
                AmphibiansUiState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as AmphibianInfoApplication)
                val amphibianInfoRepository = application.container.amphibianInfoRepository
                AmphibianViewModel(amphibianInfoRepository = amphibianInfoRepository)
            }
        }
    }
}