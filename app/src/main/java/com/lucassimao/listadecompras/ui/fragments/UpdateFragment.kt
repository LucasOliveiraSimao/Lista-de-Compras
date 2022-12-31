package com.lucassimao.listadecompras.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.lucassimao.listadecompras.R
import com.lucassimao.listadecompras.data.model.PurchaseModel
import com.lucassimao.listadecompras.databinding.FragmentUpdateBinding
import com.lucassimao.listadecompras.ui.PurchaseViewModel
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

        binding.updateBtnInsert.setOnClickListener(checkFields(bundle))
        binding.updateBtnCancel.setOnClickListener {
            findNavController().popBackStack()
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

    private fun checkFields(bundle: PurchaseModel?): (View) -> Unit = {
        val view = requireView()
        val message = ""

        if (!isFieldValid(binding.updatePurchaseName.text)) {
            warningMessage(message).show()
        }
        if (!isFieldValid(binding.updatePurchaseQuantity.text)) {
            warningMessage(message).show()
        }
        if (!isFieldValid(binding.updatePurchasePrice.text)) {
            warningMessage(message).show()
        } else {
            updatePurchase(bundle)
        }
    }

    private fun updatePurchase(bundle: PurchaseModel?) {
        if (bundle != null) {
            binding.apply {
                viewModel.update(
                    bundle.item_id,
                    updatePurchaseName.text.toString(),
                    updatePurchaseQuantity.text.toString().toInt(),
                    updatePurchasePrice.text.toString()
                )
            }
//            Snackbar.make(requireView(), getString(R.string.save_message), Snackbar.LENGTH_SHORT).show()
            findNavController().popBackStack()
        }
    }

}