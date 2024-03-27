package com.omid.glitterwall

import android.annotation.SuppressLint
import com.google.android.material.bottomnavigation.BottomNavigationView

object HomeWidget {
    lateinit var bnv : BottomNavigationView
    @SuppressLint("StaticFieldLeak")
    lateinit var toolbar : androidx.appcompat.widget.Toolbar
}