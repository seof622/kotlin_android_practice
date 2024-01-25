package com.example.copy_busschedule.data

import android.content.Context

interface AppContainer {
    val busSchedulesRepository: BusSchedulesRepository
}

class AppDataContainer(private val context: Context): AppContainer {
    override val busSchedulesRepository: BusSchedulesRepository =
        BusSchedulesRepository(BusScheduleDatabase.getDatabase(context).busScheduleDao())
}