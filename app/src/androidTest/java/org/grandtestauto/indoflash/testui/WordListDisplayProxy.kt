package org.grandtestauto.indoflash.testui

import android.support.test.espresso.UiController
import android.support.test.espresso.ViewAction
import android.view.View
import android.widget.TextView

import org.grandtestauto.indoflash.R
import org.hamcrest.Matcher

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText

class WordListDisplayProxy {
    private val currentWordProxy = TextFieldProxy(R.id.word_view)
    private val translatedWordProxy = TextFieldProxy(R.id.definition_view)
    private val nextButtonProxy = ButtonProxy(R.id.next_button)
    private val favouritesProxy = ButtonProxy(R.id.add_to_favourites_button)
    private val wordListsButtonProxy = ButtonProxy(R.id.show_word_lists_button)

    fun checkCurrentWordIs(expected: String) {
        currentWordProxy.checkText(expected)
    }

    fun checkShowsEmptyFavouritesMessage() {
        onView(withId(R.id.word_view)).check(matches(withText(R.string.favourites_is_empty)))
    }

    fun checkWordViewIsShowing() {
        currentWordProxy.checkIsShowing()
    }

    fun checkTranslationIsEmpty() {
        translatedWordProxy.checkThatTextIsEmpty()
    }

    fun checkTranslationIs(expected: String) {
        translatedWordProxy.checkText(expected)
    }

    fun checkFavouritesButtonIsShowing() {
        favouritesProxy.checkIsShowing()
    }

    fun checkNextButtonIsShowing() {
        nextButtonProxy.checkIsShowing()
    }

    fun checkWordListsButtonIsShowing() {
        wordListsButtonProxy.checkIsShowing()
    }

    fun activateNextButton() {
        nextButtonProxy.activate()
    }

    fun addToOrRemoveFromFavourites() {
        favouritesProxy.activate()
    }

    fun showDefinitionOfCurrentWord() {
        checkTranslationIsEmpty()
        activateNextButton()
    }

    fun checkTitle(expected: String) {
        ViewProxy(R.id.word_list_title_view).checkText(expected)
    }

    fun activateWordListSelectorButton(): WordListSelecterProxy {
        wordListsButtonProxy.activate()
        return WordListSelecterProxy()
    }

    fun toggleLanguage() {
        toggleIndonesianButton().activate()
    }

    fun nextButton(): ButtonProxy {
        return nextButtonProxy
    }

    fun addOrRemoveFavouriteButton(): ButtonProxy {
        return ButtonProxy(R.id.add_to_favourites_button)
    }

    fun toggleIndonesianButton(): ButtonProxy {
        return ButtonProxy(R.id.indonesian_first_button)
    }

    fun shuffleUnshuffleButton(): ButtonProxy {
        return ButtonProxy(R.id.shuffle_button)
    }


    /*
    From StackOverflow haffax
     */
    fun currentWord(): CharSequence? {
        val result = arrayOfNulls<CharSequence>(1)
        onView(withId(R.id.word_view)).perform(object : ViewAction {
            override fun getConstraints(): Matcher<View> {
                return isAssignableFrom(TextView::class.java)
            }

            override fun getDescription(): String? {
                return null
            }

            override fun perform(uiController: UiController, view: View) {
                result[0] = (view as TextView).text
            }
        })
        return result[0]
    }
}