package com.lucassimao.listadecompras.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.lucassimao.listadecompras.R
import com.lucassimao.listadecompras.databinding.FragmentInsertBinding
import com.lucassimao.listadecompras.ui.PurchaseViewModel
import com.lucassimao.listadecompras.utils.isFieldValid
import com.lucassimao.listadecompras.utils.warningMessage
import org.koin.androidx.viewmodel.ext.android.viewModel

class InsertFragment : Fragment() {
    private lateinit var binding: FragmentInsertBinding
    private val viewModel by viewModel<PurchaseViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInsertBinding.inflate(inflater, container, false)

        binding.etPurchasePrice

        binding.btnInsert.setOnClickListener {
            checkFields()
        }

        binding.btnCancel.setOnClickListener {
            returnToHomeScreen()
        }

        return binding.root
    }

    private fun checkFields() {

        if (!isFieldValid(binding.etPurchaseName.text)) {
            warningMessage(getString(R.string.insert_empty_name_field_warning)).show()
            return
        }
        if (!isFieldValid(binding.etPurchaseQuantity.text)) {
            warningMessage(getString(R.string.insert_empty_quantity_field_warning)).show()
            return
        }
        if (!isFieldValid(binding.etPurchasePrice.text)) {
            warningMessage(getString(R.string.insert_empty_price_field_warning)).show()
            return
        }
        insertPurchase()
    }

    private fun insertPurchase() {
        val productName = binding.etPurchaseName.text.toString()
        val productQuantity = binding.etPurchaseQuantity.text.toString().toInt()
        val productPrice = binding.etPurchasePrice.text.toString()

        viewModel.insert(productName, productQuantity, productPrice)

        warningMessage(getString(R.string.insert_notice_saved)).show()
        returnToHomeScreen()
    }

    private fun returnToHomeScreen() {
        findNavController().popBackStack()
    }

}