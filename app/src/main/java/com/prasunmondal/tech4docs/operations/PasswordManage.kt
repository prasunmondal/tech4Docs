package com.prasunmondal.tech4docs.operations

import android.content.Context
import com.prasunmondal.tech4docs.Constants
import com.prasunmondal.tech4docs.models.InvalidPasswordException
import com.prasunmondal.tech4docs.utils.Applog

class PasswordManage {

    companion object {
        fun verifyPassword(context: Context, password: String): Boolean {
            Applog.info("password", password, Throwable())
            return try {
                DataFile.read(context, password)
                Applog.info("result", true, Throwable())
                true
            } catch (e: InvalidPasswordException) {
                Applog.info("result", false, Throwable())
                false
            }
        }

        fun isValidCreationPassword(password: String): Boolean {
            var result = password.length >= Constants.passwordLength
            Applog.info("i/p password: ", password, Throwable())
            Applog.info("result: ", result, Throwable())
            return result
        }
    }
}