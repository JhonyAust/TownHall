package com.example.townhall.model.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.townhall.model.entities.TownHall
import kotlinx.coroutines.flow.Flow


@Dao
interface TownHallDao {
    @Insert
    suspend fun insertTownHallDetails(townHall:TownHall)
    @Query("SELECT * FROM TOWNHALL_TABLE ORDER BY ID")
    fun getAllDishesList() : Flow<List<TownHall>>

    @Update
    suspend fun updateTownHallDetails(townHall: TownHall)

    @Query("SELECT * FROM TOWNHALL_TABLE WHERE favorite_dish = 1")
    fun getFavoriteDishesList() : Flow<List<TownHall>>

}