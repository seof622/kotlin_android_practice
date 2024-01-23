package com.example.busschedule.data

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface BusScheduleDao {
    @Query("SELECT * FROM Schedule")
    fun getAllSchedules(): Flow<List<BusSchedule>>

    @Query("SELECT * FROM Schedule where stop_name = :stopName")
    fun getSchedule(stopName: String): Flow<List<BusSchedule>>
}