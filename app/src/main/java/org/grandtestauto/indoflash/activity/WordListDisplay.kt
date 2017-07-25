package org.grandtestauto.indoflash.activity

import android.app.Activity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_word_list.*
import org.grandtestauto.indoflash.IndoFlash
import org.grandtestauto.indoflash.R
import org.grandtestauto.indoflash.word.Word
import org.grandtestauto.indoflash.word.WordList
import org.jetbrains.anko.alert
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.toast
import org.jetbrains.anko.yesButton
import java.util.*

class WordListDisplay : Activity() {

    lateinit private var application: IndoFlash
    lateinit private var wordList: WordList
    private var showDefinition: Boolean = false
    private var finished = false
    private var currentPosition = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_word_list)
        indonesianFirstButton.setOnClickListener { toggleIndonesianFirst() }
        nextButton.setText(R.string.show)
        nextButton.setOnClickListener { next() }
        shuffleButton.setOnClickListener { toggleShuffle() }

        showWordListsButton.setImageResource(R.drawable.ic_lists)
        showWordListsButton.setOnClickListener {
            startActivity(intentFor<WordListSelecter>())
        }

        addOrRemoveFavouriteButton.setOnClickListener { addRemoveFavourite() }
        application = getApplication() as IndoFlash
        doSetup()
    }

    private fun loadWordList() {
        wordList = application.wordList()
        if (application.shuffle()) {
            val toShuffle = LinkedList<Word>()
            toShuffle.addAll(wordList.words())
            Collections.shuffle(toShuffle)
            wordList = WordList(toShuffle)
        }
    }

    override fun onResume() {
        super.onResume()
        doSetup()
    }

    override fun onRestart() {
        super.onRestart()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.actions, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle presses on the action bar items
        when (item.itemId) {
            R.id.action_info -> {
                alert(R.string.acknowledgements, R.string.info) {yesButton { }}.show()
                return true
            }
            R.id.action_help -> {
                alert(R.string.help_text, R.string.help) { yesButton { } }.show()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    private fun toggleIndonesianFirst() {
        application.toggleShowIndonesianFirst()
        showDefinition = false
        showFirstWord()
        setupIndonesianFirstButton(true)
    }

    private fun setupIndonesianFirstButton(showToast: Boolean) {
        var resourceId = R.string.indonesian_first
        if (application.showIndonesianFirst()) {
            indonesianFirstButton.setImageResource(R.drawable.ic_down_arrow)
        } else {
            resourceId = R.string.english_first
            indonesianFirstButton.setImageResource(R.drawable.ic_up_arrow)
        }
        val description = indonesianFirstButton.context.resources.getText(resourceId)
        indonesianFirstButton.contentDescription = description
        if (showToast)
            toast(description)
    }

    private fun toggleShuffle() {
        application.toggleShuffle()
        loadWordList()
        showFirstWord()
        setupShuffleButton()
        //todo simplify this if
        val msgId = if (application.shuffle()) R.string.shuffle_on_toast else R.string.shuffle_off_toast
        toast(msgId)
    }

    private fun setupShuffleButton() {
        var resourceId = R.string.unshuffle
        if (application.shuffle()) {
            shuffleButton.setImageResource(R.drawable.ic_shuffle)
        } else {
            shuffleButton.setImageResource(R.drawable.ic_unshuffle)
            resourceId = R.string.shuffle
        }
        val description = shuffleButton.context.resources.getText(resourceId)
        shuffleButton.contentDescription = description
    }

    private fun doSetup() {
        wordListTitleView.text = application.currentWordList().title
        setupIndonesianFirstButton(false)
        setupShuffleButton()
        setupFavouritesButton()
        loadWordList()
        showFirstWord()
    }

    private fun showFirstWord() {
        if (application.showingFavourites() && wordList.words().isEmpty()) {
            showForEmptyFavourites()
        } else {
            wordView.text = getWord(0).word
            definitionView.text = ""
            showDefinition = true
            currentPosition = 0
            nextButton.isClickable = true
            addOrRemoveFavouriteButton.isClickable = true
        }
    }

    private fun showForEmptyFavourites() {
        finished = true
        wordView.setText(R.string.favourites_is_empty)
        nextButton.text = ""
        nextButton.isClickable = false
        addOrRemoveFavouriteButton.isClickable = false
    }

    private fun setupFavouritesButton() {
        var resourceId = R.string.add_to_favourites
        if (application.showingFavourites()) {
            resourceId = R.string.remove_from_favourites
            addOrRemoveFavouriteButton.setImageResource(R.drawable.ic_remove_from_favourites)
        } else {
            addOrRemoveFavouriteButton.setImageResource(R.drawable.ic_add_to_favourites)
        }
        val description = addOrRemoveFavouriteButton.context.resources.getText(resourceId)
        addOrRemoveFavouriteButton.contentDescription = description
    }

    private fun addRemoveFavourite() {
        application.addRemoveFavourite(getWord(currentPosition))
        if (application.showingFavourites()) {
            toast(R.string.removed_from_favourites_toast)
            if (showDefinition) {
                //Removing a word is like activating Show but we don't want to see the definition of the word just removed.
                showDefinition = false
            }
            //The user may now be at the end of the list.
            if (currentPosition == wordList.words().size - 1) {
                finished = true
                if (application.storedFavourites().words().isEmpty()) {
                    showForEmptyFavourites()
                } else {
                    nextButton.setText(R.string.repeat)
                }
            } else {
                next()
            }
        } else {
            toast(R.string.added_to_favourites_toast)
        }
    }

    private fun getWord(index: Int): Word {
        val word = wordList.words()[index]
        if (application.showIndonesianFirst()) return word
        return Word(word.definition, word.word)
    }

    private operator fun next() {
        if (finished) {
            currentPosition = 0
            wordView.text = getWord(currentPosition).word
            definitionView.text = ""
            nextButton.setText(R.string.show)
            showDefinition = true
            finished = false
            return
        }
        if (showDefinition) {
            definitionView.text = getWord(currentPosition).definition
            nextButton.setText(R.string.next)
            showDefinition = false
            if (currentPosition == wordList.words().size - 1) {
                finished = true
                nextButton.setText(R.string.repeat)
            }
        } else {
            currentPosition++
            wordView.text = getWord(currentPosition).word
            definitionView.text = ""
            nextButton.setText(R.string.show)
            showDefinition = true
        }
    }
}