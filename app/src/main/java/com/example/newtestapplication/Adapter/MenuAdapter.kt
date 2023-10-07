import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newtestapplication.Modal.Meal
import com.example.newtestapplication.R

class MenuAdapter(
    private val context: Context,
    private val itemClickListener: OnItemClickListener,
    private val deleteItemClickListener: OnDeleteItemClickListener
) :
    ListAdapter<Meal, MenuAdapter.MenuViewHolder>(DiffCallback) {

    inner class MenuViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textViewName: TextView = itemView.findViewById(R.id.textViewName)
        private val textViewDescription: TextView = itemView.findViewById(R.id.textViewDescription)
        private val imageViewItem: ImageView = itemView.findViewById(R.id.imageViewItem)
        val btnSave: Button = itemView.findViewById(R.id.btn_save)

        init {
            // Set a click listener for the btnSave button
            btnSave.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val item = getItem(position)
                    deleteItemClickListener.onDeleteItemClick(item)
                }
            }

            // Set a click listener for the whole item (if needed)
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val item = getItem(position)
                    itemClickListener.onItemClick(item)
                }
            }
        }


        fun bind(item: Meal) {
            textViewName.text = item.strMeal
            textViewDescription.text = item.strMealThumb
            Glide.with(context)
                .load(item.strMealThumb)
                .placeholder(R.drawable.ic_launcher_background)
                .into(imageViewItem)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_layout, parent, false)
        return MenuViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    interface OnItemClickListener {
        fun onItemClick(meal: Meal)
    }

    interface OnDeleteItemClickListener {
        fun onDeleteItemClick(meal: Meal)
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Meal>() {
            override fun areItemsTheSame(oldItem: Meal, newItem: Meal): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Meal, newItem: Meal): Boolean {
                // Implement your logic to check if the contents of the items are the same.
                // If the ID is the same, you might want to check other properties to decide.
                // For simplicity, we'll assume they are always the same.
                return true
            }
        }
    }
}
