package com.example.busschedule.data

import android.content.Context

interface AppContainer {
    val busScheduleRepository: BusScheduleRepository
}

class AppDataContainer(private val context: Context) : AppContainer {
    override val busScheduleRepository: BusScheduleRepository by lazy {
        BusScheduleRepository(BusScheduleDatabase.getDatabase(context).busScheduleDao())
    }
}