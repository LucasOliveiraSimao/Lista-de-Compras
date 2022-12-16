package com.lucassimao.listadecompras.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.lucassimao.listadecompras.data.model.PurchaseModel

@Database(entities = [PurchaseModel::class], version = 1)
abstract class PurchaseDatabase : RoomDatabase() {
    abstract val purchaseDAO: PurchaseDAO
}