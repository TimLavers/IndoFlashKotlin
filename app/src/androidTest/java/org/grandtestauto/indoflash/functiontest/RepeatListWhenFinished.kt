package org.grandtestauto.indoflash.functiontest

import android.support.test.runner.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

/**
 * See Issue #4, Issue #14.

 * @author Tim Lavers
 */
@RunWith(AndroidJUnit4::class)
class RepeatListWhenFinished : TestBase() {

    @Test
    fun doIt() {
        ui.checkCurrentWordIs("you")//Sanity check that we are on the correct list.

        //There are 24 words in the first list. Go through the first 23.
        for (i in 0..22) {
            ui.showDefinitionOfCurrentWord()
            ui.activateNextButton()
        }
        //Sanity check that we are on the last word of the list.
        ui.checkCurrentWordIs("thank you")
        ui.showDefinitionOfCurrentWord()
        ui.checkTranslationIs("terima kasih")

        //The list is finished so the "Show/Next" button should now say "Repeat list".
        ui.nextButton().checkText("Repeat list")

        //If we activate the next button we should get back to the start.
        ui.activateNextButton()

        ui.checkCurrentWordIs("you")//We should be back at the start of the list.
        ui.showDefinitionOfCurrentWord()
        ui.checkTranslationIs("anda")//Issue #14.
    }
}