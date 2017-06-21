package org.grandtestauto.indoflash

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageButton
import android.widget.ListView

/**
 * User interface for selecting a word list to work on.

 * @author Tim Lavers
 */
class WordListSelecter : Activity() {
    private var application: IndoFlash? = null

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        application = getApplication() as IndoFlash
        setContentView(R.layout.word_list_selecter)
        val listsList = findViewById(R.id.lists_list) as ListView
        val listSpecs = application!!.currentChapter().wordLists()
        val spinnerModel = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, listSpecs)
        listsList.adapter = spinnerModel
        listsList.onItemClickListener = AdapterView.OnItemClickListener { adapterView, view, i, l ->
            val wordListSpec = listSpecs[i]
            application!!.setWordList(wordListSpec)
            val intent = Intent(this@WordListSelecter, WordListDisplay::class.java)
            startActivity(intent)
        }

        val showChaptersButton = findViewById(R.id.show_chapters_button) as ImageButton
        showChaptersButton.setImageResource(R.drawable.ic_chapters)
        showChaptersButton.setOnClickListener {
            val intent = Intent(this@WordListSelecter, ChapterSelecter::class.java)
            startActivity(intent)
        }
    }
}