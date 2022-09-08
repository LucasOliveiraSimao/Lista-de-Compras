package com.lucassimao.listadecompras.data

import com.lucassimao.listadecompras.data.db.PurchaseDAO
import com.lucassimao.listadecompras.data.model.PurchaseModel

class PurchaseRepository(private val dao: PurchaseDAO) {

    val getAllPurchase = dao.getAllPurchase()

    suspend fun insert(purchase: PurchaseModel) {
        return dao.insertPurchase(purchase)
    }

    suspend fun update(purchase: PurchaseModel) {
        return dao.updatePurchase(purchase)
    }

    suspend fun delete(purchase: PurchaseModel) {
        return dao.deletePurchase(purchase)
    }
}