package com.zsy.androidproject.repository

import androidx.lifecycle.LiveData
import com.zsy.androidproject.api.MyRetrofitBuilder
import com.zsy.androidproject.database.UserDao
import com.zsy.androidproject.models.UserObject
import kotlinx.coroutines.CompletableJob
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

object MainRepository {
    private var job: CompletableJob? = null
    lateinit var userDao: UserDao
    lateinit var userLiveData: LiveData<UserObject>

    /**
     * 初始化数据
     */
    fun fetchUser(userId: String): LiveData<UserObject> {
        userLiveData = userDao.getUser(userId)
        return userLiveData
    }

    /**
     * 开启协程，从服务器更新数据
     */
    fun updateUser(userId: String) {
        job = Job()
        job?.let { theJob ->
            CoroutineScope(IO + theJob)
                .launch {
                    try {
                        val user = MyRetrofitBuilder.apiService.getUser(userId)
                        userDao.insertUser(user)
                    } catch (e: Exception) {
                        e.printStackTrace()
                    } finally {
                        theJob.complete()
                    }
                }
        }
    }

    /**
     * 取消协程任务
     */
    fun cancelJob() {
        job?.cancel()
    }
}