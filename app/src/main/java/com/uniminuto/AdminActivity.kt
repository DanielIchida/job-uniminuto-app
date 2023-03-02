package com.uniminuto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.uniminuto.databinding.ActivityAdminBinding

class AdminActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAdminBinding
    private lateinit var intent: Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val result = getIntent()
        binding.btnClients.setOnClickListener {
            intent = Intent(this,AdminClientActivity::class.java)
            intent.putExtra(MainActivity.EXTRA_USER,result.getStringExtra(MainActivity.EXTRA_USER))
            startActivity(intent)
        }
        binding.btnProvider.setOnClickListener {
            intent = Intent(this,AdminProvidersActivity::class.java)
            intent.putExtra(MainActivity.EXTRA_USER,result.getStringExtra(MainActivity.EXTRA_USER))
            startActivity(intent)
        }
    }
}