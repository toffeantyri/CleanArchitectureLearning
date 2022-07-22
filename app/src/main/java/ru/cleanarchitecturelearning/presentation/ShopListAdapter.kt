package ru.cleanarchitecturelearning.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.cleanarchitecturelearning.R
import ru.cleanarchitecturelearning.domain.ShopItem

class ShopListAdapter : RecyclerView.Adapter<ShopListAdapter.ShopItemViewHolder>() {

    private val list = listOf<ShopItem>()

    class ShopItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        private val textName: TextView = view.findViewById(R.id.tvName)
        private val textCount: TextView = view.findViewById(R.id.tvCount)

        fun bind(shopItem: ShopItem) {
            textName.text = shopItem.name
            textCount.text = shopItem.count.toString()
            view.setOnLongClickListener {

                true
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.shop_item_layout_enabled, parent, false)
        return ShopItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShopItemViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size
}