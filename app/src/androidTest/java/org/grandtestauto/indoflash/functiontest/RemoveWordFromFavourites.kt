package org.grandtestauto.indoflash.functiontest

import android.support.test.runner.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

/**
 * Check that in the Favourites list, removing a word moves to the next word.

 * @author Tim Lavers
 */
@RunWith(AndroidJUnit4::class)
class RemoveWordFromFavourites : TestBase() {

    @Test
    fun doIt() {
        ui.checkCurrentWordIs("you")//Sanity check.
        ui.addToOrRemoveFromFavourites()
        ui.showDefinitionOfCurrentWord()
        ui.activateNextButton()

        ui.checkCurrentWordIs("what")
        ui.addToOrRemoveFromFavourites()
        showFavourites()
        ui.checkCurrentWordIs("you")

        ui.addToOrRemoveFromFavourites()
        ui.checkCurrentWordIs("what")
        ui.checkTranslationIsEmpty()
    }
}
