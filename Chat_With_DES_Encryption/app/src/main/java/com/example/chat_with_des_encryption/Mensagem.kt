package com.example.chat_with_des_encryption

class Mensagem {
    var mensagem: String? = null
    var remetenteId: String? = null

    constructor(){}

    constructor(mensagem: String, remetenteId: String?){
        this.mensagem = mensagem
        this.remetenteId = remetenteId
    }
}