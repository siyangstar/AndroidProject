package com.zsy.androidproject.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.zsy.androidproject.models.UserObject

@Database(entities = [UserObject::class], version = 1)
abstract class ProjectDatabase: RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE: ProjectDatabase? = null

        fun getDatabase(context: Context): ProjectDatabase {
            if(INSTANCE != null) {
                return INSTANCE as ProjectDatabase
            }
            synchronized(this) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    ProjectDatabase::class.java,
                    "project_database"
                ).build()
                return INSTANCE as ProjectDatabase
            }
        }
    }

}