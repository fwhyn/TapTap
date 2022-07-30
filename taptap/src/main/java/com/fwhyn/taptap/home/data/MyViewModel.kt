package com.fwhyn.taptap.home.data

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel : ViewModel() {
    val score: MutableLiveData<Int> = MutableLiveData(0)
    val arcadeScore: MutableLiveData<Int> = MutableLiveData(0)
    var arcadeScoreUpdated: Boolean = false
}