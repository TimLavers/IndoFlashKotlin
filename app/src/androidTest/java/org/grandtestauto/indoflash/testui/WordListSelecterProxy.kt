package org.grandtestauto.indoflash.testui

import org.grandtestauto.indoflash.R
import org.hamcrest.BaseMatcher
import org.hamcrest.Description

import android.support.test.espresso.Espresso.onData
import android.support.test.espresso.action.ViewActions.click

class WordListSelecterProxy internal constructor() : ListViewProxy(R.id.lists_list) {

    fun selectFavourites() {
        onData(ContainsMatcher("Favourites")).perform(click())
    }

    fun selectList(name: String): WordListDisplayProxy {
        select(name)
        return WordListDisplayProxy()
    }

    fun activateChapterListButton(): ChapterListSelecterProxy {
        ButtonProxy(R.id.show_chapters_button).activate()
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