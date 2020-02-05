package com.sber.rupassword

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.CompoundButton
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), CompoundButton.OnCheckedChangeListener {

    private lateinit var passwordHelper: PasswordHelper

    private lateinit var ruLetters: Array<String>
    private lateinit var enLetters: Array<String>

    private var symbolsQuantity = MIN_QUANTITY

    private lateinit var capsBtn: CompoundButton
    private lateinit var symbolsBtn: CompoundButton
    private lateinit var numbersBtn: CompoundButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(findViewById(R.id.toolbar1))

        ruLetters = resources.getStringArray(R.array.russians)
        enLetters = resources.getStringArray(R.array.latins)

        passwordHelper = PasswordHelper(ruLetters, enLetters)

        capsBtn = findViewById(R.id.check_caps)
        symbolsBtn = findViewById(R.id.check_symbols)
        numbersBtn = findViewById(R.id.check_numbers)

        check_caps.setOnCheckedChangeListener(this)
        check_symbols.setOnCheckedChangeListener(this)
        check_numbers.setOnCheckedChangeListener(this)

        copy_password.isEnabled = false

        regenerate_password.setOnClickListener {
            generatePassword()
        }
        copy_password.setOnClickListener {
            copyToClipboard(password_result)
        }
        copy_password_generated.setOnClickListener {
            copyToClipboard(password_generated_result)
        }

        password_bits.text = getString(R.string.bits, 0)

        setPlurals()
        generatePassword()

        password_input.doOnTextChanged { text, _, _, _ ->
            password_result.text = passwordHelper.convert(text)
            copy_password.isEnabled = text?.length != 0

            val bits: Int = passwordHelper.calculateStrength(password_result.text.toString())
            password_bits.text = getString(R.string.bits, bits)
            password_complexity.drawable.level = bits
        }

        password_length_seekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                symbolsQuantity = MIN_QUANTITY + progress
                setPlurals()
                generatePassword()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                //leave this empty
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                //leave this empty
            }

        })

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
            when (item.itemId) {
                R.id.action_show_saved_passwords -> {
                    startActivity(Intent(this, SavedPasswordsActivity::class.java))
                    true
                }
                else -> super.onOptionsItemSelected(item)
            }

    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
        generatePassword()
    }

    private fun generatePassword() {
        password_generated_result.text = passwordHelper.generatePassword(symbolsQuantity,
                check_caps.isChecked, check_symbols.isChecked, check_numbers.isChecked)

        val bits = passwordHelper.calculateStrength(password_generated_result.text.toString())
        password_generated_bits.text = getString(R.string.bits, bits)
        password_generated_complexity.drawable.level = bits
    }

    private fun setPlurals() {
        val symbols: String = resources.getQuantityString(R.plurals.symbols_count, symbolsQuantity,
                symbolsQuantity)
        password_length?.text = symbols
    }

    private fun copyToClipboard(password: TextView) {
        val manager: ClipboardManager =
                getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        manager.let {
            it.setPrimaryClip(
                    ClipData.newPlainText(getString(R.string.password), password.text.toString()))
            Toast.makeText(this, R.string.toast_text_copied, Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        private const val MIN_QUANTITY = 4
    }
}