package org.grandtestauto.indoflash.activity


import android.app.Activity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import org.grandtestauto.indoflash.IndoFlash
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.listView
import org.jetbrains.anko.verticalLayout

/**
 * User interface for selecting a chapter of word lists to work on.
 *
 * @author Tim Lavers
 */
class ChapterSelecter : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val application = application as IndoFlash
        verticalLayout {
            listView {
                val chapterSpecs = application.chapterSpecs()
                adapter = ArrayAdapter(this@ChapterSelecter, android.R.layout.simple_spinner_dropdown_item, chapterSpecs)
                onItemClickListener = AdapterView.OnItemClickListener { _, _, i, _ ->
                    application.setCurrentChapter(chapterSpecs[i])
                    startActivity(intentFor<WordListSelecter>())
                }
            }
        }
    }
}