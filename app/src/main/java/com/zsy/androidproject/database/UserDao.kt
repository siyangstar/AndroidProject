package com.zsy.androidproject.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.zsy.androidproject.database.entities.User

@Dao
interface UserDao {

    @Query("SELECT * FROM user_table ORDER BY id ASC")
    fun getUserList(): LiveData<List<User>>

    @Query("SELECT * FROM user_table WHERE username == :username")
    fun getUser(username: String): LiveData<User>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateUser(user: User)
}