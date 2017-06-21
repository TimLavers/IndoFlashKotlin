package org.grandtestauto.indoflash.testui

import org.grandtestauto.indoflash.R

class ChapterListSelecterProxy internal constructor() : ListViewProxy(R.id.chapters_list) {

    fun selectChapter(title: String): WordListSelecterProxy {
        select(title)
        return WordListSelecterProxy()
    }
}