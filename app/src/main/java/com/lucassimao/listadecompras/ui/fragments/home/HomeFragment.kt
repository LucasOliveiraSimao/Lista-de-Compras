package com.lucassimao.listadecompras.ui.fragments.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.lucassimao.listadecompras.R
import com.lucassimao.listadecompras.data.model.PurchaseModel
import com.lucassimao.listadecompras.databinding.FragmentHomeBinding
import com.lucassimao.listadecompras.ui.PurchaseViewModel
import com.lucassimao.listadecompras.utils.putCommaPrice
import com.lucassimao.listadecompras.utils.putPointPrice
import com.lucassimao.listadecompras.utils.putTwoDecimalPlaces
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

        binding.btnDeleteAll.setOnClickListener {
            deleteAllPurchaseList()
        }

        return binding.root
    }

    private fun deleteAllPurchaseList() {
        viewModel.deleteAllPurchase()
    }

    private fun setupRecyclerView() {

        adapter = PurchaseAdapter()

        deletePurchase()

        updatePurchase()

        binding.rvListPurchases.adapter = adapter

        viewModel.getAllPurchase.observe(viewLifecycleOwner) {
            adapter.submitList(it)
            sumPurchases(it)
        }
    }

    private fun updatePurchase() {
        adapter.updatePurchase = {
            goToUpdateScreen(it)
        }
    }

    private fun deletePurchase() {
        adapter.deletePurchase = {
            viewModel.delete(it)
        }
    }

    private fun goToUpdateScreen(item: PurchaseModel) {
        val bundle = Bundle()
        bundle.putParcelable("key", item)
        findNavController().navigate(
            R.id.action_homeFragment_to_updateFragment, bundle
        )
    }

    private fun sumPurchases(purchases: List<PurchaseModel>?) {
        var totalAmountOfPurchase = 0.0
        purchases?.forEach {
            totalAmountOfPurchase += multiplyQuantityTimesPrice(it)
        }

        binding.tvTotal.text = displayTotalPrice(totalAmountOfPurchase)
    }

    private fun displayTotalPrice(totalAmountOfPurchase: Double) = resources.getString(
        R.string.home_total_price,
        totalAmountOfPurchase.putTwoDecimalPlaces().putCommaPrice()
    )

    private fun multiplyQuantityTimesPrice(it: PurchaseModel) =
        (it.item_price.putPointPrice().toDouble().times(it.item_quantity))

}