package com.lucassimao.listadecompras.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.lucassimao.listadecompras.data.PurchaseRepository
import com.lucassimao.listadecompras.data.model.PurchaseModel
import kotlinx.coroutines.launch

class PurchaseViewModel(
    private val repository: PurchaseRepository
) : ViewModel() {

    val getAllPurchase = liveData {
        emitSource(
            repository.getAllPurchase
                .asLiveData()
        )
    }

    fun insert(purchaseItem: PurchaseModel) {
        viewModelScope.launch {
            repository.insert(purchaseItem)
        }
    }

    fun update(purchaseItem: PurchaseModel) {
        viewModelScope.launch {
            repository.update(purchaseItem)
        }
    }

    fun delete(purchaseItem: PurchaseModel) {
        viewModelScope.launch {
            repository.delete(purchaseItem)
        }
    }

}