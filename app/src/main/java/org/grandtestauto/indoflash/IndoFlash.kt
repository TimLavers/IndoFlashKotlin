package org.grandtestauto.indoflash

import android.app.Application
import android.content.Context
import android.util.Log
import org.grandtestauto.indoflash.spec.ApplicationSpec
import org.grandtestauto.indoflash.spec.ChapterSpec
import org.grandtestauto.indoflash.spec.WordListSpec
import org.grandtestauto.indoflash.word.Word
import org.grandtestauto.indoflash.word.WordList
import org.grandtestauto.indoflash.word.readFromStream
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import javax.xml.parsers.DocumentBuilderFactory

const val FAVOURITES_FILE_NAME = "favourites"
const val LOG_ID = "IndoFlash:IndoFlash"
const val PREFERENCES_NAME = "IndoFlash"
const val INDONESIAN_TO_ENGLISH_PREFERENCES_KEY = "IndonesianToEnglish"
const val CHAPTER_PREFERENCES_KEY = "Chapter"
const val WORD_LIST_PREFERENCES_KEY = "WordList"

/**
 * The main application. Holds the state such as the list of words
 * currently being studied, whether or not to shuffle the list,
 * and so on.
 *
 * @author Tim Lavers
 */
class IndoFlash : Application() {

    private var wordListSpec: WordListSpec? = null
    lateinit private var wordList: WordList
    private var currentChapter: ChapterSpec? = null
    lateinit private var applicationSpec: ApplicationSpec
    private var showIndonesianFirst = false
    private var showingFavourites = false
    private var shuffle = false

    override fun onCreate() {
        super.onCreate()
        parseSetupFileToApplicationSpec()
        showIndonesianFirst = getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE).getBoolean(INDONESIAN_TO_ENGLISH_PREFERENCES_KEY, false)
        setInitialChapterAndWordList()
    }

    private fun setInitialChapterAndWordList() {
        val storedChapter = getSetting(CHAPTER_PREFERENCES_KEY)
        currentChapter = applicationSpec.chapterForName(storedChapter)
        if (currentChapter == null) currentChapter = applicationSpec.chapterSpecs[0]

        val storedWordList = getSetting(WORD_LIST_PREFERENCES_KEY)
        wordListSpec = currentChapter!!.forName(storedWordList)
        if (wordListSpec == null) {
            wordListSpec = currentChapter!!.wordLists()[0]
        }
        setWordList(wordListSpec!!)
    }

    override fun onTerminate() {
        super.onTerminate()
    }

    fun applicationSpec(): ApplicationSpec {
        return applicationSpec
    }

    fun addRemoveFavourite(word: Word) {
        //If translation is showing first, the word passed in here is the reverse of its stored version in the data files.
        val wordToAddOrRemove = if (showIndonesianFirst()) word else Word(word.definition, word.word)

        val words = readFromFavourites()
        if (showingFavourites) {
            words.remove(wordToAddOrRemove)
        } else {
            words.add(wordToAddOrRemove)
        }
        writeToFavourites(words)
    }

    fun clearFavourites() {
        writeToFavourites(WordList(emptyList<Word>()))
    }

    internal fun storedFavourites(): WordList {
        return readFromFavourites()
    }

    internal fun wordList(): WordList {
        return wordList
    }

    fun toggleShowIndonesianFirst() {
        showIndonesianFirst = !showIndonesianFirst
        storeSetting(INDONESIAN_TO_ENGLISH_PREFERENCES_KEY, showIndonesianFirst)
    }

    private fun storeSetting(key: String, value: Boolean) {
        getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE).edit().putBoolean(key, value).apply()
    }

    private fun storeSetting(key: String, value: String) {
        getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE).edit().putString(key, value).apply()
    }

    private fun getSetting(key: String): String {
        return getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE).getString(key, "")
    }

    fun showIndonesianFirst(): Boolean {
        return showIndonesianFirst
    }

    fun setWordList(wordListSpec: WordListSpec) {
        this.wordListSpec = wordListSpec
        val fileName = wordListSpec.fileName()
        if (fileName.equals(FAVOURITES_FILE_NAME, ignoreCase = true)) {
            wordList = readFromFavourites()
            showingFavourites = true
        } else {
            wordList = readWordList(fileName)
            showingFavourites = false
        }
        storeSetting(WORD_LIST_PREFERENCES_KEY, wordListSpec.title())
    }

    fun setCurrentChapter(chapterSpec: ChapterSpec) {
        this.currentChapter = chapterSpec
        storeSetting(CHAPTER_PREFERENCES_KEY, chapterSpec.title())
    }

    fun currentChapter(): ChapterSpec {
        return currentChapter!!
    }

    fun currentWordList(): WordListSpec {
        return wordListSpec!!
    }

    fun chapterSpecs(): List<ChapterSpec> {
        return applicationSpec.chapterSpecs
    }

    fun shuffle(): Boolean {
        return shuffle
    }

    fun toggleShuffle() {
        shuffle = !shuffle
    }

    fun showingFavourites(): Boolean {
        return showingFavourites
    }

    private fun parseSetupFileToApplicationSpec() {
        val inputStream = resources.openRawResource(R.raw.setup)
        try {
            val builder = DocumentBuilderFactory.newInstance().newDocumentBuilder()
            val document = builder!!.parse(inputStream)
            applicationSpec = ApplicationSpec(document!!)
        } catch (e: Exception) {
            handleError("Error reading setup file", e)
        }
    }

    private fun readWordList(fileName: String): WordList {
        val raw = R.raw::class.java
        try {
            val fileNameIntField = raw.getField(fileName)
            val fileNameInt = fileNameIntField.getInt(null)
            val inputStream = resources.openRawResource(fileNameInt)
            val reader = BufferedReader(InputStreamReader(inputStream, "UTF-8"))
            return readFromStream(reader)
        } catch (e: Throwable) {
            Log.e(LOG_ID, "Problem loading file", e)
            return WordList(emptyList<Word>())
        }
    }

    private fun readFromFavourites(): WordList {
        try {
            val fin = openFileInput(FAVOURITES_FILE_NAME)
            val reader = InputStreamReader(fin, "UTF-8")
            return readFromStream(reader)
        } catch (e: Exception) {
            Log.d(LOG_ID, "Problem when reading from favourites.", e)
            return WordList(emptyList<Word>())
        }
    }

    private fun writeToFavourites(toStore: WordList) {
        try {
            val fout = openFileOutput(FAVOURITES_FILE_NAME, Context.MODE_PRIVATE)
            toStore.store(OutputStreamWriter(fout!!, "UTF-8"))
        } catch (e: Exception) {
            Log.d(LOG_ID, "Could not find file $FAVOURITES_FILE_NAME when writing to favourites.", e)
        }
    }

    private fun handleError(msg: String, problem: Exception) {
        Log.e(LOG_ID, msg, problem)
    }
}