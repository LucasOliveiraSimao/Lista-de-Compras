package com.lucassimao.listadecompras.ui.fragments.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.lucassimao.listadecompras.data.model.PurchaseModel
import com.lucassimao.listadecompras.databinding.ItemPurchaseBinding
import com.lucassimao.listadecompras.utils.putBrazilianMoneySymbol
import com.lucassimao.listadecompras.utils.putTimesSymbol
import com.lucassimao.listadecompras.utils.showPopupMenu

class PurchaseAdapter : ListAdapter<PurchaseModel, PurchaseViewHolder>(PurchaseModel) {

    var deletePurchase: (PurchaseModel) -> Unit = {}
    var updatePurchase: (PurchaseModel) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PurchaseViewHolder {
        return PurchaseViewHolder.from(parent, deletePurchase, updatePurchase)
    }

    override fun onBindViewHolder(holder: PurchaseViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class PurchaseViewHolder(
    private val binding: ItemPurchaseBinding,
    private val deletePurchase: (PurchaseModel) -> Unit,
    private val updatePurchase: (PurchaseModel) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: PurchaseModel) {
        binding.apply {
            itemName.text = item.item_name
            itemQuantity.text = item.item_quantity.putTimesSymbol()
            itemPrice.text = item.item_price.putBrazilianMoneySymbol()

            itemMorePurchase.setOnClickListener {
                showPopupMenu(item, itemMorePurchase, deletePurchase, updatePurchase)
            }

        }
    }

    companion object {
        fun from(
            parent: ViewGroup,
            deletePurchase: (PurchaseModel) -> Unit,
            updatePurchase: (PurchaseModel) -> Unit
        ): PurchaseViewHolder {
            return PurchaseViewHolder(
                ItemPurchaseBinding.inflate(LayoutInflater.from(parent.context), parent, false),
                deletePurchase,
                updatePurchase
            )
        }
    }
}