package com.diego.kotlin.unscramble.ui

import androidx.lifecycle.ViewModel
import com.diego.kotlin.unscramble.data.allWords
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.diego.kotlin.unscramble.data.MAX_NO_OF_WORDS
import com.diego.kotlin.unscramble.data.SCORE_INCREASE
import kotlinx.coroutines.flow.update

class GameViewModel: ViewModel() {
    // Game UI State
    private val _uiState = MutableStateFlow(GameUiState())

    // Copia de seguridad - Backing property to avoid state updates from other classes
    val uiState: StateFlow<GameUiState> = _uiState.asStateFlow() // flujo solo lectura

    private lateinit var currentWord: String
    // Set of words used in the game
    private var usedWords: MutableSet<String> = mutableSetOf()

    var userGuess by mutableStateOf("")
        private set

    var userAction by mutableStateOf(0)
        private set

    init {
        resetGame()
    }

    private fun pickRandomWordAndShuffle(): String {
        // Continue picking up a new randow word until yhou get one
        // that hasn't been used before
        currentWord = allWords.random()
        if (usedWords.contains(currentWord)) {
            return pickRandomWordAndShuffle()
        } else {
            usedWords.add(currentWord)
            return shuffleCurrentWord(currentWord)
        }
    }

    private fun shuffleCurrentWord(word: String): String {
        val tempWord = word.toCharArray()
        // Scramble the word
        tempWord.shuffle()
        while (String(tempWord).equals(word)) {
            tempWord.shuffle()
        }
        return String(tempWord)
    }

    fun resetGame() {
        usedWords.clear()
        _uiState.value = GameUiState(
            currentScrambledWord = pickRandomWordAndShuffle()
        )
    }

    fun updateUserGuess(guessedWord: String) {//palabra propuesta por el usuario
        userGuess = guessedWord
    }

    fun checkUserGuess() {
        if (userGuess.equals(currentWord, ignoreCase = true)) {
            val updatedScore = _uiState.value.score.plus(SCORE_INCREASE)
            updateGameState(updatedScore)
        } else {
            // User's guess is wrong, show an error
            _uiState.update { currentGameUiState ->
                currentGameUiState.copy(
                    isGuessedWordWrong = true,
                    //currentWordCount = currentGameUiState.currentWordCount.inc()
                )
            }
        }
        // Reset user guess
        updateUserGuess("")
    }

    private fun updateGameState(updatedScore: Int) {
        if (usedWords.size == MAX_NO_OF_WORDS) {
            //Last round in the game, update isGameOver to true, don't pick a new word
            _uiState.update { currentGameUiState ->
                currentGameUiState.copy(
                    isGameOver = true,
                    score = updatedScore,
                    isGuessedWordWrong = false
                )
            }
        } else {
            // Normal round in the game
            _uiState.update { currentGameUiState ->
                currentGameUiState.copy(
                    isGuessedWordWrong = false,
                    currentScrambledWord = pickRandomWordAndShuffle(),
                    score = updatedScore,
                    currentWordCount = currentGameUiState.currentWordCount.inc()
                )
            }
        }
    }

    fun skipWord() {
        updateGameState(_uiState.value.score)
        // Reset user guess
        updateUserGuess("")
    }

    // TODO 4.0 - 8. Pasa la puntuación y la cantidad de palabras
    // TODO 4.1 - 8. Para implementar la funcionalidad de omisión

    // TODO 5.0 - 9. Controla la última ronda del juego

}
// TODO 3.0 - 6. Crea la arquitectura de tu IU de Compose










