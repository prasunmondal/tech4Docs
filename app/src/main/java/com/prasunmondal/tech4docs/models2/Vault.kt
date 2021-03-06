package com.prasunmondal.tech4docs.models

import android.content.Context
import com.prasunmondal.tech4docs.Exceptions.NoVaultExistsException
import com.prasunmondal.tech4docs.Exceptions.PasswordComplexityNotMet
import com.prasunmondal.tech4docs.Exceptions.VaultNotLoaded
import com.prasunmondal.tech4docs.models2.ContainerNode
import com.prasunmondal.tech4docs.models2.Node
import com.prasunmondal.tech4docs.models2.QuestionTemplates
import com.prasunmondal.tech4docs.operations.DataFile
import com.prasunmondal.tech4docs.operations.PasswordManage
import com.prasunmondal.tech4docs.utils.Applog
import java.io.Serializable

class Vault : Serializable {
    var root: ContainerNode
    var questionTemplates: QuestionTemplates

    private constructor(context: Context, password: String) {
        root = ContainerNode(context, "Vault", null)
        questionTemplates = QuestionTemplates("Cards")
    }

    override fun toString(): String {
        return "Vault: " + root
    }

    companion object {
        var password: String = ""
        var instance: Vault? = null
        fun get(context: Context): Vault {
            if (instance == null)
                throw VaultNotLoaded()
            return instance!!
        }

        fun create(context: Context): Vault {
            if (PasswordManage.isValidCreationPassword(password)) {
                if (instance == null)
                    instance = Vault(context, password)
                DataFile.write(context, password)
                Applog.info("Vault creation: Successful", Throwable())
                return instance!!
            }
            Applog.info("Vault creation: Failed", Throwable())
            throw PasswordComplexityNotMet()
        }

        fun load(context: Context, password: String): Vault {
            if(!DataFile.exists(context))
                throw NoVaultExistsException()
            if(!PasswordManage.verifyPassword(context, password))
                throw InvalidPasswordException()

            instance = DataFile.read(context, password)
            return instance!!
        }
    }
}

class InvalidPasswordException : Throwable() {}
