package com.indigoag.android.homework.component

import android.R
import android.content.Context

object ProgressFull {

    var progressBar : ProgressBarFull? = null

    fun crossProgressFull(context: Context){
        progressBar = ProgressBarFull(
            context,
            R.style.Theme_DeviceDefault_NoActionBar_Fullscreen
        )
        progressBar?.apply {
            this.show()
        }
    }


    fun hideProgressFull(){
        progressBar?.apply {
            this.dismiss()
            progressBar = null
        }
    }
}