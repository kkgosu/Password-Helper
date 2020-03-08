package com.sber.rupassword.screens.masterpassword

import androidx.test.core.app.ActivityScenario
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.sber.rupassword.R
import com.sber.rupassword.checkTextMatch
import com.sber.rupassword.checkViewIsDisplayed
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MasterPasswordActivityViewTest {

    //Visibility
    @Test
    fun test_Visibility() {
        val activityScenario = ActivityScenario.launch(MasterPasswordActivity::class.java)

        checkViewIsDisplayed(R.id.title)
        checkViewIsDisplayed(R.id.master_password_input_layout)
        checkViewIsDisplayed(R.id.unlock)
        checkViewIsDisplayed(R.id.forgot_password)
    }

    //Text
    @Test
    fun test_textMatches() {
        val activityScenario = ActivityScenario.launch(MasterPasswordActivity::class.java)

        checkTextMatch(R.id.title, R.string.app_name)
        checkTextMatch(R.id.forgot_password, R.string.forgot_master_password)
    }
}