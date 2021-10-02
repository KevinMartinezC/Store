package com.example.store.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.store.entities.Store
import kotlinx.coroutines.flow.Flow
@Dao
interface StoreDao {
        @Query("SELECT * FROM store_table ORDER BY name ASC")
        fun getAlphabetizedStore(): Flow<List<Store>>
        @Insert(onConflict = OnConflictStrategy.IGNORE)
        suspend fun insert( store: Store)
        @Query("DELETE FROM store_table WHERE  id=:id")
        suspend fun deleteOneItem(id:Int)

}