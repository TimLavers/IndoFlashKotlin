package org.grandtestauto.indoflash.functiontest


import android.support.test.runner.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

/**
 * Check that a different word list can be selected, from the same list of word lists.

 * @author Tim Lavers
 */
@RunWith(AndroidJUnit4::class)
class SelectDifferentWordListFromSameChapter : TestBase() {

    @Test
    fun doIt() {

        ui.checkCurrentWordIs("you")//Sanity check: the first word of the first list should be showing.
        ui.activateWordListSelectorButton().selectList("Lesson 4")

        //The first word of the selected list should be showing.
        ui.checkCurrentWordIs("water, fluid, juice")
    }
}
