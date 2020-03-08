package com.sber.rupassword

import androidx.annotation.IdRes
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*

fun performClickOnView(@IdRes id: Int) = onView(withId(id)).perform(click())

fun checkViewIsDisplayed(@IdRes id: Int) = onView(withId(id)).check(matches(isDisplayed()))

fun doInputOnView(@IdRes id: Int, input: String) = onView(withId(id)).perform(typeText(input))

fun checkTextMatch(@IdRes id: Int, @IdRes text: Int) = onView(withId(id)).check(matches(withText(text)))