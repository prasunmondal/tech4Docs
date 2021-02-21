package com.prasunmondal.tech4docs.utils

import java.io.*
import java.security.Provider
import java.security.SecureRandom
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.spec.SecretKeySpec





// https://stackoverflow.com/questions/16950833/is-there-an-easy-way-to-encrypt-a-java-object
// https://stackoverflow.com/questions/4275311/how-to-encrypt-and-decrypt-file-in-android/8041442


class DEncryption {
    companion object {

        class CryptoProvider : Provider("Crypto", 1.0, "HARMONY (SHA1 digest; SecureRandom; SHA1withDSA signature)") {
            init {
                put("SecureRandom.SHA1PRNG", "org.apache.harmony.security.provider.crypto.SHA1PRNG_SecureRandomImpl")
                put("SecureRandom.SHA1PRNG ImplementedIn", "Software")
            }
        }

        @Throws(Exception::class)
        fun generateKey(password: String): ByteArray? {
            val keyStart = password.toByteArray(charset("UTF-8"))
            val kgen = KeyGenerator.getInstance("AES")
            val sr: SecureRandom = SecureRandom.getInstance("SHA1PRNG")
            sr.setSeed(keyStart)
            kgen.init(128, sr)
            val skey = kgen.generateKey()
            return skey.encoded
        }

        @Throws(IOException::class)
        fun serialize(obj: Any?): ByteArray? {
            val out = ByteArrayOutputStream()
            val os = ObjectOutputStream(out)
            os.writeObject(obj)
            return out.toByteArray()
        }

        @Throws(IOException::class, ClassNotFoundException::class)
        fun deserialize(data: ByteArray): Any? {
            val in1 = ByteArrayInputStream(data)
            val is1 = ObjectInputStream(in1)
            return is1.readObject()
        }

        @Throws(Exception::class)
        fun encodeFile(key: String, fileData: ByteArray?): ByteArray? {
            val generatedKey = generateKey(key)
            val skeySpec = SecretKeySpec(generatedKey, "AES")
            val cipher = Cipher.getInstance("AES")
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec)
            return cipher.doFinal(fileData)
        }

        @Throws(Exception::class)
        fun decodeFile(key: String, fileData: ByteArray?): ByteArray? {
            val generatedKey = generateKey(key)
            val skeySpec = SecretKeySpec(generatedKey, "AES")
            val cipher = Cipher.getInstance("AES")
            cipher.init(Cipher.DECRYPT_MODE, skeySpec)
            return cipher.doFinal(fileData)
        }
    }
}