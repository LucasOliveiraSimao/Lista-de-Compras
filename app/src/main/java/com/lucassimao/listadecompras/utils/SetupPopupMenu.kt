package com.lucassimao.listadecompras.utils

import android.widget.ImageView
import android.widget.PopupMenu
import com.lucassimao.listadecompras.R
import com.lucassimao.listadecompras.data.model.PurchaseModel

fun showPopupMenu(
    item: PurchaseModel,
    itemMorePurchase: ImageView,
    deletePurchase: (PurchaseModel) -> Unit
) {
    val popupMenu = PopupMenu(itemMorePurchase.context, itemMorePurchase)
    popupMenu.menuInflater.inflate(R.menu.popup_menu, popupMenu.menu)
    popupMenu.setOnMenuItemClickListener {
        when (it.itemId) {
            R.id.action_delete -> {
                deletePurchase(item)
            }
        }
        return@setOnMenuItemClickListener true
    }
    popupMenu.show()
}