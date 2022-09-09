package com.lucassimao.listadecompras.data.model

import android.os.Parcelable
import androidx.recyclerview.widget.DiffUtil
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "purchase_table")
@Parcelize
data class PurchaseModel(
    @PrimaryKey(autoGenerate = true)
    val item_id: Int,
    @ColumnInfo(name = "item_name")
    val item_name: String,
    @ColumnInfo(name = "item_quantity")
    val item_quantity: Int,
    @ColumnInfo(name = "item_price")
    val item_price: String
) : Parcelable {
    companion object : DiffUtil.ItemCallback<PurchaseModel>() {
        override fun areItemsTheSame(oldItem: PurchaseModel, newItem: PurchaseModel): Boolean {
            return oldItem.item_id == newItem.item_id
        }

        override fun areContentsTheSame(oldItem: PurchaseModel, newItem: PurchaseModel): Boolean {
            return oldItem == newItem
        }
    }
}
