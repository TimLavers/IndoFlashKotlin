package org.grandtestauto.indoflash

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView

/**
 * User interface for selecting a chapter of word lists to work on.
 *
 * @author Tim Lavers
 */
class ChapterSelecter : Activity() {
    private var handler: IndoFlash? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.chapter_selecter)
        val chapterList = findViewById(R.id.chapters_list) as ListView
        handler = application as IndoFlash
        val chapterSpecs = handler!!.chapterSpecs()
        val spinnerModel = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, chapterSpecs)
        chapterList.adapter = spinnerModel
        chapterList.onItemClickListener = AdapterView.OnItemClickListener { adapterView, view, i, l ->
            val chapterSpec = chapterSpecs[i]
            handler!!.setCurrentChapter(chapterSpec)
            val intent = Intent(this@ChapterSelecter, WordListSelecter::class.java)
            startActivity(intent)
        }
    }
}