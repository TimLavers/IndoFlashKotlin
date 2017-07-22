package org.grandtestauto.indoflash.activity

import android.app.Activity
import android.os.Bundle
import android.widget.AdapterView.*
import android.widget.ArrayAdapter
import org.grandtestauto.indoflash.IndoFlash
import org.grandtestauto.indoflash.R
import org.jetbrains.anko.intentFor

import kotlinx.android.synthetic.main.word_list_selecter.*

/**
 * User interface for selecting a word list to work on.

 * @author Tim Lavers
 */
class WordListSelecter : Activity() {

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val application = application as IndoFlash
        setContentView(R.layout.word_list_selecter)
        val listSpecs = application.currentChapter().wordLists()
        listsList.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, listSpecs)
        listsList.onItemClickListener = OnItemClickListener { _, _, i, _ ->
            application.setWordList(listSpecs[i])
            startActivity(intentFor<WordListDisplay>())
        }

        showChaptersButton.setImageResource(R.drawable.ic_chapters)
        showChaptersButton.setOnClickListener {
            startActivity(intentFor<ChapterSelecter>())
        }
    }
}