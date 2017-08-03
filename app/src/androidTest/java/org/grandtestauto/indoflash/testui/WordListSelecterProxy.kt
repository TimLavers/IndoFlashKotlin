package org.grandtestauto.indoflash.testui

import android.support.test.espresso.Espresso.onData
import android.support.test.espresso.action.ViewActions.click
import org.grandtestauto.indoflash.activity.CHAPTERS_BUTTON_ID
import org.grandtestauto.indoflash.activity.CHAPTERS_LIST_ID
import org.hamcrest.BaseMatcher
import org.hamcrest.Description

class WordListSelecterProxy internal constructor() : ListViewProxy(CHAPTERS_LIST_ID) {

    fun selectFavourites() {
        onData(ContainsMatcher("Favourites")).perform(click())
    }

    fun selectList(name: String): WordListDisplayProxy {
        select(name)
        return WordListDisplayProxy()
    }

    fun activateChapterListButton(): ChapterListSelecterProxy {
        ButtonProxy(CHAPTERS_BUTTON_ID).activate()
        return ChapterListSelecterProxy()
    }
}

internal class ContainsMatcher(private val toMatch: String) : BaseMatcher<String>() {
    override fun matches(item: Any): Boolean {
        return item.toString().contains(toMatch)
    }

    override fun describeTo(description: Description) {
    }
}