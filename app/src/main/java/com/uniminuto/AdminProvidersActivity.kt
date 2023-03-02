package com.uniminuto

import android.annotation.SuppressLint
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.uniminuto.adapter.ProviderAdapter
import com.uniminuto.databinding.ActivityAdminProvidersBinding
import com.uniminuto.models.Client
import com.uniminuto.models.Provider
import com.uniminuto.models.User

class AdminProvidersActivity : AppCompatActivity() {

    private lateinit var providers: MutableList<Provider>
    private lateinit var binding: ActivityAdminProvidersBinding
    private lateinit var adapter: ProviderAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminProvidersBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val gson = Gson()
        providers = mutableListOf()
        providers.add(Provider(1,"X1111111","Provider 1","LastName 1","555555","Cll 1","example@gmail.com"))
        providers.add(Provider(2,"R2222222","Provider 2","LastName 2","555555","Cll 1","example@gmail.com"))
        providers.add(Provider(3,"T3333333","Provider 3","LastName 3","555555","Cll 1","example@gmail.com"))
        val result = intent
        val providerSession = gson.fromJson(result.getStringExtra(MainActivity.EXTRA_USER), User::class.java)
        val adapter = ProviderAdapter(providers,providerSession,{c,i -> editProvider(c,i)},{i -> deleteProvider(i)})
        binding.rcListProvider.adapter = adapter
        binding.rcListProvider.layoutManager = LinearLayoutManager(this)
        binding.btnAddProvider.setOnClickListener {
            createCrudDialog(true,null,-1)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun deleteProvider(i: Int) {
        val alertDialog: AlertDialog?
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Eliminacion de Proveedor")
            .setMessage("Seguro que desea eliminar el proveedor?")
            .setPositiveButton("OK") { _: DialogInterface, _: Int ->
                providers.removeAt(i)
                adapter.notifyDataSetChanged()
            }
            .setNegativeButton("Cancel") { dialog: DialogInterface, _: Int ->
                dialog.dismiss()
            }
        alertDialog = builder.create()
        alertDialog.show()
    }

    private fun editProvider(c: Provider, i: Int) {
        createCrudDialog(false,c,i)
    }

    @SuppressLint("InflateParams", "NotifyDataSetChanged")
    fun createCrudDialog(isNew: Boolean, provider: Provider?, position: Int){
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
            txTitle.text = getString(R.string.title_form_new_provider)
        } else {
            txTitle.text = getString(R.string.title_form_edit_provider)
            btnAdd.setText(R.string.title_edit)
            document.setText(provider?.nif)
            name.setText(provider?.name)
            lastName.setText(provider?.lastName)
            email.setText(provider?.email)
            phone.setText(provider?.phone)
            address.setText(provider?.address)
        }

        btnCancel.setOnClickListener {
            alertDialog?.dismiss()
        }

        btnAdd.setOnClickListener {
            val newProvider = Provider(
                providers.size + 1,
                document.text.toString(),
                name.text.toString(),
                lastName.text.toString(),
                phone.text.toString(),
                address.text.toString(),
                email.text.toString())
            if(isNew) {
                providers.add(newProvider)
                adapter.notifyDataSetChanged()
            } else {
                providers[position] = newProvider
                adapter.notifyDataSetChanged()
            }
            alertDialog?.dismiss()
        }
        alertDialog = builder.create()
        alertDialog.show()
    }
}
