package com.sber.rupassword.screens.savedpasswords

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sber.rupassword.R
import com.sber.rupassword.domain.ISavedPasswordsContract
import com.sber.rupassword.domain.models.Password
import com.sber.rupassword.repository.PasswordPref
import com.sber.rupassword.screens.common.AddPasswordDialog
import com.sber.rupassword.screens.common.PasswordsAdapter
import com.sber.rupassword.screens.passworddetails.PasswordDetailsActivity
import kotlinx.android.synthetic.main.activity_saved_passwords.*

class SavedPasswordsActivity : AppCompatActivity(), AddPasswordDialog.Listener, PasswordsAdapter.Interaction, ISavedPasswordsContract.View {

    private lateinit var passwordsAdapter: PasswordsAdapter
    private lateinit var presenter: SavedPasswordsPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_saved_passwords)

        presenter = SavedPasswordsPresenter(this)
        passwordsAdapter = PasswordsAdapter(this)
        saved_passwords.adapter = passwordsAdapter
        passwordsAdapter.submitList(PasswordPref.getAllPasswords())

        fab.setOnClickListener {
            AddPasswordDialog().show(supportFragmentManager, "dialog")
        }
    }

    override fun submitList(passwords: List<Password>) {
        passwordsAdapter.submitList(passwords)
    }

    override fun onClick(password: Password) {
        presenter.savePassword(password)
    }

    override fun onItemSelected(position: Int, item: Password) {
        val intent = Intent(this, PasswordDetailsActivity::class.java)
        intent.putExtra("password_site", item.site)
        intent.putExtra("password_login", item.login)
        startActivity(intent)
    }
}
