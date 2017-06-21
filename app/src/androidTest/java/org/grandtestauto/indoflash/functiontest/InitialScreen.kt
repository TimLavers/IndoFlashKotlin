package org.grandtestauto.indoflash.functiontest

import android.support.test.runner.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

/**
 * See Issue #3 (and others).

 * @author Tim Lavers
 */
@RunWith(AndroidJUnit4::class)
class InitialScreen : TestBase() {
    @Test
    fun doIt() {
        ui.checkCurrentWordIs("you")
        ui.checkTranslationIsEmpty()

        ui.nextButton().checkText("Show")
        ui.checkTitle("Lesson 1")

        ui.showDefinitionOfCurrentWord()

        //The Show/Next button should now show the text "Next".
        ui.nextButton().checkText("Next")
    }
}