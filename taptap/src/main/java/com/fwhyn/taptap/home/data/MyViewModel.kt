package com.fwhyn.taptap.home.data

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fwhyn.taptap.common.Constant
import com.fwhyn.taptap.common.CustomTimer

class MyViewModel : ViewModel() {
    val arcadeScore: MutableLiveData<Long> = MutableLiveData(0)
    val beastScore: MutableLiveData<Int> = MutableLiveData(0)
    val rockyScore: MutableLiveData<Int> = MutableLiveData(0)

    val arcadeHighestScore: MutableLiveData<Long> = MutableLiveData(0)
    val beastHighestScore: MutableLiveData<Int> = MutableLiveData(0)
    val rockyHighestScore: MutableLiveData<Int> = MutableLiveData(0)

    val beastTimeCount: MutableLiveData<Int> = MutableLiveData(0)
    val rockyTimeCount: MutableLiveData<Int> = MutableLiveData(0)

    var arcadeScoreUpdate: Boolean = false
    var beastScoreUpdate: Boolean = false
    var rockyScoreUpdate: Boolean = false

    fun setArcadeScore() {
        MyData.arcadeScore++
        arcadeScore.value = MyData.arcadeScore

        if (MyData.arcadeScore > MyData.arcadeHighestScore) {
            MyData.arcadeHighestScore = MyData.arcadeScore
            arcadeHighestScore.value = MyData.arcadeHighestScore
            arcadeScoreUpdate = true
        }
    }

    fun setBeastScore() {
        beastTimer()
        if (!beastTime.isFinished) {
            MyData.beastScore++
            beastScore.value = MyData.beastScore
        }
    }

    fun setRockyScore() {
        rockyTimer()

        if (!rockyTime.isFinished) {
            MyData.rockyScore++
            rockyScore.value = MyData.rockyScore
        }
    }

    private val beastTime = object : CustomTimer(viewModelScope, Constant.MIL_BEAST_FINISH, Constant
        .MIL_BEAST_INTERVAL) {
            override fun onTick(remainingTime: Long) {
                beastTimeCount.value = (remainingTime/Constant.MIL_BEAST_INTERVAL).toInt()
            }

            override fun onFinish() {
                if (MyData.beastScore > MyData.beastHighestScore) {
                    MyData.beastHighestScore = MyData.beastScore
                    beastHighestScore.value = MyData.beastHighestScore
                    beastScoreUpdate = true
                }

                MyData.beastScore = 0
                beastScore.value = MyData.beastScore
                beastTimeCount.value = 0
            }
        }

    private val rockyTime = object : CustomTimer(viewModelScope, Constant.MIL_ROCKY_FINISH, Constant
        .MIL_ROCKY_INTERVAL) {
            override fun onTick(remainingTime: Long) {
                rockyTimeCount.value = (remainingTime/Constant.MIL_ROCKY_INTERVAL).toInt()
            }

            override fun onFinish() {
                if (MyData.rockyScore > MyData.rockyHighestScore) {
                    MyData.rockyHighestScore = MyData.rockyScore
                    rockyHighestScore.value = MyData.rockyHighestScore
                    rockyScoreUpdate = true
                }

                MyData.rockyScore = 0
                rockyScore.value = MyData.rockyScore
                rockyTimeCount.value = 0
            }
        }

    private fun beastTimer() {
        if (beastTime.isFinished) {
            beastTime.start()
        } else {
            beastTime.cancel()
            beastTime.start()
        }
    }

    private fun rockyTimer() {
        if (rockyTime.isFinished) {
            rockyTime.start()
        }
    }
}