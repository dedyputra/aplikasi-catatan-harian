package com.example.catatanharian

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.catatanharian.databinding.ActivityAddCatatanBinding

class AddCatatanActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddCatatanBinding
//    database
    private lateinit var db: CatatanDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddCatatanBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        db = CatatanDatabaseHelper(this)

        binding.saveButton.setOnClickListener {
            val title = binding.titleEditText.text.toString()
            val content = binding.contentEditText.text.toString()
            val catatan = Catatan(0, title, content)
            db.insertCatatan(catatan)
            finish()
            Toast.makeText(this, "Simpan Catatan", Toast.LENGTH_SHORT).show()


        }

    }
}