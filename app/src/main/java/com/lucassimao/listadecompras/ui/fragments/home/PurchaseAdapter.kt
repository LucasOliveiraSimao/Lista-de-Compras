package com.lucassimao.listadecompras.ui.fragments.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.lucassimao.listadecompras.data.model.PurchaseModel
import com.lucassimao.listadecompras.databinding.ItemPurchaseBinding
import com.lucassimao.listadecompras.utils.putBrazilianMoneySymbol
import com.lucassimao.listadecompras.utils.putTimesSymbol

class PurchaseAdapter : ListAdapter<PurchaseModel, PurchaseViewHolder>(PurchaseModel) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PurchaseViewHolder {
        return PurchaseViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: PurchaseViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class PurchaseViewHolder(
    private val binding: ItemPurchaseBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: PurchaseModel) {
        binding.apply {
            itemName.text = item.item_name
            itemQuantity.text = item.item_quantity.putTimesSymbol()
            itemPrice.text = item.item_price.putBrazilianMoneySymbol()
        }
    }

    companion object {
        fun from(parent: ViewGroup): PurchaseViewHolder {
            return PurchaseViewHolder(
                ItemPurchaseBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
        }
    }
}