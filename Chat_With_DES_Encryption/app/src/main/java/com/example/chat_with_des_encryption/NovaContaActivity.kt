package com.example.chat_with_des_encryption

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class NovaContaActivity : AppCompatActivity() {
    private lateinit var editNome: EditText
    private lateinit var editEmail: EditText
    private lateinit var editSenha: EditText
    private lateinit var botaoCriarConta: Button
    private lateinit var mAuth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sing_up)

        mAuth = FirebaseAuth.getInstance()

        editNome = findViewById(R.id.edit_text_nome)
        editEmail = findViewById(R.id.edit_text_email)
        editSenha = findViewById(R.id.edit_text_senha)
        botaoCriarConta = findViewById(R.id.botao_nova_conta)

        botaoCriarConta.setOnClickListener {
            val email = editEmail.text.toString()
            val senha = editSenha.text.toString()
            
            criarConta(email, senha)
        }
    }

    private fun criarConta(email: String, senha: String) {
        // Lógica para criar uma nova conta
        mAuth.createUserWithEmailAndPassword(email, senha)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    vaiParaMain()
                } else {
                    Toast.makeText(this@NovaContaActivity,"Algo deu errado. Tente novamente", Toast.LENGTH_SHORT).show()
                }
            }

    }

    private fun vaiParaMain() {
        val intent = Intent(this@NovaContaActivity, MainActivity::class.java)
        startActivity(intent)
    }


}