package ru.cleanarchitecturelearning.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.cleanarchitecturelearning.domain.ShopItem
import ru.cleanarchitecturelearning.domain.ShopListRepository
import java.lang.RuntimeException
import kotlin.random.Random

object ShopListRepositoryImpl : ShopListRepository {

    private val shopListLiveData = MutableLiveData<List<ShopItem>>()

    private val shopListItem = sortedSetOf<ShopItem>({ o1, o2 ->
        o1.id.compareTo(o2.id)
    })

    init {
        for (i in 0 until 1000) {
            val item = ShopItem("Name $i", i, Random.nextBoolean())
            addShopItem(item)
        }
    }

    private var autoIncId = 0

    override fun addShopItem(shopItem: ShopItem) {
        if (shopItem.id == ShopItem.UNDEFINED_ID) {
            shopItem.id = autoIncId++
        }
        shopListItem.add(shopItem)
        updateList()
    }

    override fun deleteShopItem(shopItem: ShopItem) {
        shopListItem.remove(shopItem)
        updateList()
    }

    override fun editShopItem(shopItem: ShopItem) {
        val oldElementId = getShopItem(shopItem.id)
        shopListItem.remove(oldElementId)
        shopListItem.add(shopItem)
        updateList()
    }

    override fun getShopItem(shopItemId: Int): ShopItem {
        return shopListItem.find { it.id == shopItemId }
            ?: throw RuntimeException("Element by index $shopItemId - not found")
    }

    override fun getShopList(): LiveData<List<ShopItem>> {
        return shopListLiveData
    }

    private fun updateList() {
        shopListLiveData.value = shopListItem.toList()
    }
}