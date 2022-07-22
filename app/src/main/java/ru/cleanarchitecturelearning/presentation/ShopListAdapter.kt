package ru.cleanarchitecturelearning.presentation

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import ru.cleanarchitecturelearning.R
import ru.cleanarchitecturelearning.domain.ShopItem
import java.lang.RuntimeException

class ShopListAdapter : RecyclerView.Adapter<ShopListAdapter.ShopItemViewHolder>() {

    var shopItemList = listOf<ShopItem>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    var count = 0

    class ShopItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val textName: TextView = view.findViewById(R.id.tvName)
        val textCount: TextView = view.findViewById(R.id.tvCount)

        fun bind(shopItem: ShopItem) {
            textName.text = shopItem.name
            textCount.text = shopItem.count.toString()
            view.setOnLongClickListener {
                //todo
                true
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {
        Log.d("MyLog", "onCreateViewHolder ${++count}")

        val layoutId = when (viewType) {
            VIEW_TYPE_ENABLED -> R.layout.shop_item_layout_enabled
            VIEW_TYPE_DISABLED -> R.layout.shop_item_layout_disabled
            else -> throw RuntimeException("Unknown viewType $viewType")
        }
        return ShopItemViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(layoutId, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ShopItemViewHolder, position: Int) {
        holder.bind(shopItemList[position])
    }

    override fun getItemCount(): Int = shopItemList.size

    //проверка и установка viewType который мы получаем в onCreateViewHolder как Int который мы сами придумаем
    override fun getItemViewType(position: Int): Int {
        return if (shopItemList[position].enabled) VIEW_TYPE_ENABLED else VIEW_TYPE_DISABLED
    }

    //когда вью переиспользуется - for example set default values
    override fun onViewRecycled(holder: ShopItemViewHolder) {
        super.onViewRecycled(holder)
        holder.apply {
//            textName.setTextColor(ContextCompat.getColor(holder.itemView.context, android.R.color.white))
//            textName.text = ""
//            textCount.text = ""
        }
    }

    companion object {
        const val VIEW_TYPE_ENABLED = 1
        const val VIEW_TYPE_DISABLED = 0
        const val MAX_POOL_SIZE = 15
    }

}