package com.prasunmondal.tech4docs.models

class SessionData {
    var password = ""
    var passwordDecryptCheckString = "thisISaPasswordDecryptyString"

    object Singleton {
        var instance = SessionData()
    }
}