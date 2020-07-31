package com.zsy.androidproject.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.zsy.androidproject.database.ProjectDatabase
import com.zsy.androidproject.models.UserObject
import com.zsy.androidproject.repository.MainRepository

class MainViewModel(application: Application): AndroidViewModel(application) {

    var userObject: LiveData<UserObject>

    init {
        val userDao = ProjectDatabase.getDatabase(application).userDao()
        MainRepository.userDao = userDao
        userObject = MainRepository.fetchUser("1") //初始化
    }

    /**
     * 更新数据
     */
    fun updateUser(userId: String) {
        MainRepository.updateUser(userId)
    }

    /**
     * 取消页面所有请求
     */
    fun cancelJobs() {
        MainRepository.cancelJob()
    }
}