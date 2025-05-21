package com.example.catatanharian

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.catatanharian.databinding.ActivityUpdateCatatanBinding


class UpdateCatatanActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateCatatanBinding
    private lateinit var  db: CatatanDatabaseHelper
    private var catatanId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUpdateCatatanBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        db = CatatanDatabaseHelper(this)

        catatanId = intent.getIntExtra("catatan_id", -1)
        if(catatanId == -1){
            finish()
            return
        }

        val catatan = db.getCatatanByID(catatanId)
        binding.updateTitleEditText.setText(catatan.title)
        binding.updateContentEditText.setText(catatan.content)

        binding.updateSaveButton.setOnClickListener {
            val newTitle = binding.updateTitleEditText.text.toString()
            val newContent = binding.updateContentEditText.text.toString()
            val updatedCatatan = Catatan(catatanId, newTitle, newContent)
            db.updateCatatan(updatedCatatan)
            finish()
            Toast.makeText(this, "Update Berhasil Disimpan", Toast.LENGTH_SHORT).show()
        }

    }
}