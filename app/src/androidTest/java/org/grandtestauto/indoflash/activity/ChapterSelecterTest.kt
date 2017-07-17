package org.grandtestauto.indoflash.activity

import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import org.grandtestauto.indoflash.R
import org.grandtestauto.indoflash.testui.ChapterListSelecterProxy

import org.grandtestauto.indoflash.testui.ListViewProxy
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

import java.util.ArrayList

@RunWith(AndroidJUnit4::class)
class ChapterSelecterTest {

    @Suppress("unused")
    @get:Rule var activityRule = ActivityTestRule<ChapterSelecter>(ChapterSelecter::class.java)

    @Test
    fun ensureChaptersShow() {
        val expectedChapters = ArrayList<String>(6)
        expectedChapters.add("Lessons 1 - 10")
        expectedChapters.add("Lessons 11 - 20")
        expectedChapters.add("Lessons 21 - 30")
        expectedChapters.add("Lessons 31 - 40")
        expectedChapters.add("Lessons 41 - 50")
        expectedChapters.add("Lessons 51 - 57")

        ChapterListSelecterProxy().checkElements(expectedChapters)
    }
}