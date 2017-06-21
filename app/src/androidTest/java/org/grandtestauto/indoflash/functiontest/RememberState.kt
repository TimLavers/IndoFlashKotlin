package org.grandtestauto.indoflash.functiontest

import android.support.test.runner.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

/**
 * Check that application state such as the list showing and whether to show Indonesian or English first
 * is preserved across application instances.

 * @author Tim Lavers
 */
@RunWith(AndroidJUnit4::class)
class RememberState : TestBase() {

    @Test
    fun doIt() {
        //Check that the current word is the first word of the first list of the first chapter.
        ui.checkCurrentWordIs("you")

        //Select Indonesian first.
        ui.toggleLanguage()

        //Choose list 4 of chapter 5. (bahagia=contented, happy (with your lot in life))
        ui.activateWordListSelectorButton()
                .activateChapterListButton()
                .selectChapter("Lessons 51 - 57")
                .select("Lesson 54")
        ui.checkCurrentWordIs("bahagia")

        //Shut down and restart the application.
        closeApplication()
        restartApplication()

        //Check that the same word is selected, and that we are still showing Indonesian first.
        ui.checkCurrentWordIs("bahagia")
    }
}
