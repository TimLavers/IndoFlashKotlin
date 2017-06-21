package org.grandtestauto.indoflash.testui

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withContentDescription
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText

open class ViewProxy internal constructor(private val id: Int) {

    internal fun checkIsShowing() {
        onView(withId(id)).check(matches(isDisplayed()))
    }

    internal fun id(): Int {
        return id
    }

    fun checkText(expected: String) {
        onView(withId(id())).check(matches(withText(expected)))
    }

    fun checkDescription(expected: String) {
        onView(withId(id())).check(matches(withContentDescription(expected)))
    }
}