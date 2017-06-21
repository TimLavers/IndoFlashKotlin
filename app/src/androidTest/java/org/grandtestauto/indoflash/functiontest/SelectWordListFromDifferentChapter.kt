package org.grandtestauto.indoflash.functiontest

import android.support.test.runner.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

/**
 * Check that a different word list can be selected, from another list of word lists.

 * @author Tim Lavers
 */
@RunWith(AndroidJUnit4::class)
class SelectWordListFromDifferentChapter : TestBase() {

    @Test
    fun doIt() {
        ui.checkCurrentWordIs("you")//Sanity check: the first word of the first list should be showing.

        ui.activateWordListSelectorButton()
                .activateChapterListButton()
                .selectChapter("Lessons 21 - 30")
                .selectList("Lesson 24")
        //The first word of the selected list should be showing.
        ui.checkCurrentWordIs("in order to")
    }
}