package com.lucassimao.listadecompras.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.lucassimao.listadecompras.R
import com.lucassimao.listadecompras.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.fabInsertPurchase.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_insertFragment)
        }
        return binding.root
    }

}