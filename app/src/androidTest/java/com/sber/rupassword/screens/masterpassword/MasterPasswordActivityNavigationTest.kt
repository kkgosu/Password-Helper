package com.sber.rupassword.screens.masterpassword

import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.Espresso.pressBackUnconditionally
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import com.sber.rupassword.R
import com.sber.rupassword.checkViewIsDisplayed
import com.sber.rupassword.doInputOnView
import com.sber.rupassword.performClickOnView
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MasterPasswordActivityNavigationTest {
    @get:Rule
    val activityTestRule: ActivityTestRule<MasterPasswordActivity> = ActivityTestRule(MasterPasswordActivity::class.java)

    @Test
    fun test_NavToNextActivity() {

        checkViewIsDisplayed(R.id.title)
        doInputOnView(R.id.master_password_input, "1337")
        performClickOnView(R.id.unlock)
        checkViewIsDisplayed(R.id.toolbar1)
        pressBackUnconditionally()

        assertTrue(activityTestRule.activity.isFinishing)
    }

    @Test
    fun test_NavToResetMasterPasswordActivityAndGoBack() {

        checkViewIsDisplayed(R.id.title)
        performClickOnView(R.id.forgot_password)
        checkViewIsDisplayed(R.id.reset_password_header)
        pressBack()
        checkViewIsDisplayed(R.id.title)
    }
}