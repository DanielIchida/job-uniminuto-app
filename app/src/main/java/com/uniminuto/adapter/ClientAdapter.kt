package com.uniminuto.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.uniminuto.R
import com.uniminuto.models.Client
import com.uniminuto.models.User

class ClientAdapter(private val clients: MutableList<Client>,
                    private val clientSession: User,
                    private val editClickLister: (Client,Int) -> Unit,
                    private val deleteClickLister: (Int) -> Unit,
) : RecyclerView.Adapter<ClientAdapter.ClientViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClientViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_list, parent, false)
        return ClientViewHolder(view)
    }

    override fun getItemCount(): Int {
        return clients.size
    }

    override fun onBindViewHolder(holder: ClientViewHolder, position: Int) {
        val client = clients[position]
        holder.bind(position,client,editClickLister,deleteClickLister)

    }

    class ClientViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val document = view.findViewById<TextView>(R.id.item_tx_document)
        private val name = view.findViewById<TextView>(R.id.tx_item_name)
        private val lastName = view.findViewById<TextView>(R.id.tx_lastname_lastname)
        private val email = view.findViewById<TextView>(R.id.item_tx_email)
        private val phone = view.findViewById<TextView>(R.id.item_tx_phone)
        private val address = view.findViewById<TextView>(R.id.tx_item_address)
        private val btnUpdate = view.findViewById<Button>(R.id.item_btn_edit);
        private val btnDelete = view.findViewById<Button>(R.id.item_btn_delete);

        fun bind(position: Int,client: Client,editClickLister: (Client,Int) -> Unit,deleteClickLister: (Int) -> Unit) {
             document.text = client.document
             name.text = client.name
             lastName.text = client.lastName
             email.text = client.email
             phone.text = client.phone
             address.text = client.address
             btnUpdate.setOnClickListener { editClickLister(client,position) }
             btnDelete.setOnClickListener { deleteClickLister(position) }
        }

    }

}