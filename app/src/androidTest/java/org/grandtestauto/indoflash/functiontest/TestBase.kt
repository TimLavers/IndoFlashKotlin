package org.grandtestauto.indoflash.functiontest

import android.content.Intent
import android.support.test.rule.ActivityTestRule
import org.grandtestauto.indoflash.IndoFlash
import org.grandtestauto.indoflash.activity.WordListDisplay
import org.grandtestauto.indoflash.testui.WordListDisplayProxy
import org.junit.After
import org.junit.Before
import org.junit.Rule
import java.util.*

/**
 * @author Tim Lavers
 */
open class TestBase {
    @get:Rule
    var activityRule = ActivityTestRule<WordListDisplay>(WordListDisplay::class.java)
    lateinit var ui: WordListDisplayProxy

    @Before
    fun setup() {
        ui = WordListDisplayProxy()
    }

    @After
    fun cleanup() {
        val application = activityRule.activity.application as IndoFlash
        val chapter1Spec = application.applicationSpec().chapterSpecs[0]
        application.setCurrentChapter(chapter1Spec)
        application.setWordList(chapter1Spec.wordLists()[0])

        application.clearFavourites()
        if (application.shuffle()) {
            application.toggleShuffle()
        }
        if (application.showIndonesianFirst()) {
            application.toggleShowIndonesianFirst()
        }
    }

    internal fun closeApplication() {
        activityRule.activity.finish()
    }

    internal fun restartApplication() {
        activityRule.launchActivity(Intent())
        ui = WordListDisplayProxy()
    }

    internal fun showFavourites() {
        val wordListSelectorProxy = ui.activateWordListSelectorButton()
        wordListSelectorProxy.selectFavourites()
    }

    internal fun firstFiveWordsShowing(): List<CharSequence> {
        val wordsShown = LinkedList<CharSequence>()
        for (i in 0..4) {
            wordsShown.add(ui.currentWord()!!)
            ui.showDefinitionOfCurrentWord()
            ui.activateNextButton()
        }
        return wordsShown
    }

    internal fun firstFiveWordsUnshuffled(): List<CharSequence> {
        val wordsInOrder = LinkedList<CharSequence>()
        wordsInOrder.add("you")
        wordsInOrder.add("what")
        wordsInOrder.add("How are you?")
        wordsInOrder.add("good")
        wordsInOrder.add("fine")
        return wordsInOrder
    }
}