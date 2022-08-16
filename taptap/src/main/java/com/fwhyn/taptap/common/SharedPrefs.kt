package com.fwhyn.taptap.common

import android.content.Context
import android.content.SharedPreferences

object SharedPrefs {
    private var sharedPrefs: SharedPreferences? = null

    private val prefsEditor: SharedPreferences.Editor?
        get() {
            return sharedPrefs?.edit()
        }

    var arcadeHighestScore: Long
        get() {
            return try {
                sharedPrefs?.getLong(Constant.ARCADE_HIGHEST_SCORE_KEY, 0) ?: 0
            } catch (e: Exception) {
                e.printStackTrace()
                prefsEditor?.remove(Constant.ARCADE_HIGHEST_SCORE_KEY)?.apply()
                0
            }
        }
        set(value) {
            prefsEditor?.putLong(Constant.ARCADE_HIGHEST_SCORE_KEY, value)?.apply()
        }


    var beastHighestScore: Int
        get() {
            return try {
                sharedPrefs?.getInt(Constant.BEAST_HIGHEST_SCORE_KEY, 0) ?: 0
            } catch (e: Exception) {
                e.printStackTrace()
                prefsEditor?.remove(Constant.BEAST_HIGHEST_SCORE_KEY)?.apply()
                0
            }
        }
        set(value) {
            prefsEditor?.putInt(Constant.BEAST_HIGHEST_SCORE_KEY, value)?.apply()
        }

    var rockyHighestScore: Int
        get() {
            return try {
                sharedPrefs?.getInt(Constant.ROCKY_HIGHEST_SCORE_KEY, 0) ?: 0
            } catch (e: Exception) {
                e.printStackTrace()
                prefsEditor?.remove(Constant.ROCKY_HIGHEST_SCORE_KEY)?.apply()
                0
            }
        }
        set(value) {
            prefsEditor?.putInt(Constant.ROCKY_HIGHEST_SCORE_KEY, value)?.apply()
        }

    fun getSharedPrefs(context: Context) {
        if (sharedPrefs == null) {
            sharedPrefs = context.getSharedPreferences(
                Constant.PREFS_DATA_KEY,
                Context.MODE_PRIVATE
            )
        }
    }
}