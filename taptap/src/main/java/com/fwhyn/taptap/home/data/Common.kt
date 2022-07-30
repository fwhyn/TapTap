package com.fwhyn.taptap.home.data

import android.content.Context
import android.content.SharedPreferences
import com.fwhyn.taptap.R

class Common {
    companion object {
        const val TAG = "yana_w"

        fun getSavedHighestScore(context: Context): Int {
            return getSharedPreference(context).getInt(context.getString(R.string.highest_score_key), 0)
        }

        fun saveHighestScore(context: Context, value: Int) {
            with (getSharedPreference(context).edit()) {
                putInt(context.getString(R.string.highest_score_key), value)
                apply()
            }
        }

        private fun getSharedPreference(context: Context): SharedPreferences {
            return context.getSharedPreferences(
                context.getString(R.string.preference_data_key),
                Context.MODE_PRIVATE
            )
        }
    }
}