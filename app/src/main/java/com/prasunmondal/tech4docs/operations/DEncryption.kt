package com.prasunmondal.tech4docs.operations

import android.annotation.SuppressLint
import com.prasunmondal.tech4docs.utils.Applog
import com.prasunmondal.tech4docs.utils.Bytes
import java.io.*
import java.security.SecureRandom
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.spec.SecretKeySpec

// https://stackoverflow.com/questions/16950833/is-there-an-easy-way-to-encrypt-a-java-object
// https://stackoverflow.com/questions/4275311/how-to-encrypt-and-decrypt-file-in-android/8041442


class DEncryption {
    companion object {

        private var paddingArray: ByteArray = byteArrayOf(7, 1, 2, 7, 8, 6, 1, 6, 6, 6, 3, 2, 5, 7, 1, 2, 7, 8, 6, 1, 6, 6, 6, 3, 2, 5)
        private var minPasswordLengthForEncryptionDecryption = 24

        @Throws(Exception::class)
        fun generateKey(password: String): ByteArray {
            Applog.info("password", password, Throwable())
            val keyStart = password.toByteArray(charset("UTF-8"))
            val kgen = KeyGenerator.getInstance("AES")
            val sr: SecureRandom = SecureRandom.getInstance("SHA1PRNG")
            sr.setSeed(keyStart)
            kgen.init(128, sr)
            val skey = kgen.generateKey()
            Applog.info("skey.encoded", skey.encoded, Throwable())
            return keyStart
        }

        @Throws(IOException::class)
        fun objectToByteArray(obj: Any?): ByteArray {
            val out = ByteArrayOutputStream()
            val os = ObjectOutputStream(out)
            os.writeObject(obj)
            return out.toByteArray()
        }

        @Throws(IOException::class, ClassNotFoundException::class)
        fun byteArrayToObject(data: ByteArray): Any {
            val in1 = ByteArrayInputStream(data)
            val is1 = ObjectInputStream(in1)
            return is1.readObject()
        }

        @SuppressLint("GetInstance")
        @Throws(Exception::class)
        fun encodeFile(fileData: ByteArray, key: String): ByteArray? {
            Applog.info("key", key, Throwable())
            var expandedPassword = stretchPassword(key)
            Applog.info("expandedPassword", expandedPassword, Throwable())
            val generatedKey = generateKey(expandedPassword)
            Applog.info("generatedKey", Bytes.printBytes(generatedKey), Throwable())
            val skeySpec = SecretKeySpec(generatedKey, "AES")
            val cipher = Cipher.getInstance("AES")
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec)
            return cipher.doFinal(fileData)
        }

        @SuppressLint("GetInstance")
        @Throws(Exception::class)
        fun decodeFile(fileData: ByteArray, key: String): ByteArray? {
            Applog.info("key", key, Throwable())
            var expandedPassword = stretchPassword(key)
            Applog.info("expandedPassword", expandedPassword, Throwable())
            val generatedKey = generateKey(expandedPassword)
            Applog.info("generatedKey", Bytes.printBytes(generatedKey), Throwable())
            val skeySpec = SecretKeySpec(generatedKey, "AES")
            val cipher = Cipher.getInstance("AES")
            cipher.init(Cipher.DECRYPT_MODE, skeySpec)
            return cipher.doFinal(fileData)
        }

        private fun dataStartPosition(bytes: ByteArray, paddingPattern: ByteArray): Int {
            var paddingPatternIndex = 0
            for (i in 0..bytes.size - 1) {
                if (paddingPattern[paddingPatternIndex] != bytes[i]) {
                    paddingPatternIndex = 0
                } else {
                    paddingPatternIndex++
                }
                if (paddingPatternIndex == paddingPattern.size)
                    return i + 1
            }
            return -1
        }

        fun removePadding(bytes: ByteArray): ByteArray {
            val dataStartPos = dataStartPosition(bytes, paddingArray)
            Applog.info("paddingPattern", paddingArray, Throwable())
            Applog.info("bytes", bytes, Throwable())
            Applog.info("dataStartPos", dataStartPos, Throwable())
            val dataLength = bytes.size - dataStartPos
            val beforeEncoding = ByteArray(dataLength)

            for (i in dataStartPos until bytes.size) {
                beforeEncoding[i - dataStartPos] = bytes[i]
            }
            return beforeEncoding
        }

        fun addPadding(bytes: ByteArray): ByteArray {
            return paddingArray + bytes
        }

        private fun stretchPassword(password: String): String {
            var generatedPassword = password
            while (generatedPassword.length < minPasswordLengthForEncryptionDecryption) {
                generatedPassword += password
            }
            Applog.info("generatedPassword", generatedPassword, Throwable())
            Applog.endMethod(Throwable())
            return generatedPassword
        }
    }
}