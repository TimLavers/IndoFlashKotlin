package org.grandtestauto.indoflash.activity

import android.app.Activity
import android.widget.AdapterView.*
import android.widget.ArrayAdapter
import android.widget.ImageButton
import android.widget.ListView
import org.grandtestauto.indoflash.IndoFlash
import org.grandtestauto.indoflash.R
import org.jetbrains.anko.intentFor

/**
 * User interface for selecting a word list to work on.

 * @author Tim Lavers
 */
class WordListSelecter : Activity() {

    public override fun onCreate(savedInstanceState: android.os.Bundle?) {
        super.onCreate(savedInstanceState)
        val application = getApplication() as IndoFlash
        setContentView(R.layout.word_list_selecter)
        val listsList = findViewById(R.id.lists_list) as ListView
        val listSpecs = application.currentChapter().wordLists()
        val spinnerModel = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, listSpecs)
        listsList.adapter = spinnerModel
        listsList.onItemClickListener = OnItemClickListener { _, _, i, _ ->
            application.setWordList(listSpecs[i])
            startActivity(intentFor<WordListDisplay>())
        }

        val showChaptersButton = findViewById(R.id.show_chapters_button) as ImageButton
        showChaptersButton.setImageResource(R.drawable.ic_chapters)
        showChaptersButton.setOnClickListener {
            startActivity(intentFor<ChapterSelecter>())
        }
    }
}