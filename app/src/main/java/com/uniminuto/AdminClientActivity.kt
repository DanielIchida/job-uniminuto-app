package com.uniminuto

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.uniminuto.adapter.ClientAdapter
import com.uniminuto.databinding.ActivityAdminClientBinding
import com.uniminuto.models.Client
import com.uniminuto.models.User

class AdminClientActivity : AppCompatActivity() {

    private lateinit var clients: MutableList<Client>
    private lateinit var binding: ActivityAdminClientBinding
    private lateinit var adapter: ClientAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminClientBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val gson = Gson()
        clients = mutableListOf()
        clients.add(Client(1,"1111111","Client 1","LastName 1","555555","Cll 1","example@gmail.com"))
        clients.add(Client(2,"2222222","Client 2","LastName 2","555555","Cll 1","example@gmail.com"))
        clients.add(Client(3,"3333333","Client 3","LastName 3","555555","Cll 1","example@gmail.com"))
        val result = intent
        val clientSession = gson.fromJson(result.getStringExtra(MainActivity.EXTRA_USER),User::class.java)
        adapter = ClientAdapter(clients,clientSession,{c,i -> editClient(c,i)},{i -> deleteClient(i)})
        binding.rcClients.adapter = adapter
        binding.rcClients.layoutManager = LinearLayoutManager(this)

        binding.btnClientAdd.setOnClickListener {
            createCrudDialog(true,null,-1)
        }

    }

    private fun editClient(client: Client?,position: Int) {
        createCrudDialog(false,client,position)
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun deleteClient(position: Int) {
        val alertDialog: AlertDialog?
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Eliminacion de Cliente")
            .setMessage("Seguro que desea eliminar el cliente?")
            .setPositiveButton("OK") { _: DialogInterface, _: Int ->
                 clients.removeAt(position)
                 adapter.notifyDataSetChanged()
            }
            .setNegativeButton("Cancel") { dialog: DialogInterface, _: Int ->
                  dialog.dismiss()
            }
        alertDialog = builder.create()
        alertDialog.show()
    }

    @SuppressLint("InflateParams", "NotifyDataSetChanged")
    fun createCrudDialog(isNew: Boolean, client: Client?,position: Int){
        var alertDialog: AlertDialog? = null;
        val builder = AlertDialog.Builder(this)
        val inflater = this.layoutInflater
        val view = inflater.inflate(R.layout.dialog_form,null)
        builder.setView(view)

        val txTitle = view.findViewById<TextView>(R.id.tx_title_form)
        val document = view.findViewById<EditText>(R.id.edit_tx_identy)
        val name = view.findViewById<EditText>(R.id.edit_tx_name)
        val lastName = view.findViewById<EditText>(R.id.edit_tx_lastname)
        val email = view.findViewById<EditText>(R.id.edit_tx_email)
        val phone = view.findViewById<EditText>(R.id.edit_tx_phone)
        val address = view.findViewById<EditText>(R.id.edit_tx_adress)
        val btnCancel = view.findViewById<Button>(R.id.btn_modal_cancel)
        val btnAdd = view.findViewById<Button>(R.id.btn_modal_add)

        document.setHint(R.string.hint_document)
        if(isNew) {
           txTitle.text = getString(R.string.title_form_new_cliente)
        } else {
           txTitle.text = getString(R.string.title_form_edit_cliente)
           btnAdd.setText(R.string.title_edit)
           document.setText(client?.document)
           name.setText(client?.name)
           lastName.setText(client?.lastName)
           email.setText(client?.email)
           phone.setText(client?.phone)
           address.setText(client?.address)
        }

        btnCancel.setOnClickListener {
            alertDialog?.dismiss()
        }

        btnAdd.setOnClickListener {
            val newClient = Client(
                clients.size + 1,
                document.text.toString(),
                name.text.toString(),
                lastName.text.toString(),
                phone.text.toString(),
                address.text.toString(),
                email.text.toString())
            if(isNew) {
                clients.add(newClient)
                adapter.notifyDataSetChanged()
            } else {
                clients[position] = newClient
                adapter.notifyDataSetChanged()
            }
            alertDialog?.dismiss()
        }
        alertDialog = builder.create()
        alertDialog.show()
    }
}