package com.diego.kotlin.unscramble.ui

data class GameUiState(
    val currentScrambledWord: String = "",
    val isGuessedWordWrong: Boolean = false
)
