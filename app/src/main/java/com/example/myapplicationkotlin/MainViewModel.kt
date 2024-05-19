package com.example.myapplicationkotlin

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    val liveData = MutableLiveData<String>()

    init {

    }
}