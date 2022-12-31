package com.lucassimao.listadecompras.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.lucassimao.listadecompras.R
import com.lucassimao.listadecompras.data.model.PurchaseModel
import com.lucassimao.listadecompras.databinding.FragmentUpdateBinding
import com.lucassimao.listadecompras.ui.PurchaseViewModel
import com.lucassimao.listadecompras.utils.formatMoneyToBrazilianFormat
import com.lucassimao.listadecompras.utils.isFieldValid
import com.lucassimao.listadecompras.utils.warningMessage
import org.koin.androidx.viewmodel.ext.android.viewModel

class UpdateFragment : Fragment() {
    private lateinit var binding: FragmentUpdateBinding
    private val viewModel by viewModel<PurchaseViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUpdateBinding.inflate(inflater, container, false)

        val bundle = arguments?.getParcelable<PurchaseModel>("key")
        checkBundlePurchase(bundle)

        binding.updatePurchasePrice.formatMoneyToBrazilianFormat()

        binding.updateBtnInsert.setOnClickListener {
            checkFields(bundle)
        }

        binding.updateBtnCancel.setOnClickListener {
            goToHomeScreen()
        }

        return binding.root
    }

    private fun checkBundlePurchase(bundle: PurchaseModel?) {
        if (bundle != null) {
            binding.updatePurchaseName.setText(bundle.item_name)
            binding.updatePurchaseQuantity.setText(bundle.item_quantity.toString())
            binding.updatePurchasePrice.setText(bundle.item_price)
        }
    }

    private fun checkFields(bundle: PurchaseModel?) {

        if (!isFieldValid(binding.updatePurchaseName.text)) {
            warningMessage(getString(R.string.update_empty_name_field_warning)).show()
            return
        }
        if (!isFieldValid(binding.updatePurchaseQuantity.text)) {
            warningMessage(getString(R.string.update_empty_quantity_field_warning)).show()
            return
        }
        if (!isFieldValid(binding.updatePurchasePrice.text)) {
            warningMessage(getString(R.string.update_empty_price_field_warning)).show()
            return
        }
        updatePurchase(bundle)
    }

    private fun updatePurchase(bundle: PurchaseModel?) {
        if (bundle != null) {

            val productId = bundle.item_id
            val productName = binding.updatePurchaseName.text.toString()
            val productQuantity = binding.updatePurchaseQuantity.text.toString().toInt()
            val productPrice = binding.updatePurchasePrice.text.toString()

            binding.apply {
                viewModel.update(
                    productId,
                    productName,
                    productQuantity,
                    productPrice
                )
            }
            warningMessage(getString(R.string.update_notice_saved))
            goToHomeScreen()
        }
    }

    private fun goToHomeScreen() {
        findNavController().popBackStack()
    }

}