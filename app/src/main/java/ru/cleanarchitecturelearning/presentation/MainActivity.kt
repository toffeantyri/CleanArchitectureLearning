package ru.cleanarchitecturelearning.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.shop_item_layout_enabled.view.*
import ru.cleanarchitecturelearning.R
import ru.cleanarchitecturelearning.domain.ShopItem

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    private var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        viewModel.shopList.observe(this) {
            Log.d("MyLog", it.toString())
            showList(it)
        }
    }

    private fun showList(list: List<ShopItem>) {
        linearContainer.removeAllViews()
        for (shopItem in list) {
            val layoutId = if (shopItem.enabled) R.layout.shop_item_layout_enabled
            else R.layout.shop_item_layout_disabled
            val view = layoutInflater.inflate(layoutId, linearContainer, false).apply {
                tvName.text = shopItem.name
                tvCount.text = shopItem.count.toString()
            }
            view.setOnLongClickListener {
                viewModel.changeEnableState(shopItem)
                true
            }
            linearContainer.addView(view)
        }
    }
}
