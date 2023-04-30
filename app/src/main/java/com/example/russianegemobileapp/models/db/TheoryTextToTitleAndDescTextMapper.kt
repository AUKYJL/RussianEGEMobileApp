package com.example.russianegemobileapp.models.db

import android.util.Log
import com.example.russianegemobileapp.models.Constants
import com.example.russianegemobileapp.models.db.local.theory.Theory



class TheoryTextToTitleAndDescTextMapper{
    /**
     * Преобразует полученный текст теории о задании из базы данных в массив стрингов,
     * где в одном массиве содержатся все тайтлы этой теории, а в другом - описания
     */

    fun execute(theory: Theory): List<ArrayList<String>> {
        val text = theory.theoryText
        val lines = text.split("\n")
        val titles = getTitles(lines)
        val descs = getDescs(lines)
        Log.d("MyLog","execute")
        return listOf(titles,descs)
    }

    private fun getTitles(textLines: List<String>): ArrayList<String> {
        val result = ArrayList<String>()
        textLines.forEach {
            if (it.contains(Constants.SYMBOLS_FOR_TITLE)) {
                result.add(it.drop(Constants.SYMBOLS_FOR_TITLE.length).trim())
            }
        }
        Log.d("MyLog","execute1")
        return result
    }

    private fun getDescs(textLines: List<String>): ArrayList<String> {
        val result = ArrayList<String>()
        var textForOneTextView = ""
        var id = -1
        textLines.forEach {
            if (it.contains(Constants.SYMBOLS_FOR_TITLE)) {
                if (id >= 0) {
                    result.add(textForOneTextView)
                    textForOneTextView = ""
                }
                id++
            }
            if (!it.contains(Constants.SYMBOLS_FOR_TITLE) && !it.isEmpty() && id >= 0) {
                textForOneTextView += "${it}\n"

            }
        }
        result.add(textForOneTextView)
        Log.d("MyLog","execute2")
        return result
    }
}