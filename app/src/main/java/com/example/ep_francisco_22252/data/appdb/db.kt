package com.example.ep_francisco_22252.data.appdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.ep_francisco_22252.data.Airdao
import com.example.ep_francisco_22252.data.Air

@Database(entities = [Air::class], version = 1, exportSchema = false)
abstract class AppDB : RoomDatabase() {

    // 2. Link your DAO
    abstract fun airdao(): Airdao

    companion object {
        @Volatile
        private var Instance: com.example.ep_francisco_22252.data.appdb.AppDB? = null

        // 3. Singleton pattern to prevent multiple database instances
        fun getdb(context: Context): com.example.ep_francisco_22252.data.appdb.AppDB {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context, com.example.ep_francisco_22252.data.appdb.AppDB::class.java, "ods_db")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also{Instance = it}
            }
        }
    }
}