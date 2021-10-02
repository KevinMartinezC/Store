package com.example.store.database

import com.example.store.Dao.StoreDao
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.store.entities.Store

@Database(entities = [Store::class], version = 1, exportSchema = false)
 abstract class StoreRoomDatabase : RoomDatabase() {
    abstract fun StoreDao(): StoreDao
    companion object {
        // Singleton prevents multiple instances of com.example.store.database opening at the same time
        @Volatile
        private var INSTANCE: StoreRoomDatabase? = null
        fun getDatabase(context: Context): StoreRoomDatabase{
                // if the INSTANCE is not null, then return it,
                // if it is, then create the com.example.store.database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    StoreRoomDatabase::class.java,
                    "store_database"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}