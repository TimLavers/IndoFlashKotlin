package org.grandtestauto.indoflash.functiontest

import android.support.test.runner.AndroidJUnit4

import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Check that when the Shuffle button is activated once the words are shown in random order.

 * @author Tim Lavers
 */
@RunWith(AndroidJUnit4::class)
class Shuffle : TestBase() {

    @Test
    fun doIt() {
        ui.checkCurrentWordIs("you")//Sanity check.

        ui.shuffleUnshuffleButton().checkDescription("Turn shuffle on")

        ui.shuffleUnshuffleButton().activate()

        ui.shuffleUnshuffleButton().checkDescription("Turn shuffle off")

        //The list should be refreshed and the words should show in a random order.
        Assert.assertNotEquals(firstFiveWordsShowing(), firstFiveWordsUnshuffled())//Could happen, but very unlikely (1 in 5,100,480 chance).
    }
}