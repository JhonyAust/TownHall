package com.example.townhall.model.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.townhall.model.entities.TownHall

@Database(entities = [TownHall::class] ,version = 1)
abstract class TownHallRoomDatabase: RoomDatabase() {
    abstract fun townHallDao():TownHallDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: TownHallRoomDatabase? = null

        fun getDatabase(context: Context): TownHallRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TownHallRoomDatabase::class.java,
                    "town_hall_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}