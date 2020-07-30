package com.zsy.androidproject.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.zsy.androidproject.models.UserObject

@Dao
interface UserDao {

    @Query("SELECT * FROM user_table")
    fun getUserList(): LiveData<List<UserObject>>

    @Query("SELECT * FROM user_table WHERE id == :id")
    fun getUser(id: String): LiveData<UserObject>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserObject)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateUser(user: UserObject
    )
}