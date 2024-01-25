package com.example.copy_busschedule

import android.app.Application
import com.example.copy_busschedule.data.AppContainer
import com.example.copy_busschedule.data.AppDataContainer

class BusScheduleApplication: Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}