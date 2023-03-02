package com.uniminuto

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.uniminuto.data.DataUser
import com.uniminuto.databinding.ActivityMainBinding
import com.uniminuto.models.Roles

class MainActivity : AppCompatActivity() {

    private lateinit var dataUser: DataUser
    private lateinit var binding: ActivityMainBinding

    companion object {
        const val EXTRA_USER = "USER"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        dataUser = DataUser()
        val gson = Gson()
        binding.btnIngresar.setOnClickListener {
            val currentUser = dataUser.login(binding.editName.text.toString(),
                binding.editPassword.text.toString())
            if (currentUser != null) {
                val intent: Intent = when(currentUser.rol) {
                    Roles.MANAGER -> Intent(this,ManagerActivity::class.java)
                    Roles.ADMIN -> Intent(this,AdminActivity::class.java)
                    Roles.RECEPTIONIST -> Intent(this,ReceptionistActivity::class.java)
                    Roles.CARRIER -> Intent(this,CarrierActivity::class.java)
                    Roles.WAREHOUSE -> Intent(this,WareHouseActivity::class.java)
                }
                val jsonUser = gson.toJson(currentUser)
                intent.putExtra(EXTRA_USER,jsonUser)
                startActivity(intent)
            } else {
                Snackbar.make(
                    binding.formLogin,"Error de usuario",
                    BaseTransientBottomBar.LENGTH_SHORT)
                    .show()
            }
        }
    }
}