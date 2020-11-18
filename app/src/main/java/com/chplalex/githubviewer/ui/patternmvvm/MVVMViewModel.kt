package com.chplalex.githubviewer.ui.patternmvvm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MVVMViewModel : ViewModel() {

    private var counter = 0

    val liveData01: MutableLiveData<String> = MutableLiveData<String>().apply {
        value = counter.toString()
    }
    val liveData02: MutableLiveData<String> = MutableLiveData<String>().apply {
        value = counter.toString()
    }
    val liveData03: MutableLiveData<String> = MutableLiveData<String>().apply {
        value = counter.toString()
    }

    private var currentLiveData = liveData01

    fun fabClicked() {
        counter++
        when (currentLiveData) {
            liveData01 -> currentLiveData = liveData02
            liveData02 -> currentLiveData = liveData03
            liveData03 -> currentLiveData = liveData01
        }
        currentLiveData.value = counter.toString()
    }
}