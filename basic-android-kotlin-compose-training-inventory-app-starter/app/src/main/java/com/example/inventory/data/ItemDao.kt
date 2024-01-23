package com.example.inventory.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao {
    // Room은 기본 스레드에서 db access를 허용하지 않으므로 suspend로 선언해야 함.
    // onConflict는 db에 insert할 때 동시에 진행되는 경우 하나의 고유 키 값에 대해 여러 insert가 생길 수 있으므로 이를 방지하고자 room에서 해야할 작업을 정의
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(ite: Item)

    @Update
    suspend fun update(item: Item)

    @Delete
    suspend fun delete(item: Item)

    @Query("SELECT * from items where id = :id")
    fun getItem(id: Int): Flow<Item>

    @Query("SELECT * from items ORDER BY name ASC")
    fun getAllItems(): Flow<List<Item>>
}