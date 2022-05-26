package com.example.chat_with_des_encryption

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class ChatActivity : AppCompatActivity() {
    private lateinit var chatRecyclerView: RecyclerView
    private lateinit var mensagemEditText: EditText
    private lateinit var botaoEnviar: ImageView
    private lateinit var mensagemAdapter: MensagemAdapter
    private lateinit var mensagemList: ArrayList<Mensagem>
    private lateinit var mDbRef: DatabaseReference

    var areaDoDestinatario: String? = null
    var areaDoRemetente: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        val nome = intent.getStringExtra("nome",)

        val destinatarioUid = intent.getStringExtra("uid")

        val remetenteUid = FirebaseAuth.getInstance().currentUser?.uid

        mDbRef = FirebaseDatabase.getInstance().getReference()

        areaDoRemetente = destinatarioUid + remetenteUid
        areaDoDestinatario = remetenteUid + destinatarioUid

        supportActionBar?.title = nome

        chatRecyclerView = findViewById(R.id.chat_recycler_view)
        mensagemEditText = findViewById(R.id.edit_text_mensagem)
        botaoEnviar = findViewById(R.id.botao_enviar)
        mensagemList = ArrayList()
        mensagemAdapter = MensagemAdapter(this, mensagemList)

        chatRecyclerView.layoutManager = LinearLayoutManager(this)
        chatRecyclerView.adapter = mensagemAdapter
        mDbRef.child("chats").child(areaDoRemetente!!).child("mensagens")
            .addValueEventListener(object: ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {

                    mensagemList.clear()

                    for (postSnapShot in snapshot.children){
                        val mensagem = postSnapShot.getValue(Mensagem::class.java)
                        mensagemList.add(mensagem!!)
                    }
                    mensagemAdapter.notifyDataSetChanged()
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            })

        botaoEnviar.setOnClickListener {
            val mensagem = mensagemEditText.text.toString()
            val mensagemObject = Mensagem(mensagem,remetenteUid)

            mDbRef.child("chats").child(areaDoRemetente!!).child("mensagens").push()
                .setValue(mensagemObject).addOnSuccessListener {

                    mDbRef.child("chats").child(areaDoDestinatario!!).child("mensagens").push()
                        .setValue(mensagemObject)
                }
            mensagemEditText.setText("")
        }

    }
}