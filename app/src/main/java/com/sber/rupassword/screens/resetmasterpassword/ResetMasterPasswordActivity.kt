package com.sber.rupassword.screens.resetmasterpassword

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sber.rupassword.R
import com.sber.rupassword.domain.IResetMasterPasswordContract
import com.sber.rupassword.screens.createmasterpassword.CreateMasterPasswordActivity
import kotlinx.android.synthetic.main.activity_reset_master_password.*

class ResetMasterPasswordActivity : AppCompatActivity(), IResetMasterPasswordContract.View {

    private lateinit var presenter: ResetMasterPasswordPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_master_password)

        presenter = ResetMasterPasswordPresenter(this)

        reset_password.setOnClickListener {
            presenter.resetAccount(this)
        }

        cancel.setOnClickListener {
            onBackPressed()
        }
    }

    override fun startCreateMasterPasswordActivity() {
        startActivity(Intent(this, CreateMasterPasswordActivity::class.java))
        finishAffinity()
    }
}
