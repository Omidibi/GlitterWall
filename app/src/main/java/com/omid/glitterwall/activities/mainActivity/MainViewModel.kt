package com.omid.glitterwall.activities.mainActivity

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    val currentPage = MutableLiveData<Int>()
}