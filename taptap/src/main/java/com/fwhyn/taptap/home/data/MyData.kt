package com.fwhyn.taptap.home.data

object MyData {
    var score: Int = 0
    var highestScore: Int = 0
        get() {
            return if (score > field) {
                field = score
                field
            } else {
                field
            }
        }
}