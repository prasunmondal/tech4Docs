package com.prasunmondal.tech4docs.utils

import android.util.Log

class Applog {
    companion object {
        fun info(variableName: String, string: String, throwable: Throwable) {
            Log.i("APPLOG: ", createString(throwable, variableName, string))
        }

        fun error(variableName: String, string: String, throwable: Throwable) {
            Log.e("APPLOG: ", createString(throwable, variableName, string))
        }

        private fun createString(throwable: Throwable, variableName: String, string: String): String {
            return "${className(throwable)}.${methodName(throwable)}().$variableName: $string"
        }

        private fun className(throwable: Throwable): String {
            return throwable
                .stackTrace[0]
                .className
        }

        private fun methodName(throwable: Throwable): String {
            return throwable
                .stackTrace[0]
                .methodName
        }
    }
}