package org.grandtestauto.indoflash

import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import org.grandtestauto.indoflash.activity.WordListDisplay

import org.grandtestauto.indoflash.testui.WordListDisplayProxy
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class WordListDisplayTest {
    @Suppress("unused")
    @get:Rule
    var activityRule = ActivityTestRule<WordListDisplay>(WordListDisplay::class.java)

    @Test
    fun testOnCreate() {
        val wld = WordListDisplayProxy()
        wld.checkWordViewIsShowing()
        wld.checkCurrentWordIs("you")
        wld.checkTranslationIsEmpty()
        wld.checkFavouritesButtonIsShowing()
        wld.checkNextButtonIsShowing()
        wld.checkWordListsButtonIsShowing()
    }

    @Test
    fun testActivateNext() {
        val wld = WordListDisplayProxy()
        wld.checkCurrentWordIs("you")
        wld.checkTranslationIsEmpty()

        wld.activateNextButton()
        wld.checkCurrentWordIs("you")
        wld.checkTranslationIs("anda")

        wld.activateNextButton()
        wld.checkCurrentWordIs("what")
        wld.checkTranslationIsEmpty()

        wld.activateNextButton()
        wld.checkCurrentWordIs("what")
        wld.checkTranslationIs("apa")
    }
}