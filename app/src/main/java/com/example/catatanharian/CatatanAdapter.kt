package com.example.catatanharian

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class CatatanAdapter(private var catatan: List<Catatan>, context: Context) : RecyclerView.Adapter<CatatanAdapter.CatatanViewHolder>() {

    private val db: CatatanDatabaseHelper = CatatanDatabaseHelper(context)

    class CatatanViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        val contentTextView: TextView = itemView.findViewById(R.id.contentTextView)
        val updateButton: ImageView = itemView.findViewById(R.id.updateButton)
        val deleteButton: ImageView = itemView.findViewById(R.id.deleteButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatatanViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.catatan_item, parent, false)
        return CatatanViewHolder(view)
    }

    override fun getItemCount(): Int = catatan.size

    override fun onBindViewHolder(holder: CatatanViewHolder, position: Int) {
        val currentCatatan = catatan[position]
        holder.titleTextView.text = currentCatatan.title
        holder.contentTextView.text = currentCatatan.content

//        untuk update
        holder.updateButton.setOnClickListener {
            val intent = Intent(holder.itemView.context, UpdateCatatanActivity::class.java).apply {
                putExtra("catatan_id", currentCatatan.id)

            }
            holder.itemView.context.startActivity(intent)
        }

        holder.deleteButton.setOnClickListener {
            db.deleteCatatan(currentCatatan.id)
            refreshData(db.getAllCatatan())
            Toast.makeText(holder.itemView.context, "Catatan Berhasil Dihapus!", Toast.LENGTH_SHORT).show()

        }
    }

    fun refreshData(newCatatan: List<Catatan>) {
        catatan = newCatatan
        notifyDataSetChanged()
    }


}
