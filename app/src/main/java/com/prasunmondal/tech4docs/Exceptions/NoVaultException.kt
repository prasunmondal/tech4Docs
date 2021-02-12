package com.prasunmondal.tech4docs.Exceptions

import java.lang.Exception

class NoVaultException: Exception() {

    override fun toString(): String {
        return super.toString()
    }
}

class VaultVerificationError: Exception() {

    override fun toString(): String {
        return super.toString()
    }
}

class VaultNotLoaded: Exception() {

    override fun toString(): String {
        return super.toString()
    }
}

class PasswordComplexityNotMet: Exception() {
    override fun toString(): String {
        return super.toString()
    }
}