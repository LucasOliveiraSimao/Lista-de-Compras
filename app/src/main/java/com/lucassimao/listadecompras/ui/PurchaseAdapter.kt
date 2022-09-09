package com.lucassimao.listadecompras.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.lucassimao.listadecompras.data.model.PurchaseModel
import com.lucassimao.listadecompras.databinding.ItemPurchaseBinding
import com.lucassimao.listadecompras.utils.formatPrice

class PurchaseAdapter(
    private val onItemClick: (PurchaseModel) -> Unit,
    private val onLongItemClick: (PurchaseModel) -> Unit
) : ListAdapter<PurchaseModel, PurchaseViewHolder>(PurchaseModel) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PurchaseViewHolder {
        return PurchaseViewHolder.from(parent, onItemClick, onLongItemClick)
    }

    override fun onBindViewHolder(holder: PurchaseViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class PurchaseViewHolder(
    private val binding: ItemPurchaseBinding,
    private val onItemClick: (PurchaseModel) -> Unit,
    private val onLongItemClick: (PurchaseModel) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: PurchaseModel) {
        binding.apply {
            itemName.text = item.item_name
            itemQuantity.text = item.item_quantity.toString()
            itemPrice.text = formatPrice(item.item_price.toDouble())
            itemDeletePurchase.setOnClickListener {
                onItemClick(item)
            }
            itemView.setOnClickListener {
                onLongItemClick(item)
                return@setOnClickListener
            }
        }
    }

    companion object {
        fun from(
            parent: ViewGroup,
            onItemClick: (PurchaseModel) -> Unit,
            onLongItemClick: (PurchaseModel) -> Unit
        ): PurchaseViewHolder {
            return PurchaseViewHolder(
                ItemPurchaseBinding.inflate(LayoutInflater.from(parent.context), parent, false),
                onItemClick,
                onLongItemClick
            )
        }
    }
}