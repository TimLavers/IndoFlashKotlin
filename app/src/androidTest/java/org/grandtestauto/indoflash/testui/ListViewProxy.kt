package org.grandtestauto.indoflash.testui

import android.view.View
import android.widget.ListView

import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher

import android.support.test.espresso.Espresso.onData
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import org.hamcrest.Matchers.anything

open class ListViewProxy(id: Int) : ViewProxy(id) {

    /*
     * From https://stackoverflow.com/questions/30361068/assert-proper-number-of-items-in-list-with-espresso
     */
    private fun withListSize(size: Int): Matcher<View> {
        return object : TypeSafeMatcher<View>() {
            public override fun matchesSafely(view: View): Boolean {
                return (view as ListView).count == size
            }

            override fun describeTo(description: Description) {
                description.appendText("ListView should have $size items")
            }
        }
    }

    fun checkElements(expected: List<String>) {
        for (i in expected.indices) {
            onData(anything())
                    .inAdapterView(withId(id()))
                    .atPosition(i)
                    .check(matches(withText(expected[i])))
        }
        onView(withId(id())).check(matches(withListSize(expected.size)))
    }

    fun select(itemText: String) {
        onData(ContainsMatcher(itemText)).perform(click())
    }
}