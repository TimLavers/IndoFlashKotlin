package org.grandtestauto.indoflash.testui

import org.grandtestauto.indoflash.activity.CHAPTERS_BUTTON_ID

class ChapterListSelecterProxy internal constructor() : ListViewProxy(CHAPTERS_BUTTON_ID) {

    fun selectChapter(title: String): WordListSelecterProxy {
        select(title)
        return WordListSelecterProxy()
    }
}