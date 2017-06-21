package org.grandtestauto.indoflash.functiontest

import android.support.test.runner.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

/**
 * Check that the "Add to favourites" button works.

 * @author Tim Lavers
 */
@RunWith(AndroidJUnit4::class)
class AddFavourite : TestBase() {

    @Test
    fun doIt() {
        ui.checkCurrentWordIs("you")//Sanity check.
        ui.addToOrRemoveFromFavourites()
        ui.showDefinitionOfCurrentWord()
        ui.checkTranslationIs("anda")

        ui.activateNextButton()
        ui.checkCurrentWordIs("what")

        showFavourites()
        ui.checkCurrentWordIs("you")//It was previously showing "what".
        ui.checkTranslationIsEmpty()//It was previously showing "anda".
        ui.showDefinitionOfCurrentWord()
        ui.checkTranslationIs("anda")//Check that the definition is carried along with a word.
    }
}