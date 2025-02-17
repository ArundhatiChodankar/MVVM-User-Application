package com.example.mvvmuserapplication.model.data.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mvvmuserapplication.model.data.model.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavouriteUser(user: User)

    @Query("Select * from User")
    fun getAllFavouriteUsers(): LiveData<List<User>>

    @Query("Delete from User where id=:id")
    fun deleteFromFavourite(id: String)
}