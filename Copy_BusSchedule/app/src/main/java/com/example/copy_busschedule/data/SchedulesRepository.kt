package com.example.copy_busschedule.data

import kotlinx.coroutines.flow.Flow

interface SchedulesRepository {
    fun getAllScheduleStream(): Flow<List<BusSchedule>>
    fun getScheduleStream(stopName: String): Flow<List<BusSchedule>>
}