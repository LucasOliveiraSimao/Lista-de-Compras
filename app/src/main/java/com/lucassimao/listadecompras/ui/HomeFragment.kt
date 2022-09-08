package com.lucassimao.listadecompras.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.lucassimao.listadecompras.R
import com.lucassimao.listadecompras.data.model.PurchaseModel
import com.lucassimao.listadecompras.databinding.FragmentHomeBinding
import com.lucassimao.listadecompras.utils.formatPrice
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel by viewModel<PurchaseViewModel>()
    private lateinit var adapter: PurchaseAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        setupRecyclerView()

        binding.fabInsertPurchase.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_insertFragment)
        }
        return binding.root
    }

    private fun setupRecyclerView() {
        adapter = PurchaseAdapter(onItemClick = {

        })

        binding.rvListPurchases.adapter = adapter
        viewModel.getAllPurchase.observe(viewLifecycleOwner) {
            adapter.submitList(it)
            sumPurchases(it)
        }
    }

    private fun sumPurchases(it: List<PurchaseModel>?) {
        var priceTotal = 0.0
        it?.forEach {
            priceTotal += (it.item_price.toDouble().times(it.item_quantity))
        }
        binding.tvTotal.text = resources.getString(R.string.total_price, formatPrice(priceTotal))
    }

}