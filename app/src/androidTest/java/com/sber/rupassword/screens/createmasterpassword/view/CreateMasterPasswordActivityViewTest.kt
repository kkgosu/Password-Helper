package com.sber.rupassword.screens.createmasterpassword.view

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.sber.rupassword.R
import com.sber.rupassword.screens.createmasterpassword.CreateMasterPasswordActivity
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class CreateMasterPasswordActivityViewTest {

    //Visibility
    @Test
    fun test_Activity_inView() {
        val activityScenario = ActivityScenario.launch(CreateMasterPasswordActivity::class.java)

        onView(withId(R.id.master_password_header)).check(matches(isDisplayed()))
        onView(withId(R.id.master_password_description)).check(matches(isDisplayed()))
        onView(withId(R.id.new_master_password_layout)).check(matches(isDisplayed()))
        onView(withId(R.id.proceed)).check(matches(isDisplayed()))
        onView(withId(R.id.cancel)).check(matches(isDisplayed()))
    }

    //Text
    @Test
    fun test_HeaderTextDisplayed() {
        val activityScenario = ActivityScenario.launch(CreateMasterPasswordActivity::class.java)
        onView(withId(R.id.master_password_header)).check(matches(withText(R.string.creating_new_master_password)))
        onView(withId(R.id.master_password_description)).check(matches(withText(R.string.master_password_desc)))
    }
}