package com.houssam.guidomia.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.houssam.guidomia.databinding.HomeFragmentBinding
import com.houssam.guidomia.util.Resource
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment: Fragment() {
    private var _binding: HomeFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by activityViewModels()

    private lateinit var carsAdapter:CarsAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = HomeFragmentBinding.inflate(inflater, container, false )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
        viewModel.getAllCars()
    }

    private fun setupObservers() {
        viewModel.allCarsList.observe(viewLifecycleOwner) {
            carsList->
            when(carsList) {
                is Resource.Loading -> displayLoading()
                is Resource.Success -> carsList.data?.let {
                    val adapter = CarsAdapter()
                    adapter.carsList = it
                    binding.carRecyclerView.layoutManager = LinearLayoutManager(requireContext())
                    binding.carRecyclerView.adapter = adapter
                    adapter.notifyDataSetChanged()
                }
                else -> {}
            }
            Log.d("list is", carsList.data.toString())

        }
    }

    private fun displayLoading() {

    }

}