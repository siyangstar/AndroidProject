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

    /**
     * 获取用户数据
     */
    fun getUser(userId: String): LiveData<UserObject> {
        //从服务器获取
        getUserFromServer(userId)
        //返回数据库缓存的数据
        return userDao.getUser(userId)
    }

    /**
     * 开启协程，从服务器获取数据
     */
    fun getUserFromServer(userId: String) {
        job = Job()
        job?.let { theJob ->
            CoroutineScope(IO + theJob)
                .launch {
                    val user = MyRetrofitBuilder.apiService.getUser(userId)
                    userDao.insertUser(user)
                    theJob.complete()
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