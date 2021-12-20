package com.example.android.unscramble.ui.game

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {
    private var _score = MutableLiveData<Int>()
    private var _currentWordCount = MutableLiveData<Int>()
    private var _currentScrambledWord = MutableLiveData<String>()
    //private var _wordsCanStillShow = allWordsList.toMutableList()
    //private lateinit var currentWord: String
    private var wordsList: MutableList<String> = mutableListOf()
    private lateinit var currentWord: String

    init {
        resetGame()
    }

    public val score: LiveData<Int>
        get() = _score

    public val currentWordCount: LiveData<Int>
        get() = _currentWordCount

    public val currentScrambleWord: LiveData<String>
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
            _currentScrambledWord.value = String(tempWord)
            _currentWordCount.value = _currentWordCount.value?.plus(1)
            wordsList.add(currentWord)
        }
    }

    // In the GameViewModel class, add another method called nextWord().
    // Get the next word from the list and return true if the word count
    //  is less than the MAX_NO_OF_WORDS.
    fun canGetNextWord(): Boolean {
        return if (_currentWordCount.value!! < MAX_NO_OF_WORDS) {
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
        _score.value = _score.value?.plus(SCORE_INCREASE)
    }

    fun resetGame() {
        _score.value = 0
        _currentWordCount.value = 0
        wordsList.clear()
        getNextWord()
    }

}