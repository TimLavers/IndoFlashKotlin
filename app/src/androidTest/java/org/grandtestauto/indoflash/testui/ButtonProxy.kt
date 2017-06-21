package org.grandtestauto.indoflash.testui

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isClickable
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.view.View
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.not

class ButtonProxy internal constructor(id: Int) : ViewProxy(id) {

    fun activate() {
        onView(withId(id())).perform(click())
    }

    fun checkIsInactive() {
        onView(allOf<View>(withId(id()), not<View>(isClickable()))).check(matches(isDisplayed()))
    }
}