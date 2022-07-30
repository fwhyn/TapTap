package com.fwhyn.taptap.home.ui

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.fwhyn.taptap.R
import com.fwhyn.taptap.home.data.MyData
import com.fwhyn.taptap.home.data.MyViewModel

class HomeActivity : AppCompatActivity() {
    private lateinit var viewModel: MyViewModel

    private lateinit var mScoreText: TextView
    private lateinit var mHighestScoreText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        init()
    }

    private fun init() {
        initData()
        initView()
    }

    private fun initData() {
        viewModel = ViewModelProvider(this).get(MyViewModel::class.java)
        viewModel.score.observe(this) {value ->
            mScoreText.text = value.toString()
            if (value > viewModel.highestScore.value!!) {
                viewModel.highestScore.value = value
            }
        }
        viewModel.highestScore.observe(this) {value ->
            mHighestScoreText.text = value.toString()
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