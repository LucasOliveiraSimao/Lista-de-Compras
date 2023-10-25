package com.lucassimao.listadecompras.utils

import android.os.Bundle
import androidx.navigation.NavType
import com.google.gson.Gson
import com.lucassimao.listadecompras.data.model.PurchaseModel

class CustomType : NavType<PurchaseModel>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): PurchaseModel? {
        return bundle.getParcelable(key)
    }
    override fun parseValue(value: String): PurchaseModel {
        return Gson().fromJson(value, PurchaseModel::class.java)
    }
    override fun put(bundle: Bundle, key: String, value: PurchaseModel) {
        bundle.putParcelable(key, value)
    }
}