package com.example.newtestapplication.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newtestapplication.Modal.Meal
import com.example.newtestapplication.R

class menuAdapter(private val context: Context, private val menuList: LiveData<List<Meal>>) :
    RecyclerView.Adapter<menuAdapter.menuViewHolder>() {

    private var data: List<Meal> = emptyList()

    inner class menuViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val textViewName: TextView = itemView.findViewById(R.id.textViewName)
        private val textViewDescription: TextView = itemView.findViewById(R.id.textViewDescription)
        private val imageViewItem: ImageView = itemView.findViewById(R.id.imageViewItem)
        fun bind(item: Meal) {
            textViewName.text = item.strMeal
            textViewDescription.text = item.strMealThumb
            Glide.with(context)
                .load(item.strMealThumb)
                .placeholder(R.drawable.ic_launcher_background) // Placeholder image
                .into(imageViewItem)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): menuViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_layout, parent, false)
        return menuViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: menuViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun submitList(newData: List<Meal>) {
        data = newData
        notifyDataSetChanged()
    }
}