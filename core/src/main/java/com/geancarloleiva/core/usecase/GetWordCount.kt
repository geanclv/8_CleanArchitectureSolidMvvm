package com.geancarloleiva.core.usecase

import com.geancarloleiva.core.data.Note

class GetWordCount {

    suspend operator fun invoke(note: Note): Int {
        var wordCount = 0
        wordCount += getCountFromString(note.title)
        wordCount += getCountFromString(note.content)
        return wordCount
    }

    private fun getCountFromString(str: String): Int {
        return str.split(" ", "\n")
            .filter {
                it.contains(Regex(".*[a-zA-Z].*"))
            }
            .count()
    }
}