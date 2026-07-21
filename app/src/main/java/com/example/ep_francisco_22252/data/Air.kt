package com.example.ep_francisco_22252.data

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "air")
data class Air(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val elevation: Int,
    val latitude: Double,
    val longitude: Double,
    val pm10: Float,
    val pm2_5: Float
)

