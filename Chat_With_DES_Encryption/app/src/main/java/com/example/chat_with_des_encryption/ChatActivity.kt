package com.example.chat_with_des_encryption

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

class ChatActivity : AppCompatActivity() {
    private lateinit var chatRecyclerView: RecyclerView
    private lateinit var mensagemEditText: EditText
    private lateinit var botaoEnviar: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        val nome = intent.getStringExtra("nome",)
        val uid = intent.getStringExtra("uid")

        supportActionBar?.title = nome

        chatRecyclerView = findViewById(R.id.chat_recycler_view)
        mensagemEditText = findViewById(R.id.edit_text_mensagem)
        botaoEnviar = findViewById(R.id.botao_enviar)

    }
}