package com.example.townhall.application

import android.app.Application
import com.example.townhall.model.database.TownHallRepository
import com.example.townhall.model.database.TownHallRoomDatabase

class TownHallApplication : Application() {

    private val database by lazy { TownHallRoomDatabase.getDatabase((this@TownHallApplication))}

    val repository by lazy { TownHallRepository(database.townHallDao()) }
}