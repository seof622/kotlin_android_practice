package com.example.busschedule.data

import kotlinx.coroutines.flow.Flow

class BusScheduleRepository(private val busScheduleDao: BusScheduleDao): ScheduleRepository {
    override fun getAllSchedulesStream(): Flow<List<BusSchedule>> {
        return busScheduleDao.getAllSchedules()
    }

    override fun getScheduleStream(stopName: String): Flow<List<BusSchedule>> {
        return busScheduleDao.getSchedule(stopName)
    }
}