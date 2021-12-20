package com.example.android.unscramble.ui.game

import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {
    private var _score = 0
    private var _currentWordCount = 0
    private var _currentScrambledWord = "test"
    //private var _wordsCanStillShow = allWordsList.toMutableList()
    //private lateinit var currentWord: String
    private var wordsList: MutableList<String> = mutableListOf()
    private lateinit var currentWord: String

    init {
        getNextWord()
    }

    public val score: Int
        get() = _score

    public val currentWordCount: Int
        get() = _currentWordCount

    public val currentScrambleWord: String
        get() = _currentScrambledWord

//    private fun getNextWord() {
//        currentWord = _wordsCanStillShow.random()
//        // remove the random element so it isn't shown again
//        _wordsCanStillShow.remove(currentWord)
//
//        val tempWordAsCharArr = currentWord.toCharArray()
//        var newScrambled = tempWordAsCharArr.shuffle().toString()
//        // if the scrambled word is the same as the temp word, shuffle it again until it isn't
//        while (newScrambled == currentWord) {
//            // shuffle the new word until it isn't the same as the original word
//            newScrambled = tempWordAsCharArr.shuffle().toString()
//        }
//        _currentScrambledWord = newScrambled
//        ++_currentWordCount
//    }

    private fun getNextWord() {
        currentWord = allWordsList.random()
        val tempWord = currentWord.toCharArray()
        tempWord.shuffle()

        while (String(tempWord).equals(currentWord, false)) {
            tempWord.shuffle()
        }
        if (wordsList.contains(currentWord)) {
            getNextWord()
        } else {
            _currentScrambledWord = String(tempWord)
            ++_currentWordCount
            wordsList.add(currentWord)
        }
    }

    // In the GameViewModel class, add another method called nextWord().
    // Get the next word from the list and return true if the word count
    //  is less than the MAX_NO_OF_WORDS.
    fun canGetNextWord(): Boolean {
        return if (_currentWordCount < MAX_NO_OF_WORDS) {
            getNextWord()
            true
        } else {
            false
        }
    }

    fun isWordCorrect(guess: String): Boolean {
        return guess == currentWord
    }

    fun increaseScore() {
        _score += SCORE_INCREASE
    }

    fun resetGame() {
        _score = 0
        _currentWordCount = 0
        wordsList.clear()
        getNextWord()
    }

}