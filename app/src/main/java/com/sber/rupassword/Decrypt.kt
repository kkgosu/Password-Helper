package com.sber.rupassword

import android.util.Base64
import java.security.KeyStore
import javax.crypto.Cipher
import javax.crypto.SecretKey
import javax.crypto.spec.IvParameterSpec

class Decrypt {

    private var keyStore: KeyStore

    init {
        keyStore = KeyStore.getInstance(ANDROID_KEY_STORE)
        keyStore.load(null)
    }

    fun decryptData(alias: String, decryptString: String): String {
        val parts = decryptString.split(",")
        val encrypted = Base64.decode(parts[0], Base64.DEFAULT)
        val iv = Base64.decode(parts[1], Base64.DEFAULT)
        val cipher = Cipher.getInstance(TRANSFORMATION)
        val spec = IvParameterSpec(iv)
        cipher.init(Cipher.DECRYPT_MODE, getSecretKey(alias), spec)

        return String(cipher.doFinal(encrypted), Charsets.UTF_8)
    }

    private fun getSecretKey(alias: String): SecretKey =
            (keyStore.getEntry(alias, null) as KeyStore.SecretKeyEntry).secretKey

    companion object {
        private const val TRANSFORMATION = "AES/CBC/PKCS7Padding"
        private const val ANDROID_KEY_STORE = "AndroidKeyStore"
    }
}