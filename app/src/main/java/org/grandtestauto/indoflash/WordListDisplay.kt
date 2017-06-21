package org.grandtestauto.indoflash

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import java.util.*

class WordListDisplay : Activity() {

    lateinit private var application: IndoFlash
    lateinit private var chapterTitle: TextView
    lateinit private var wordView: TextView
    private var definitionView: TextView? = null
    private var nextButton: Button? = null
    private var indonesianFirstButton: ImageButton? = null
    private var addRemoveFavouriteButton: ImageButton? = null
    private var shuffleButton: ImageButton? = null
    private var showDefinition: Boolean = false
    private var finished = false
    private var currentPosition = 0
    private var wordList: WordList? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_word_list)
        chapterTitle = findViewById(R.id.word_list_title_view) as TextView
        wordView = findViewById(R.id.word_view) as TextView
        definitionView = findViewById(R.id.definition_view) as TextView
        indonesianFirstButton = findViewById(R.id.indonesian_first_button) as ImageButton
        indonesianFirstButton!!.setOnClickListener { toggleIndonesianFirst() }
        nextButton = findViewById(R.id.next_button) as Button
        nextButton!!.setText(R.string.show)
        nextButton!!.setOnClickListener { next() }
        shuffleButton = findViewById(R.id.shuffle_button) as ImageButton
        shuffleButton!!.setOnClickListener { toggleShuffle() }

        val showListsButton = findViewById(R.id.show_word_lists_button) as ImageButton
        showListsButton.setImageResource(R.drawable.ic_lists)
        showListsButton.setOnClickListener {
            val intent = Intent(this@WordListDisplay, WordListSelecter::class.java)
            startActivity(intent)
        }

        addRemoveFavouriteButton = findViewById(R.id.add_to_favourites_button) as ImageButton
        addRemoveFavouriteButton!!.setOnClickListener { addRemoveFavourite() }
        application = getApplication() as IndoFlash
        doSetup()
    }

    private fun loadWordList() {
        wordList = application.wordList()
        if (application.shuffle()) {
            val toShuffle = LinkedList<Word>()
            toShuffle.addAll(wordList!!.words())
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
        val inflater = menuInflater
        inflater.inflate(R.menu.actions, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle presses on the action bar items
        when (item.itemId) {
            R.id.action_info -> {
                showFeedback()
                return true
            }
            R.id.action_help -> {
                showHelp()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    private fun showFeedback() {
        val builder = AlertDialog.Builder(this)
        // Add the buttons
        builder.setPositiveButton(R.string.ok) { dialog, id ->
            // User clicked OK button
        }
        builder.setTitle(applicationContext.resources.getString(R.string.info))
        builder.setMessage(applicationContext.resources.getString(R.string.acknowledgements))
        // Create the AlertDialog
        val dialog = builder.create()
        dialog.show()
    }

    private fun showHelp() {
        val builder = AlertDialog.Builder(this)
        // Add the buttons
        builder.setPositiveButton(R.string.ok) { dialog, id ->
            // User clicked OK button
        }
        builder.setTitle(applicationContext.resources.getString(R.string.help))
        builder.setMessage(applicationContext.resources.getString(R.string.help_text))
        // Create the AlertDialog
        val dialog = builder.create()
        dialog.show()
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
            indonesianFirstButton!!.setImageResource(R.drawable.ic_down_arrow)
        } else {
            resourceId = R.string.english_first
            indonesianFirstButton!!.setImageResource(R.drawable.ic_up_arrow)
        }
        val description = indonesianFirstButton!!.context.resources.getText(resourceId)
        indonesianFirstButton!!.contentDescription = description
        if (showToast)
            Toast.makeText(applicationContext, description, Toast.LENGTH_SHORT).show()
    }

    private fun toggleShuffle() {
        application.toggleShuffle()
        loadWordList()
        showFirstWord()
        setupShuffleButton()
        val msgId = if (application.shuffle()) R.string.shuffle_on_toast else R.string.shuffle_off_toast
        Toast.makeText(applicationContext, msgId, Toast.LENGTH_SHORT).show()
    }

    private fun setupShuffleButton() {
        var resourceId = R.string.unshuffle
        if (application.shuffle()) {
            shuffleButton!!.setImageResource(R.drawable.ic_shuffle)
        } else {
            shuffleButton!!.setImageResource(R.drawable.ic_unshuffle)
            resourceId = R.string.shuffle
        }
        val description = shuffleButton!!.context.resources.getText(resourceId)
        shuffleButton!!.contentDescription = description
    }

    private fun doSetup() {
        chapterTitle.text = application.currentWordList().title()
        setupIndonesianFirstButton(false)
        setupShuffleButton()
        setupFavouritesButton()
        loadWordList()
        showFirstWord()
    }

    private fun showFirstWord() {
        if (application.showingFavourites() && wordList!!.words().isEmpty()) {
            showForEmptyFavourites()
        } else {
            wordView.text = getWord(0).word
            definitionView!!.text = ""
            showDefinition = true
            currentPosition = 0
            nextButton!!.isClickable = true
            addRemoveFavouriteButton!!.isClickable = true
        }
    }

    private fun showForEmptyFavourites() {
        finished = true
        wordView.setText(R.string.favourites_is_empty)
        nextButton!!.text = ""
        nextButton!!.isClickable = false
        addRemoveFavouriteButton!!.isClickable = false
    }

    private fun setupFavouritesButton() {
        var resourceId = R.string.add_to_favourites
        if (application.showingFavourites()) {
            resourceId = R.string.remove_from_favourites
            addRemoveFavouriteButton!!.setImageResource(R.drawable.ic_remove_from_favourites)
        } else {
            addRemoveFavouriteButton!!.setImageResource(R.drawable.ic_add_to_favourites)
        }
        val description = addRemoveFavouriteButton!!.context.resources.getText(resourceId)
        addRemoveFavouriteButton!!.contentDescription = description
    }

    private fun addRemoveFavourite() {
        application.addRemoveFavourite(getWord(currentPosition))
        if (application.showingFavourites()) {
            Toast.makeText(applicationContext, R.string.removed_from_favourites_toast, Toast.LENGTH_SHORT).show()
            if (showDefinition) {
                //Removing a word is like activating Show but we don't want to see the definition of the word just removed.
                showDefinition = false
            }
            //The user may now be at the end of the list.
            if (currentPosition == wordList!!.words().size - 1) {
                finished = true
                if (application.storedFavourites().words().isEmpty()) {
                    showForEmptyFavourites()
                } else {
                    nextButton!!.setText(R.string.repeat)
                }
            } else {
                next()
            }
        } else {
            Toast.makeText(applicationContext, R.string.added_to_favourites_toast, Toast.LENGTH_SHORT).show()
        }
    }

    private fun getWord(index: Int): Word {
        val word = wordList!!.words()[index]
        if (application.showIndonesianFirst()) return word
        return Word(word.definition, word.word)
    }

    private operator fun next() {
        if (finished) {
            currentPosition = 0
            wordView.text = getWord(currentPosition).word
            definitionView!!.text = ""
            nextButton!!.setText(R.string.show)
            showDefinition = true
            finished = false
            return
        }
        if (showDefinition) {
            definitionView!!.text = getWord(currentPosition).definition
            nextButton!!.setText(R.string.next)
            showDefinition = false
            if (currentPosition == wordList!!.words().size - 1) {
                finished = true
                nextButton!!.setText(R.string.repeat)
            }
        } else {
            currentPosition++
            wordView.text = getWord(currentPosition).word
            definitionView!!.text = ""
            nextButton!!.setText(R.string.show)
            showDefinition = true
        }
    }
}