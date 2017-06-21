package org.grandtestauto.indoflash.functiontest

import android.support.test.runner.AndroidJUnit4

import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Check that the Shuffle button acts as a toggle.
 *
 * @author Tim Lavers
 */
@RunWith(AndroidJUnit4::class)
class TurnShuffleOff : TestBase() {

    @Test
    fun doIt() {
        ui.checkCurrentWordIs("you")//Sanity check.

        ui.shuffleUnshuffleButton().checkDescription("Turn shuffle on")

        ui.shuffleUnshuffleButton().activate()

        ui.shuffleUnshuffleButton().checkDescription("Turn shuffle off")
        ui.shuffleUnshuffleButton().activate()//Second time it is called.

        //The list should be refreshed and the words should show in normal order.
        Assert.assertEquals(firstFiveWordsUnshuffled(), firstFiveWordsShowing())
    }
}