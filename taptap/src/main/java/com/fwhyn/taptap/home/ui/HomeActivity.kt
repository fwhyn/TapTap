package com.fwhyn.taptap.home.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.fwhyn.taptap.R

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        supportActionBar?.elevation = 0F
    }
}