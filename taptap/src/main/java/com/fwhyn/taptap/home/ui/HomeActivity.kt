package com.fwhyn.taptap.home.ui

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.fwhyn.taptap.R
import com.fwhyn.taptap.home.data.Common
import com.fwhyn.taptap.home.data.MyData
import com.fwhyn.taptap.home.data.MyViewModel

class HomeActivity : AppCompatActivity() {
    private lateinit var viewModel: MyViewModel

    private lateinit var mScoreText: TextView
    private lateinit var mHighestScoreText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        init(savedInstanceState)
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)

        with(viewModel) {
            if (highestScoreUpdated) {
                Common.saveHighestScore(applicationContext, MyData.highestScore)
                Log.d(Common.TAG, "saveHighestScore")
                highestScoreUpdated = false
            }
        }
    }

    private fun init(savedInstanceState: Bundle?) {
        initData(savedInstanceState)
        initView()
    }

    private fun initData(savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(MyViewModel::class.java)

        with(viewModel) {
            score.observe(this@HomeActivity) { value ->
                mScoreText.text = value.toString()
                if (value > highestScore.value!!) {
                    highestScore.value = value
                    highestScoreUpdated = true
                }
            }
            highestScore.observe(this@HomeActivity) { value ->
                mHighestScoreText.text = value.toString()
            }
        }

        if (savedInstanceState == null) {
            with(MyData) {
                highestScore = Common.getSavedHighestScore(applicationContext)
                Log.d(Common.TAG, "getSavedHighestScore")
                viewModel.highestScore.value = highestScore
            }
        }

    }

    private fun initView() {
        mScoreText = findViewById(R.id.score)
        mHighestScoreText = findViewById(R.id.highest_score)

        val button1 = findViewById<ImageView>(R.id.tap_button1)
        button1.setOnClickListener {
            MyData.score++
            viewModel.score.value = MyData.score
        }

        val button2 = findViewById<ImageView>(R.id.tap_button2)
        button2.setOnClickListener {
            MyData.score++
            viewModel.score.value = MyData.score
        }
    }
}