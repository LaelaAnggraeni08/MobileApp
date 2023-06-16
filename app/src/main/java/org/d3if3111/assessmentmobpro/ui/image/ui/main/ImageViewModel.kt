package org.d3if3111.assessmentmobpro.ui.image.ui.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.d3if3111.assessmentmobpro.R
import org.d3if3111.assessmentmobpro.ui.image.model.Image
import org.d3if3111.assessmentmobpro.ui.image.network.ImageApi
import org.d3if3111.assessmentmobpro.ui.image.network.UpdateWorker
import java.util.concurrent.TimeUnit

class ImageViewModel  : ViewModel() {

    private val data = MutableLiveData<List<Image>>()
    private val status = MutableLiveData<ImageApi.ApiStatus>()

    init {
        retrieveData()
    }

    private fun retrieveData() {
        viewModelScope.launch (Dispatchers.IO) {
            status.postValue(ImageApi.ApiStatus.LOADING)
            try {
                data.postValue(ImageApi.service.getImage())
                status.postValue(ImageApi.ApiStatus.SUCCESS)
            } catch (e: Exception) {
                Log.d("ImageViewModel", "Failure: ${e.message}")
                status.postValue(ImageApi.ApiStatus.FAILED)
            }
        }
    }

    fun getData(): LiveData<List<Image>> = data

    fun getStatus(): LiveData<ImageApi.ApiStatus> = status

    fun scheduleUpdater(app: Application) {
        val request = OneTimeWorkRequestBuilder<UpdateWorker>()
            .setInitialDelay(1, TimeUnit.MINUTES)
            .build()

        WorkManager.getInstance(app).enqueueUniqueWork(
            UpdateWorker.WORK_NAME,
            ExistingWorkPolicy.REPLACE,
            request
        )
    }

}
