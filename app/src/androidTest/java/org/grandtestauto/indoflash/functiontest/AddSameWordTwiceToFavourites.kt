package org.grandtestauto.indoflash.functiontest

import android.support.test.runner.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

/**
 * See Issue #2.

 * @author Tim Lavers
 */
@RunWith(AndroidJUnit4::class)
class AddSameWordTwiceToFavourites : TestBase() {

    @Test
    fun doIt() {
        ui.checkCurrentWordIs("you")//Sanity check.
        ui.addToOrRemoveFromFavourites()
        ui.checkCurrentWordIs("you")//Sanity check that we are about to add the same word again to the favourites.
        ui.addToOrRemoveFromFavourites()

        ui.showDefinitionOfCurrentWord()
        ui.activateNextButton()
        ui.checkCurrentWordIs("what")//Sanity check that we added "you/anda" twice as a favourite.
        ui.addToOrRemoveFromFavourites()//So that two distinct words have been added as favourites.

        showFavourites()
        ui.checkCurrentWordIs("you")
        ui.checkTranslationIsEmpty()
        ui.showDefinitionOfCurrentWord()
        ui.checkTranslationIs("anda")
        ui.activateNextButton()
        ui.checkCurrentWordIs("what")//Not "you/anda" even though it was added twice.
    }
}