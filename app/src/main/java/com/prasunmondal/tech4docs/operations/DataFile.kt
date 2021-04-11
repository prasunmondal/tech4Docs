package com.prasunmondal.tech4docs.operations

import android.content.Context
import com.prasunmondal.tech4docs.Constants
import com.prasunmondal.tech4docs.models.InvalidPasswordException
import com.prasunmondal.tech4docs.models.Vault
import com.prasunmondal.tech4docs.utils.Applog
import com.prasunmondal.tech4docs.utils.FileIO
import java.io.FileNotFoundException
import javax.crypto.BadPaddingException

class DataFile {
    companion object {
        fun exists(context: Context): Boolean {
            return try {
                FileIO().ReadBytesFromFile(context, Constants.FILENAME_PHONEBOOK)
                Applog.info("return", true, Throwable())
                true
            } catch (e: FileNotFoundException) {
                Applog.info("Vault Not Found", Throwable())
                Applog.info("return", false, Throwable())
                false
            }
        }

        fun read(context: Context, key: String): Vault? {
            // read the vault from the file and decrypt it!
            try {
                var beforeDecoding = FileIO().ReadBytesFromFile(context, Constants.FILENAME_PHONEBOOK)
                var removePadding = DEncryption.removePadding(beforeDecoding)
                var afterDecoding = DEncryption.decodeFile(removePadding, key)!!
                var vault = DEncryption.byteArrayToObject(afterDecoding) as Vault
                return vault
            } catch (e: BadPaddingException) {
                throw InvalidPasswordException()
            }
        }

        fun write(context: Context, key: String) {
            // write the vault to a file after encrypting it!
            Applog.info("Data Write: \n" + Vault.instance, Throwable())
            var beforeEncoding: ByteArray = DEncryption.objectToByteArray(Vault.instance)
            var afterEncoding: ByteArray = DEncryption.encodeFile(beforeEncoding, key)!!
            var padded = DEncryption.addPadding(afterEncoding)
            FileIO().WriteBytesToFile(context, Constants.FILENAME_PHONEBOOK, padded)
        }
    }
}