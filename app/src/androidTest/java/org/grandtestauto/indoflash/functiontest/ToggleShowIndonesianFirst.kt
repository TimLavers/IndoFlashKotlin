package org.grandtestauto.indoflash.functiontest

import android.support.test.runner.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

/**
 * Check that the "Show Indonesian/English First" toggle button works as expected.

 * @author Tim Lavers
 */
@RunWith(AndroidJUnit4::class)
class ToggleShowIndonesianFirst : TestBase() {

    @Test
    fun doIt() {
        //Sanity check that word1 of list1 of chapter1 is showing.
        ui.checkCurrentWordIs("you")
        ui.checkTranslationIsEmpty()

        //Toggle.
        ui.toggleIndonesianButton().checkDescription("English to Indonesian mode")
        ui.toggleLanguage()
        ui.toggleIndonesianButton().checkDescription("Indonesian to English mode")

        //The translation should now be showing as the word.
        ui.checkCurrentWordIs("anda")
        ui.checkTranslationIsEmpty()

        //Check the activation of the "Show" button with Indonesian first.
        ui.showDefinitionOfCurrentWord()
        ui.checkCurrentWordIs("anda")
        ui.checkTranslationIs("you")

        //Check the activation of the "Show" button with Indonesian first.
        ui.activateNextButton()
        ui.checkCurrentWordIs("apa")
        ui.checkTranslationIsEmpty()

        ui.showDefinitionOfCurrentWord()
        ui.checkCurrentWordIs("apa")
        ui.checkTranslationIs("what")

        //Toggle again.
        ui.toggleLanguage()
        ui.toggleIndonesianButton().checkDescription("English to Indonesian mode")
        ui.checkCurrentWordIs("you")
        ui.checkTranslationIsEmpty()
    }
}