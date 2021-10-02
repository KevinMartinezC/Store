package com.example.store.repository

import com.example.store.Dao.StoreDao
import android.content.Context
import com.example.store.database.StoreRoomDatabase
import com.example.store.entities.Store
import kotlinx.coroutines.flow.Flow

class StoreRepository(private val StoreDao: StoreDao) {
    companion object {
        private var INSTANCE: StoreRepository? = null
        fun getRepository(context: Context): StoreRepository {
            return INSTANCE ?: synchronized(this) {
                val database = StoreRoomDatabase.getDatabase(context)
                val instance = StoreRepository(database.StoreDao())
                INSTANCE = instance
                instance
            }
        }
    }

    val Products: Flow<List<Store>> = StoreDao.getAlphabetizedStore()


    suspend fun insert(Store: Store) {
        StoreDao.insert(Store)
    }

    suspend fun deleteOneItem(id: Int) {
        StoreDao.deleteOneItem(id)

    }



    }

