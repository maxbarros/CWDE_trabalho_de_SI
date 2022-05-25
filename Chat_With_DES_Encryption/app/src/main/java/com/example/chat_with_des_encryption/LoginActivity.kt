package com.example.chat_with_des_encryption

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {
    private lateinit var editEmail: EditText
    private lateinit var editSenha: EditText
    private lateinit var botaoEntrar: Button
    private lateinit var botaoNovaConta: Button
    private lateinit var mAuth: FirebaseAuth



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mAuth = FirebaseAuth.getInstance()

        editEmail = findViewById(R.id.edit_text_email)
        editSenha = findViewById(R.id.edit_text_senha)
        botaoEntrar = findViewById(R.id.botao_entrar)
        botaoNovaConta = findViewById(R.id.botao_nova_conta)

        botaoEntrar.setOnClickListener {
            val email = editEmail.text.toString() 
            val senha = editSenha.text.toString()
            login(email, senha)
        }



        botaoNovaConta.setOnClickListener {
            vaiParaNovaConta()
        }






    }

    private fun login(email: String, senha: String) {
        // Lógica para entrar em uma conta
        mAuth.signInWithEmailAndPassword(email, senha)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    vaiParaMain()
                } else {
                    Toast.makeText(this@LoginActivity, "Usuário não encontrado.",
                        Toast.LENGTH_SHORT).show()
                }
            }


    }



    private fun vaiParaNovaConta() {
        val intent = Intent(this, NovaContaActivity::class.java)
        startActivity(intent)
    }

    private fun vaiParaMain() {
        val intent = Intent(this@LoginActivity, MainActivity::class.java)
        startActivity(intent)
    }
}