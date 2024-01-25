package com.example.copy_busschedule.data

import kotlinx.coroutines.flow.Flow

class BusSchedulesRepository(private val busScheduleDao: BusScheduleDao): SchedulesRepository {
    override fun getAllScheduleStream(): Flow<List<BusSchedule>>  {
        return busScheduleDao.getAllBusSchedule()
    }

    override fun getScheduleStream(stopName: String): Flow<List<BusSchedule>> {
        return busScheduleDao.getBusSchedule(stopName)
    }
}