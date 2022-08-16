package com.fwhyn.taptap.home.ui

import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.fwhyn.taptap.R
import com.fwhyn.taptap.common.SharedPrefs
import com.fwhyn.taptap.home.data.MyData
import com.fwhyn.taptap.home.data.MyViewModel

class HomeActivity : AppCompatActivity() {
    private lateinit var viewModel: MyViewModel

    private lateinit var arcadeScoreText: TextView
    private lateinit var beastScoreText: TextView
    private lateinit var rockyScoreText: TextView

    private lateinit var arcadeHighestScoreText: TextView
    private lateinit var beastHighestScoreText: TextView
    private lateinit var rockyHighestScoreText: TextView

    private lateinit var beastTimerText: TextView
    private lateinit var rockyTimerText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        init(savedInstanceState)
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)

        saveData()
    }

    private fun init(savedInstanceState: Bundle?) {
        initData(savedInstanceState)
        initView()
    }

    private fun initData(savedInstanceState: Bundle?) {
        // view model
        viewModel = ViewModelProvider(this)[MyViewModel::class.java]

        with(viewModel) {
            // arcade
            arcadeScore.observe(this@HomeActivity) { value ->
                arcadeScoreText.text = value.toString()
            }
            arcadeHighestScore.observe(this@HomeActivity) { value ->
                arcadeHighestScoreText.text = value.toString()
            }

            // beast
            beastScore.observe(this@HomeActivity) { value ->
                beastScoreText.text = value.toString()
            }
            beastHighestScore.observe(this@HomeActivity) { value ->
                beastHighestScoreText.text = value.toString()
            }
            beastTimeCount.observe(this@HomeActivity) { value ->
                beastTimerText.text = value.toString()
            }

            // rocky
            rockyScore.observe(this@HomeActivity) { value ->
                rockyScoreText.text = value.toString()
            }
            rockyHighestScore.observe(this@HomeActivity) { value ->
                rockyHighestScoreText.text = value.toString()
            }
            rockyTimeCount.observe(this@HomeActivity) { value ->
                rockyTimerText.text = value.toString()
            }
        }

        // data
        if (savedInstanceState == null) {
            initData()
            getData()
        }
    }

    private fun initView() {
        // text view
        arcadeScoreText = findViewById(R.id.arcade_score)
        beastScoreText = findViewById(R.id.beast_score)
        rockyScoreText = findViewById(R.id.rocky_score)

        arcadeHighestScoreText = findViewById(R.id.arcade_highest_score)
        beastHighestScoreText = findViewById(R.id.beast_highest_score)
        rockyHighestScoreText = findViewById(R.id.rocky_highest_score)

        beastTimerText = findViewById(R.id.beast_timer)
        rockyTimerText = findViewById(R.id.rocky_timer)

        // button
        val button1 = findViewById<ImageButton>(R.id.tap1)
        button1.setOnClickListener {
            scoreIt()
        }

        val button2 = findViewById<ImageButton>(R.id.tap2)
        button2.setOnClickListener {
            scoreIt()
        }
    }

    private fun initData() {
        SharedPrefs.getSharedPrefs(applicationContext)
    }

    private fun getData() {
        with(MyData) {
            arcadeHighestScore = SharedPrefs.arcadeHighestScore
            beastHighestScore = SharedPrefs.beastHighestScore
            rockyHighestScore = SharedPrefs.rockyHighestScore

            viewModel.arcadeHighestScore.value = arcadeHighestScore
            viewModel.beastHighestScore.value = beastHighestScore
            viewModel.rockyHighestScore.value = rockyHighestScore
        }
    }

    private fun saveData() {
        with(viewModel) {
            if (arcadeScoreUpdate) {
                SharedPrefs.arcadeHighestScore = MyData.arcadeHighestScore
                arcadeScoreUpdate = false
            }

            if (beastScoreUpdate) {
                SharedPrefs.beastHighestScore = MyData.beastHighestScore
                beastScoreUpdate = false
            }

            if (rockyScoreUpdate) {
                SharedPrefs.rockyHighestScore = MyData.rockyHighestScore
                rockyScoreUpdate = false
            }
        }
    }

    private fun scoreIt() {
        with (viewModel) {
            // arcade score
            setArcadeScore()

            // beast score
            setBeastScore()

            // rocky score
            setRockyScore()
        }
    }
}