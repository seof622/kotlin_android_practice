package com.example.copy_busschedule.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.InitializerViewModelFactoryBuilder
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.copy_busschedule.BusScheduleApplication
import com.example.copy_busschedule.data.BusSchedule
import com.example.copy_busschedule.data.BusSchedulesRepository
import kotlinx.coroutines.flow.Flow

class BusScheduleViewModel(private val busSchedulesRepository: BusSchedulesRepository): ViewModel() {
    fun getAllBusSchedules(): Flow<List<BusSchedule>> {
        return busSchedulesRepository.getAllScheduleStream()
    }
    fun getBusSchedule(stopName: String): Flow<List<BusSchedule>> {
        return busSchedulesRepository.getScheduleStream(stopName)
    }

    companion object {
        val Factory : ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = this[APPLICATION_KEY] as BusScheduleApplication
                BusScheduleViewModel(application.container.busSchedulesRepository)
            }
        }
    }
}