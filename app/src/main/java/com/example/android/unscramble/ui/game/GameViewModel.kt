package com.example.android.unscramble.ui.game

class GameViewModel {
    private var _score = 0
    private var _currentWordCount = 0
    private var _currentScrambledWord = "test"
    private var _wordsCanStillShow = ArrayList(allWordsList)
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

    private fun getNextWord() {
        currentWord = _wordsCanStillShow.random()
        // remove the random element so it isn't shown again
        _wordsCanStillShow.remove(currentWord)

        val tempWordAsCharArr = currentWord.toCharArray()
        var newScrambled = tempWordAsCharArr.shuffle().toString()
        // if the scrambled word is the same as the temp word, shuffle it again until it isn't
        while (newScrambled == currentWord) {
            // shuffle the new word until it isn't the same as the original word
            newScrambled = tempWordAsCharArr.shuffle().toString()
        }
        _currentScrambledWord = newScrambled
        ++_currentWordCount
    }

    // In the GameViewModel class, add another method called nextWord().
    // Get the next word from the list and return true if the word count
    //  is less than the MAX_NO_OF_WORDS.
    private fun nextWord(): Boolean {
        getNextWord()
        return (_currentWordCount < MAX_NO_OF_WORDS)
    }

}