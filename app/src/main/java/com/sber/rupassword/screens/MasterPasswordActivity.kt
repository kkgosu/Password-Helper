package com.sber.rupassword.screens

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.sber.rupassword.R
import com.sber.rupassword.domain.IMasterPasswordContract
import kotlinx.android.synthetic.main.activity_master_password.*

class MasterPasswordActivity() : AppCompatActivity(), IMasterPasswordContract.View {

    private lateinit var presenter: MasterPasswordPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_master_password)

        presenter = MasterPasswordPresenter(this)

        unlock.setOnClickListener {
            presenter.onNextClicked(master_password_input.text.toString())
        }

        forgot_password.setOnClickListener {
            presenter.onForgotPasswordClicked()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    override fun startMainActivity() {
        startActivity(Intent(this@MasterPasswordActivity, MainActivity::class.java))
        finishAffinity()
    }

    override fun startCreateMasterPasswordActivity() {
        startActivity(Intent(this, CreateMasterPasswordActivity::class.java))
        finishAffinity()
    }

    override fun startForgotPasswordActivity() {
        startActivity(Intent(this, ResetMasterPasswordActivity::class.java))
    }

    override fun showMessage() {
        Toast.makeText(this@MasterPasswordActivity, getString(
                R.string.wrong_master_password),
                Toast.LENGTH_SHORT).show()
    }
}
