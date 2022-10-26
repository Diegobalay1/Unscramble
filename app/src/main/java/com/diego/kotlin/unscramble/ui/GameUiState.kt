package com.diego.kotlin.unscramble.ui

data class GameUiState(
    val currentScrambledWord: String = "",
    val isGuessedWordWrong: Boolean = false,
    val score: Int = 0,
    val currentWordCount: Int = 0,
    val isGameOver: Boolean = false
)

// TODO 6.0 - 9. Muestra el diálogo de finalización del juego
