package com.example.madlevel2task2.data

data class Question(var questionText: String) {
    companion object {
        val QUESTIONS = arrayOf(
            "A 'val' and 'var' are the same.",
            "Mobile Application Development grants 12 ECTS.",
            "A Unit in Kotlin corresponds to void in Java.",
            "In Kotlin 'when' replaces 'switch' operator in Java",
            "Adapters are awesome",
            "I will pass this course"
        )

        val ANSWERS = arrayOf(
            true,
            false,
            false,
            true,
            true,
            true
        )
    }
}