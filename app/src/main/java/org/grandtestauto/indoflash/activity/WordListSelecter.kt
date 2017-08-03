package org.grandtestauto.indoflash.activity

import android.app.Activity
import android.os.Bundle
import android.view.Gravity
import android.widget.ArrayAdapter
import org.grandtestauto.indoflash.IndoFlash
import org.grandtestauto.indoflash.R
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick

val CHAPTERS_BUTTON_ID = 10_000_001
val CHAPTERS_LIST_ID = 10_000_002
/**
 * User interface for selecting a word list to work on.

 * @author Tim Lavers
 */
class WordListSelecter : Activity() {

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val application = application as IndoFlash

        verticalLayout {
            listView {
                id = CHAPTERS_LIST_ID
                val listSpecs = application.currentChapter().wordLists()
                adapter = ArrayAdapter(this@WordListSelecter, android.R.layout.simple_spinner_dropdown_item, listSpecs)
                setOnItemClickListener { parent, view, i, id ->
                    application.setWordList(listSpecs[i])
                    startActivity(intentFor<WordListDisplay>())
                }
            }
            linearLayout {
                gravity = Gravity.CENTER
                imageButton(R.drawable.ic_chapters) {
                    id = CHAPTERS_BUTTON_ID
                    padding = dip(5)
                    onClick {
                        startActivity(intentFor<ChapterSelecter>())
                    }
                }
            }
        }
    }
}