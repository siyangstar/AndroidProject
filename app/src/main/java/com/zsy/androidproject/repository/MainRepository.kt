package com.zsy.androidproject.repository

import androidx.lifecycle.LiveData
import com.zsy.androidproject.api.MyRetrofitBuilder
import com.zsy.androidproject.models.UserObject
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main

object MainRepository {
    var job: CompletableJob? = null

    fun getUser(userId: String): LiveData<UserObject> {
        job = Job()
        return object : LiveData<UserObject>() {
            override fun onActive() {
                super.onActive()
                job?.let { theJob -> CoroutineScope(IO + theJob)
                    .launch {
                        val user = MyRetrofitBuilder.apiService.getUser(userId)
                        withContext(Main) {
                            value = user
                            theJob.complete()
                        }
                    }}
            }
        }
    }

    fun cancelJob() {
        job?.cancel()
    }
}