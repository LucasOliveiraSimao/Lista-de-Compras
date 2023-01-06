package com.lucassimao.listadecompras.data.db

import androidx.room.*
import com.lucassimao.listadecompras.data.model.PurchaseModel
import kotlinx.coroutines.flow.Flow

@Dao
interface PurchaseDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPurchase(purchase: PurchaseModel)

    @Query("SELECT * FROM purchase_table ORDER BY item_id ASC")
    fun getAllPurchase(): Flow<List<PurchaseModel>>

    @Update
    suspend fun updatePurchase(purchase: PurchaseModel)

    @Delete
    suspend fun deletePurchase(purchase: PurchaseModel)

    @Query("DELETE FROM purchase_table")
    suspend fun deleteAllPurchase()
}