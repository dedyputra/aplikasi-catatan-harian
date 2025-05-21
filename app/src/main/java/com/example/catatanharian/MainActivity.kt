package com.example.catatanharian

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.catatanharian.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var db: CatatanDatabaseHelper
    private lateinit var catatanAdapter: CatatanAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        db = CatatanDatabaseHelper(this)
        catatanAdapter = CatatanAdapter(db.getAllCatatan(), this)

        binding.catatanRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.catatanRecyclerView.adapter = catatanAdapter

        binding.addButton.setOnClickListener {
            val intent = Intent(this, AddCatatanActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onResume() {
        super.onResume()
        catatanAdapter.refreshData(db.getAllCatatan())
    }
}