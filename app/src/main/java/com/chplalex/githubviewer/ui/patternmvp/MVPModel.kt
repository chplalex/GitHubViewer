package com.chplalex.githubviewer.ui.patternmvp

// используем синглтон, чтобы при пересоздании презентера не терялись данные
object MVPModel {
    private var counter = 0
    private var index = 0

    fun incCounter() {
        counter++

        when (index) {
            0, 1 -> index++
            2 -> index = 0
        }
    }

    fun getCounter() = counter
    fun getIndex() = index
    fun getInstance() = this
}