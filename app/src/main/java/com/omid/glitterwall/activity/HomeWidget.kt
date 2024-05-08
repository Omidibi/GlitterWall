package com.omid.glitterwall.activity

import android.annotation.SuppressLint
import com.google.android.material.bottomnavigation.BottomNavigationView

object HomeWidget {
    lateinit var bnv: BottomNavigationView
    var isDataLoaded = false

    @SuppressLint("StaticFieldLeak")
    lateinit var toolbar: androidx.appcompat.widget.Toolbar
}