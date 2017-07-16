package org.grandtestauto.indoflash.activity

/**
 * User interface for selecting a chapter of word lists to work on.
 *
 * @author Tim Lavers
 */
class ChapterSelecter : android.app.Activity() {
    private var handler: org.grandtestauto.indoflash.IndoFlash? = null

    override fun onCreate(savedInstanceState: android.os.Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(org.grandtestauto.indoflash.R.layout.chapter_selecter)
        val chapterList = findViewById(org.grandtestauto.indoflash.R.id.chapters_list) as android.widget.ListView
        handler = application as org.grandtestauto.indoflash.IndoFlash
        val chapterSpecs = handler!!.chapterSpecs()
        val spinnerModel = android.widget.ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, chapterSpecs)
        chapterList.adapter = spinnerModel
        chapterList.onItemClickListener = android.widget.AdapterView.OnItemClickListener { adapterView, view, i, l ->
            val chapterSpec = chapterSpecs[i]
            handler!!.setCurrentChapter(chapterSpec)
            val intent = android.content.Intent(this@ChapterSelecter, WordListSelecter::class.java)
            startActivity(intent)
        }
    }
}