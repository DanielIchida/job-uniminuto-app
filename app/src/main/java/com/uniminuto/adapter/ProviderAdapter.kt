package com.uniminuto.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.uniminuto.R
import com.uniminuto.models.Provider
import com.uniminuto.models.User

class ProviderAdapter(
    private val providers: MutableList<Provider>,
    private val userSession: User,
    private val editClickLister: (Provider,Int) -> Unit,
    private val deleteClickLister: (Int) -> Unit,
    ) : RecyclerView.Adapter<ProviderAdapter.ProviderViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProviderViewHolder {
        val view = LayoutInflater
                .from(parent.context)
            .inflate(R.layout.item_list, parent, false)
        return ProviderViewHolder(view)
    }

    override fun getItemCount(): Int {
        return providers.size
    }

    override fun onBindViewHolder(holder: ProviderViewHolder, position: Int) {
        val provider = providers[position]
        holder.bind(position,provider,editClickLister,deleteClickLister)
    }

    class ProviderViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val document = view.findViewById<TextView>(R.id.item_tx_document)
        private val name = view.findViewById<TextView>(R.id.tx_item_name)
        private val lastName = view.findViewById<TextView>(R.id.tx_lastname_lastname)
        private val email = view.findViewById<TextView>(R.id.item_tx_email)
        private val phone = view.findViewById<TextView>(R.id.item_tx_phone)
        private val address = view.findViewById<TextView>(R.id.tx_item_address)
        private val btnUpdate = view.findViewById<Button>(R.id.item_btn_edit);
        private val btnDelete = view.findViewById<Button>(R.id.item_btn_delete);

        fun bind(position: Int,provider: Provider,editClickLister: (Provider,Int) -> Unit,deleteClickLister: (Int) -> Unit) {
            document.text = provider.nif
            name.text = provider.name
            lastName.text = provider.lastName
            email.text = provider.email
            phone.text = provider.phone
            address.text = provider.address
            btnUpdate.setOnClickListener { editClickLister(provider,position) }
            btnDelete.setOnClickListener { deleteClickLister(position) }
        }
    }
}