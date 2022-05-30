package com.indigoag.android.homework.ui

import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

open class BaseViewModel : ViewModel() {
    val progressLiveData = MutableLiveData<Boolean>()
    val errorLiveData = MutableLiveData<Throwable>()


    fun <T> executeLiveData(func: suspend () -> T) =
        liveData(Dispatchers.IO) {
            try {
                progressLiveData.postValue(true)
                val value = func()
                emit(value)
                progressLiveData.postValue(false)
            } catch (ex: Exception) {
                progressLiveData.postValue(false)
            }
        }

    fun executeSuspend(func: suspend () -> Unit) =
        viewModelScope.launch(Dispatchers.IO) {
            try {
                progressLiveData.postValue(true)
                func()
                progressLiveData.postValue(false)
            } catch (ex: Exception) {
                progressLiveData.postValue(false)
                handleException(ex)
            }
        }

    private fun handleException(ex: Exception) {
        ex.printStackTrace()
        errorLiveData.postValue(ex)
    }
}