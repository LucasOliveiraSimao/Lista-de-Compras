package com.lucassimao.listadecompras.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.lucassimao.listadecompras.R
import com.lucassimao.listadecompras.databinding.FragmentInsertBinding
import com.lucassimao.listadecompras.utils.isFieldValid
import org.koin.androidx.viewmodel.ext.android.viewModel

class InsertFragment : Fragment() {
    private lateinit var binding: FragmentInsertBinding
    private val viewModel by viewModel<PurchaseViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInsertBinding.inflate(inflater, container, false)

        binding.btnInsert.setOnClickListener(checkFields())
        binding.btnCancel.setOnClickListener {
            findNavController().popBackStack()
        }

        return binding.root
    }

    private fun checkFields(): (View) -> Unit = {
        if (isFieldValid(
                requireView(),
                getString(R.string.warning_message),
                binding.etPurchaseName.text
            )
        ) {
            if (isFieldValid(
                    requireView(),
                    getString(R.string.warning_message),
                    binding.etPurchaseQuantity.text
                )
            ) {
                if (isFieldValid(
                        requireView(),
                        getString(R.string.warning_message),
                        binding.etPurchasePrice.text
                    )
                ) {
                    insertPurchase()
                }
            }
        }

    }

    private fun insertPurchase() {
        binding.apply {
            viewModel.insert(
                etPurchaseName.text.toString(),
                etPurchaseQuantity.text.toString().toInt(),
                etPurchasePrice.text.toString()
            )
        }
        Snackbar.make(requireView(), getString(R.string.save_message), 4000).show()
    }

}