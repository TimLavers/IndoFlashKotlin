package org.grandtestauto.indoflash.functiontest

import android.support.test.runner.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

/**
 * See Issue #7, Issue #8.

 * @author Tim Lavers
 */
@RunWith(AndroidJUnit4::class)
class RemoveTheOnlyFavourite : TestBase() {

    @Test
    fun doIt() {
        ui.checkCurrentWordIs("you")//Sanity check.
        ui.addToOrRemoveFromFavourites()

        showFavourites()

        ui.checkCurrentWordIs("you")
        ui.addToOrRemoveFromFavourites()

        //Check that the empty favourites message shows.
        ui.checkShowsEmptyFavouritesMessage()

        //Check that the remove favourite button is disabled. (Issue #7.)
        ui.addOrRemoveFavouriteButton().checkIsInactive()
    }
}