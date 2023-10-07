package com.example.newtestapplication.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newtestapplication.Modal.Meal
import com.example.newtestapplication.R

class ProductAdapter(
    private val context: Context,
    private val productList: List<Meal>
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    private var itemClickListener: OnItemClickListener? = null
    fun setOnItemClickListener(listener: OnItemClickListener) {
        itemClickListener = listener
    }

    inner class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val productImageView: ImageView = itemView.findViewById(R.id.iv_image)
        val product_name: TextView = itemView.findViewById(R.id.tv_product_name)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    itemClickListener?.onItemClick(productList[position])
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.image_item, parent, false)
        return ProductViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productList[position]
        Glide.with(context)
            .load(product.strMealThumb)
            .placeholder(R.drawable.ic_launcher_background)
            .into(holder.productImageView)
        val nameText = product.strMeal
        holder.product_name.text = nameText
    }


    override fun getItemCount(): Int {
        return productList.size
    }

    interface OnItemClickListener {
        fun onItemClick(meal: Meal)
    }
}
