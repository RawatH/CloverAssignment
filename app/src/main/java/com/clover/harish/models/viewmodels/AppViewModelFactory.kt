package com.clover.harish.models.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.clover.harish.app.CloverApplication



class AppViewModelFactory(val application:CloverApplication) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(CloverApplication::class.java).newInstance(application)
    }

}