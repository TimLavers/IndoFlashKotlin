package org.grandtestauto.indoflash.functiontest

import android.support.test.runner.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

/**
 * Check that a Favourite can successfully be removed when its definition is showing.

 * @author Tim Lavers
 */
@RunWith(AndroidJUnit4::class)
class RemoveWordFromFavouritesWhileDefinitionIsShowing : TestBase() {

    @Test
    fun doIt() {
        //Add the first two words to favourites.
        ui.checkCurrentWordIs("you")//Sanity check.
        ui.addToOrRemoveFromFavourites()
        ui.showDefinitionOfCurrentWord()
        ui.activateNextButton()
        ui.checkCurrentWordIs("what")
        ui.addToOrRemoveFromFavourites()

        showFavourites()
        ui.checkCurrentWordIs("you")
        ui.showDefinitionOfCurrentWord()
        ui.checkTranslationIs("anda")
        ui.addToOrRemoveFromFavourites()

        ui.checkCurrentWordIs("what")
        ui.checkTranslationIsEmpty()
    }
}