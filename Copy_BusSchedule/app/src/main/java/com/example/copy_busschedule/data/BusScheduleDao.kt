package com.example.copy_busschedule.data

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface BusScheduleDao {
    @Query("SELECT * from Schedule")
    fun getAllBusSchedule(): Flow<List<BusSchedule>>

    @Query("SELECT * from Schedule where stop_name = :stopName")
    fun getBusSchedule(stopName: String): Flow<List<BusSchedule>>
}