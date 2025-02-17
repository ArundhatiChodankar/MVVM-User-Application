package com.example.mvvmuserapplication.model.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mvvmuserapplication.model.data.model.User

@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

        abstract  fun getUserDao():UserDao

        companion object {
            @Volatile
            private var instance: AppDatabase? = null

            operator fun invoke(context: Context) = instance ?: synchronized(this) {
                instance ?: createDatabase(context).also {
                    instance = it
                }
            }

            private fun createDatabase(context: Context) = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "userDb.db"
            ).build()
        }
}