package com.example.chat_with_des_encryption

class Mensagem {
    var mensagen: String? = null
    var remetenteId: String? = null

    constructor(){}

    constructor(mensagem: Mensagem, remetenteId: String){
        this.mensagen = mensagen
        this.remetenteId = remetenteId
    }
}