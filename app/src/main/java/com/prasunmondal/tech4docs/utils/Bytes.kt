package com.prasunmondal.tech4docs.utils

class Bytes {
    companion object {
        fun printBytes(bytes: ByteArray?): String {
            if (bytes == null)
                return "null"
            val str = StringBuilder("")
            for (i in bytes.indices) {
                str.append(bytes[i].toString() + ",")
            }
            return str.toString()
        }
    }
}