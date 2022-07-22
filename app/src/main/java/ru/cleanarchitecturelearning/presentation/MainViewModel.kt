package ru.cleanarchitecturelearning.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.cleanarchitecturelearning.data.ShopListRepositoryImpl
import ru.cleanarchitecturelearning.domain.*

class MainViewModel : ViewModel() {

    private val repo = ShopListRepositoryImpl   //need DI

    private val getShopListUseCase = GetShopListUseCase(repo)
    private val deleteShopItemUseCase = DeleteShopItemUseCase(repo)
    private val editShopItemUseCase = EditShopItemUseCase(repo)

    fun getShopList() {
        val list = getShopListUseCase.getShopList()
        shopList.value = list
    }

    fun delShopItem(shopItem: ShopItem) {
        repo.deleteShopItem(shopItem)
        getShopList()
    }

    fun changeEnableState(shopItem: ShopItem) {
        val newItem = shopItem.copy(enabled = !shopItem.enabled)
        editShopItemUseCase.editShopItem(newItem)
        getShopList()
    }

    val shopList = MutableLiveData<List<ShopItem>>()

}