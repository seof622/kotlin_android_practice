package com.example.copy_busschedule.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Schedule")
data class BusSchedule (
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "stop_name") val stopName: String,
    @ColumnInfo(name = "arrival_time") val arrivalTime: Int
)