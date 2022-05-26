package com.example.chat_with_des_encryption

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase

class MensagemAdapter(val context: Context, val mensagemList: ArrayList<Mensagem>):
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val ITEM_RECEBIDO = 1
    val ITEM_ENVIADO = 2
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == 1){
            val view: View = LayoutInflater.from(context).inflate(R.layout.recebimento, parent, false)
            return recebimentoViewHolder(view)
        }else{
            val view: View = LayoutInflater.from(context).inflate(R.layout.envio, parent, false)
            return envioViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val mensagemAtual = mensagemList[position]

        if( holder.javaClass == envioViewHolder::class.java){



            val viewHolder = holder as envioViewHolder
            holder.mensagemEnviada.text = mensagemAtual.mensagen

        }else{
            val viewHolder = holder as recebimentoViewHolder
            holder.mensagemRecebida.text = mensagemAtual.mensagen
        }
    }

    override fun getItemViewType(position: Int): Int {
        val mensagemAtual = mensagemList[position]
        if (FirebaseAuth.getInstance().currentUser?.uid.equals(mensagemAtual.remetenteId)){
            return ITEM_ENVIADO
        }else{
            return ITEM_RECEBIDO
        }
    }

    override fun getItemCount(): Int {
        return mensagemList.size

    }

    class envioViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mensagemEnviada = itemView.findViewById<TextView>(R.id.text_mensagem_enviada)
    }

    class recebimentoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mensagemRecebida = itemView.findViewById<TextView>(R.id.text_mensagem_recebida)
    }
}