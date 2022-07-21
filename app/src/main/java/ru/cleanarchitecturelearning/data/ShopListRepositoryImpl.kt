package ru.cleanarchitecturelearning.data

import ru.cleanarchitecturelearning.domain.ShopItem
import ru.cleanarchitecturelearning.domain.ShopListRepository
import java.lang.RuntimeException

object ShopListRepositoryImpl : ShopListRepository {

    private val shopListItem = mutableListOf<ShopItem>()

    private var autoIncId = 0

    override fun addShopItem(shopItem: ShopItem) {
        if (shopItem.id == ShopItem.UNDEFINED_ID) {
            shopItem.id = autoIncId++
        }
        shopListItem.add(shopItem)
    }

    override fun deleteShopItem(shopItem: ShopItem) {
        shopListItem.remove(shopItem)
    }

    override fun editShopItem(shopItem: ShopItem) {
        val oldElementId = getShopItem(shopItem.id)
        shopListItem.remove(oldElementId)
        shopListItem.add(shopItem)
    }

    override fun getShopItem(shopItemId: Int): ShopItem {
        return shopListItem.find { it.id == shopItemId }
            ?: throw RuntimeException("Element by index $shopItemId - not found")
    }

    override fun getShopList(): List<ShopItem> {
        return shopListItem.toList() //toList() - copy orig list
    }
}