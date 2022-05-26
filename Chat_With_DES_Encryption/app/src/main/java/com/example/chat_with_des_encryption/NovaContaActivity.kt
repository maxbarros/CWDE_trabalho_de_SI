package com.example.chat_with_des_encryption

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class NovaContaActivity : AppCompatActivity() {
    private lateinit var editNome: EditText
    private lateinit var editEmail: EditText
    private lateinit var editSenha: EditText
    private lateinit var botaoCriarConta: Button
    private lateinit var mAuth: FirebaseAuth
    private lateinit var mDBRef: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sing_up)

        supportActionBar?.hide()

        mAuth = FirebaseAuth.getInstance()

        editNome = findViewById(R.id.edit_text_nome)
        editEmail = findViewById(R.id.edit_text_email)
        editSenha = findViewById(R.id.edit_text_senha)
        botaoCriarConta = findViewById(R.id.botao_criar_conta)

        botaoCriarConta.setOnClickListener {
            val nome = editNome.text.toString()
            val email = editEmail.text.toString()
            val senha = editSenha.text.toString()
            
            criarConta(nome, email, senha)
        }
    }

    private fun criarConta(nome: String, email: String, senha: String) {
        // Lógica para criar uma nova conta
        mAuth.createUserWithEmailAndPassword(email, senha)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    salvarContaNoBancoDeDados(nome, email, mAuth.currentUser?.uid!!)
                    vaiParaMain()
                } else {
                    Toast.makeText(this@NovaContaActivity,"Algo deu errado. Tente novamente", Toast.LENGTH_SHORT).show()
                }
            }

    }

    private fun salvarContaNoBancoDeDados(nome: String, email: String, uid: String) {
        mDBRef = FirebaseDatabase.getInstance().getReference()
        mDBRef.child("user").child(uid).setValue(User(nome, email, uid))

    }

    private fun vaiParaMain() {
        val intent = Intent(this@NovaContaActivity, MainActivity::class.java)
        finish()
        startActivity(intent)
    }


}