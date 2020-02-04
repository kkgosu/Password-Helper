package com.sber.rupassword

import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import android.util.Base64
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey

class Encrypt {
    private lateinit var encryption: ByteArray
    private lateinit var iv: ByteArray

    fun encryptText(alias: String, textToEncrypt: String): String {
        val cipher = Cipher.getInstance(TRANSFORMATION)
        cipher.init(Cipher.ENCRYPT_MODE, getSecretKey(alias))
        encryption = cipher.doFinal(textToEncrypt.toByteArray())
        iv = cipher.iv
        return Base64.encodeToString(encryption, Base64.DEFAULT) + "," + Base64.encodeToString(iv,
                Base64.DEFAULT)
    }

    private fun getSecretKey(alias: String): SecretKey {
        val keyGenerator = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES,
                ANDROID_KEY_STORE)
        keyGenerator.init(KeyGenParameterSpec.Builder(alias,
                KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT)
                .setKeySize(256)
                .setBlockModes(KeyProperties.BLOCK_MODE_CBC)
                .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_PKCS7)
                .setRandomizedEncryptionRequired(true)
                .build())
        return keyGenerator.generateKey()
    }

    companion object {
        private const val TRANSFORMATION = "AES/CBC/PKCS7Padding"
        private const val ANDROID_KEY_STORE = "AndroidKeyStore"
    }
}