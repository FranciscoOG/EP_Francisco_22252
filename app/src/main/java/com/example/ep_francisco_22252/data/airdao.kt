package com.example.ep_francisco_22252.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface Airdao{
    @Query("SELECT * FROM air")
    fun getAllOds():Flow<List<Air>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAir(air: List<Air>)

    @Query("DELETE FROM air")
    suspend fun clearAir()

    @Query("SELECT * FROM air WHERE latitude LIKE :lat AND longitude LIKE :long")
    fun getAirQuality(lat: Double, long: Double): Flow<List<Air>>
}
