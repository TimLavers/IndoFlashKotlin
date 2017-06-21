package org.grandtestauto.indoflash.functiontest

import android.support.test.runner.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

/**
 * Checks that the Show/Next button works as expected.

 * @author Tim Lavers
 */
@RunWith(AndroidJUnit4::class)
class NextWord : TestBase() {

    @Test
    fun doIt() {
        ui.checkCurrentWordIs("you")
        ui.checkTranslationIsEmpty()
        ui.showDefinitionOfCurrentWord()
        ui.checkCurrentWordIs("you")
        ui.checkTranslationIs("anda")

        ui.activateNextButton()

        ui.checkCurrentWordIs("what")
        ui.checkTranslationIsEmpty()

        ui.showDefinitionOfCurrentWord()

        ui.checkCurrentWordIs("what")
        ui.checkTranslationIs("apa")
    }
}
