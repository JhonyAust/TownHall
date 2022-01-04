package com.example.townhall.model.database

import androidx.annotation.WorkerThread
import com.example.townhall.model.entities.TownHall
import kotlinx.coroutines.flow.Flow

class TownHallRepository(private val townHallDao: TownHallDao) {

    @WorkerThread
    suspend fun insertTownHallData(townHall:TownHall){
        townHallDao.insertTownHallDetails((townHall))
    }
    val allDishesList: Flow<List<TownHall>> =townHallDao.getAllDishesList()

    @WorkerThread
    suspend fun updateTownHallData(townHall:TownHall){
        townHallDao.updateTownHallDetails((townHall))
    }

    val favoriteDishes: Flow<List<TownHall>> =townHallDao.getFavoriteDishesList()

    @WorkerThread
    suspend fun deleteTownHallData(townHall:TownHall){
        townHallDao.deleteTownHallDetails((townHall))
    }
    fun filteredListDishes(value:String): Flow<List<TownHall>> =townHallDao.getFilteredDishesList(value)
}