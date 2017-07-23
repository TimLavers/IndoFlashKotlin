package org.grandtestauto.indoflash.activity


import android.app.Activity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import org.grandtestauto.indoflash.IndoFlash
import org.grandtestauto.indoflash.R
import org.jetbrains.anko.intentFor

import kotlinx.android.synthetic.main.chapter_selecter.*

/**
 * User interface for selecting a chapter of word lists to work on.
 *
 * @author Tim Lavers
 */
class ChapterSelecter : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.chapter_selecter)
        val handler = application as IndoFlash
        val chapterSpecs = handler.chapterSpecs()
        chapters_list.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, chapterSpecs)
        chapters_list.onItemClickListener = AdapterView.OnItemClickListener { _, _, i, _ ->
            handler.setCurrentChapter(chapterSpecs[i])
            startActivity(intentFor<WordListSelecter>())
        }
    }
}