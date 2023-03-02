package com.uniminuto

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.uniminuto.databinding.ActivityWareHouseBinding
import java.security.SecureRandom

class WareHouseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWareHouseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWareHouseBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val ramdom = SecureRandom()
        val num = 1000000
        val consecutivo = ramdom.nextInt(num)
        val ticket = ramdom.nextInt(num)
        binding.packEditCode.setText(consecutivo.toString())
        binding.packEditTack.setText(ticket.toString())
        binding.btnTicketWare.setOnClickListener {
            val alertDialog: AlertDialog?
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Reempacar")
                .setMessage("Se ha generado la etiqueta correctamente")
                .setNegativeButton("Salir") { dialog: DialogInterface, _: Int ->
                    dialog.dismiss()
                }
            alertDialog = builder.create()
            alertDialog.show()
        }
    }
}