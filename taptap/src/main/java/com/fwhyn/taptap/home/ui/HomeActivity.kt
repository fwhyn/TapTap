package com.fwhyn.taptap.home.ui

import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.fwhyn.taptap.R
import com.fwhyn.taptap.common.Common
import com.fwhyn.taptap.home.data.MyData
import com.fwhyn.taptap.home.data.MyViewModel

class HomeActivity : AppCompatActivity() {
    private lateinit var viewModel: MyViewModel

    private lateinit var mScoreText: TextView
    private lateinit var mArcadeScoreText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        init(savedInstanceState)
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)

        with(viewModel) {
            if (arcadeScoreUpdated) {
                Common.saveHighestScore(applicationContext, MyData.arcadeScore)
                Log.d(Common.TAG, "saveHighestScore")
                arcadeScoreUpdated = false
            }
        }
    }

    private fun init(savedInstanceState: Bundle?) {
        initData(savedInstanceState)
        initView()
    }

    private fun initData(savedInstanceState: Bundle?) {
        // view model
        viewModel = ViewModelProvider(this)[MyViewModel::class.java]

        with(viewModel) {
            score.observe(this@HomeActivity) { value ->
                mScoreText.text = value.toString()
                if (value > arcadeScore.value!!) {
                    arcadeScore.value = value
                    arcadeScoreUpdated = true
                }
            }
            arcadeScore.observe(this@HomeActivity) { value ->
                mArcadeScoreText.text = value.toString()
            }
        }

        // data
        if (savedInstanceState == null) {
            with(MyData) {
                arcadeScore = Common.getSavedHighestScore(applicationContext)
                Log.d(Common.TAG, "getSavedHighestScore")
                viewModel.arcadeScore.value = arcadeScore
            }
        }

    }

    private fun initView() {
        // text view
        mScoreText = findViewById(R.id.score)
        mArcadeScoreText = findViewById(R.id.arcade_score)

        // button
        val button1 = findViewById<ImageButton>(R.id.tap1)
        button1.setOnClickListener {
            MyData.score++
            viewModel.score.value = MyData.score
        }

        val button2 = findViewById<ImageButton>(R.id.tap2)
        button2.setOnClickListener {
            MyData.score++
            viewModel.score.value = MyData.score
        }
    }
}