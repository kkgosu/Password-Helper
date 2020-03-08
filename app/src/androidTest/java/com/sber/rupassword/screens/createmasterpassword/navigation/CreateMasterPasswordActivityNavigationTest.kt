package com.sber.rupassword.screens.createmasterpassword.navigation

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import com.sber.rupassword.R
import com.sber.rupassword.checkViewIsDisplayed
import com.sber.rupassword.performClickOnView
import com.sber.rupassword.screens.createmasterpassword.CreateMasterPasswordActivity
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class CreateMasterPasswordActivityNavigationTest {

    @get:Rule
    val activityTestRule: ActivityTestRule<CreateMasterPasswordActivity> = ActivityTestRule(CreateMasterPasswordActivity::class.java)

    @Test
    fun test_navToMasterPasswordActivityAndBack() {
        //given
        val input = "1337"

        //execute and verify
        //check that click with empty fields don't proceed to the next activity
        onView(withId(R.id.master_password_header)).check(matches(isDisplayed()))
        onView(withId(R.id.proceed)).perform(click())
        onView(withId(R.id.master_password_header)).check(matches(isDisplayed()))

        //fill fields with equal inputs
        onView(withId(R.id.new_master_password)).perform(typeText(input))
        onView(withId(R.id.confirm_new_master_password)).perform(typeText(input))
        onView(withId(R.id.proceed)).perform(click())

        //check that next activity in shown and go back
        onView(withId(R.id.title)).check(matches(isDisplayed()))
        pressBack()
        onView(withId(R.id.master_password_header)).check(matches(isDisplayed()))

    }

    @Test
    fun test_navToConfirmPasswordOnEqualInput() {
        //given
        val input = "1337"

        //execute and verify
        //check that click with empty fields don't proceed to the next activity
        onView(withId(R.id.master_password_header)).check(matches(isDisplayed()))
        onView(withId(R.id.proceed)).perform(click())
        onView(withId(R.id.master_password_header)).check(matches(isDisplayed()))

        //fill fields with equal inputs
        onView(withId(R.id.new_master_password)).perform(typeText(input))
        onView(withId(R.id.confirm_new_master_password)).perform(typeText(input))
        onView(withId(R.id.proceed)).perform(click())

        //check that next activity in shown
        onView(withId(R.id.title)).check(matches(isDisplayed()))
    }

    @Test
    fun test_navToConfirmPasswordOnNotEqualInput() {
        //given
        val input1 = "1337"
        val input2 = "1111"

        //execute and verify
        //check that click with empty fields don't proceed to the next activity
        onView(withId(R.id.master_password_header)).check(matches(isDisplayed()))
        onView(withId(R.id.proceed)).perform(click())
        onView(withId(R.id.master_password_header)).check(matches(isDisplayed()))

        //fill fields with not equal inputs
        onView(withId(R.id.new_master_password)).perform(typeText(input1))
        onView(withId(R.id.confirm_new_master_password)).perform(typeText(input2))
        onView(withId(R.id.proceed)).perform(click())

        //check that next activity is not shown
        onView(withId(R.id.title)).check(doesNotExist())
    }

    @Test
    fun test_cancelButtonCloseApp() {

        checkViewIsDisplayed(R.id.master_password_header)
        checkViewIsDisplayed(R.id.cancel)
        performClickOnView(R.id.cancel)

        Thread.sleep(2000)
        assertTrue(activityTestRule.activity.isFinishing)
    }
}