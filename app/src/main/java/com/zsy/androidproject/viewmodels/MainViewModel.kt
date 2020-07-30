package com.zsy.androidproject.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations.switchMap
import com.zsy.androidproject.models.UserObject
import com.zsy.androidproject.repository.MainRepository

class MainViewModel(application: Application): AndroidViewModel(application) {
    //_userId为请求参数，在activity中改变参数即可请求数据并刷新界面
    private val _userId: MutableLiveData<String> = MutableLiveData()
    fun setUserId(userId: String) {
        if(userId == _userId.value) {
            return
        }
        _userId.value = userId
    }

    //每当_userId改变，则自动更新数据
    var userObject: LiveData<UserObject> = switchMap(_userId) {
        MainRepository.getUser(it)
    }

    //取消页面所有请求
    fun cancelJobs() {
        MainRepository.cancelJob()
    }
}