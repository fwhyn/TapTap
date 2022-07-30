package com.fwhyn.taptap.home.data

object MyData {
    var score: Int = 0
    var arcadeScore: Int = 0
        get() {
            return if (score > field) {
                field = score
                field
            } else {
                field
            }
        }
}