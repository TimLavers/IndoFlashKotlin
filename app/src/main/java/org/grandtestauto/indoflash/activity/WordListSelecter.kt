package org.grandtestauto.indoflash.activity

/**
 * User interface for selecting a word list to work on.

 * @author Tim Lavers
 */
class WordListSelecter : android.app.Activity() {
    private var application: org.grandtestauto.indoflash.IndoFlash? = null

    public override fun onCreate(savedInstanceState: android.os.Bundle?) {
        super.onCreate(savedInstanceState)
        application = getApplication() as org.grandtestauto.indoflash.IndoFlash
        setContentView(org.grandtestauto.indoflash.R.layout.word_list_selecter)
        val listsList = findViewById(org.grandtestauto.indoflash.R.id.lists_list) as android.widget.ListView
        val listSpecs = application!!.currentChapter().wordLists()
        val spinnerModel = android.widget.ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, listSpecs)
        listsList.adapter = spinnerModel
        listsList.onItemClickListener = android.widget.AdapterView.OnItemClickListener { adapterView, view, i, l ->
            val wordListSpec = listSpecs[i]
            application!!.setWordList(wordListSpec)
            val intent = android.content.Intent(this@WordListSelecter, WordListDisplay::class.java)
            startActivity(intent)
        }

        val showChaptersButton = findViewById(org.grandtestauto.indoflash.R.id.show_chapters_button) as android.widget.ImageButton
        showChaptersButton.setImageResource(org.grandtestauto.indoflash.R.drawable.ic_chapters)
        showChaptersButton.setOnClickListener {
            val intent = android.content.Intent(this@WordListSelecter, ChapterSelecter::class.java)
            startActivity(intent)
        }
    }
}