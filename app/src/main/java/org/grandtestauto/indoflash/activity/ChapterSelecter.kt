package org.grandtestauto.indoflash.activity

import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import org.grandtestauto.indoflash.IndoFlash
import org.grandtestauto.indoflash.R
import org.jetbrains.anko.intentFor

/**
 * User interface for selecting a chapter of word lists to work on.
 *
 * @author Tim Lavers
 */
class ChapterSelecter : android.app.Activity() {
    lateinit private var handler: IndoFlash

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.chapter_selecter)
        val chapterList = findViewById(R.id.chapters_list) as ListView
        handler = application as IndoFlash
        val chapterSpecs = handler.chapterSpecs()
        val spinnerModel = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, chapterSpecs)
        chapterList.adapter = spinnerModel
        chapterList.onItemClickListener = AdapterView.OnItemClickListener { adapterView, view, i, l ->
            handler.setCurrentChapter(chapterSpecs[i])
            startActivity(intentFor<WordListSelecter>())
        }
    }
}