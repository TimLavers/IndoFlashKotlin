package org.grandtestauto.indoflash.functiontest

import org.junit.Test

/**
 * Check the controls on screen when the Favourites list is showing.

 * @author Tim Lavers
 */
class InitialScreenForFavourites : TestBase() {
    @Test
    fun doIt() {
        ui.checkCurrentWordIs("you")//Sanity check.

        //Add a word to Favourites so that there is at least one word in the list.
        ui.addToOrRemoveFromFavourites()

        showFavourites()

        ui.checkCurrentWordIs("you")
        ui.checkTranslationIsEmpty()

        ui.addOrRemoveFavouriteButton().checkDescription("Remove from Favourites")
        ui.checkTitle("Favourites")
    }
}
