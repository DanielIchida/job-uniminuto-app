package com.uniminuto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.uniminuto.databinding.ActivityCarrierBinding

class CarrierActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCarrierBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCarrierBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCarrierChange.setOnClickListener {
            var alertDialog: AlertDialog? = null;
            val builder = AlertDialog.Builder(this)
            val inflater = this.layoutInflater
            val view = inflater.inflate(R.layout.dialog_change_ticket,null)
            builder.setView(view)
            alertDialog = builder.create()
            alertDialog.show()
        }
    }
}