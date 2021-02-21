package com.prasunmondal.tech4docs.utils

import android.util.Log

class Applog {
    companion object {

        fun info(string: String, throwable: Throwable) {
            info("", string, throwable)
        }

        fun info(variableName: String, value: Any?, throwable: Throwable) {
            Log.i("APPLOG: ", createString(variableName, value.toString(), throwable))
        }

        fun error(variableName: String, value: Any?, throwable: Throwable) {
            Log.e("APPLOG: ", createString(variableName, value.toString(), throwable))
        }

        fun error(string: String, throwable: Throwable) {
            Log.e("APPLOG: ", createString("", string, throwable))
        }


        private fun createString(variableName: String, string: String, throwable: Throwable): String {
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