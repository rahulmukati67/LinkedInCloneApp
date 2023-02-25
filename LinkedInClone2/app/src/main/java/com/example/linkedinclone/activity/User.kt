package com.example.linkedinclone.activity

class User(var uID: String, var name: String, var emaiil: String) {

    fun getuID(): String {
        return uID
    }

    fun setuID(uID: String) {
        this.uID = uID
    }
}

