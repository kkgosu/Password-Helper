package com.sber.rupassword.auth

class Account(val userName: String, val password: String) {
    override fun equals(other: Any?): Boolean {
        val otherAccount = other as? Account ?: return false

        return otherAccount.userName == userName && otherAccount.password == password
    }

    override fun hashCode(): Int {
        var result = userName.hashCode()
        result = 31 * result + password.hashCode()
        return result
    }
}