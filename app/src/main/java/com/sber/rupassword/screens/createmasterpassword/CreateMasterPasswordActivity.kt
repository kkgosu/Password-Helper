package com.sber.rupassword.screens.createmasterpassword

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.sber.rupassword.R
import com.sber.rupassword.domain.ICreateMasterPasswordContract
import com.sber.rupassword.screens.masterpassword.MasterPasswordActivity
import com.sber.rupassword.utils.setError
import com.sber.rupassword.utils.toStringOrNull
import kotlinx.android.synthetic.main.activity_create_master_password.*

class CreateMasterPasswordActivity : AppCompatActivity(), ICreateMasterPasswordContract.View {

    private lateinit var presenter: CreateMasterPasswordPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_master_password)

        presenter = CreateMasterPasswordPresenter(this)

        new_master_password.setError()
        confirm_new_master_password.setError()

        proceed.setOnClickListener {
            val password = new_master_password.text?.toStringOrNull()
            val passwordConfirmed = confirm_new_master_password.text?.toStringOrNull()

            presenter.onNextClicked(password, passwordConfirmed)
        }

        cancel.setOnClickListener {
            presenter.onBackClicked()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    override fun onBackClicked() {
        onBackPressed()
    }

    override fun startMasterPasswordActivity() {
        startActivity(Intent(this, MasterPasswordActivity::class.java))
    }

    override fun showMessage() {
        Toast.makeText(this, getString(R.string.passwords_not_match), Toast.LENGTH_SHORT).show()
    }
}
