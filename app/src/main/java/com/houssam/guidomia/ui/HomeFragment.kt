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
import com.houssam.guidomia.pojo.CarModel
import com.houssam.guidomia.util.Resource
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment: Fragment() {
    private var _binding: HomeFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by activityViewModels()

    private var adapter = CarsAdapter()
    private var allCarsList: List<CarModel> = listOf()


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
            carsList.data?.let {
                allCarsList = carsList.data
            }
            when(carsList) {
                is Resource.Loading -> displayLoading()
                is Resource.Success -> {
                    adapter.carsList = allCarsList
                    binding.carRecyclerView.layoutManager = LinearLayoutManager(requireContext())
                    binding.carRecyclerView.adapter = adapter
                    for(i in 0..allCarsList.size) adapter.notifyItemInserted(i)
                }


                else -> {}
            }
            Log.d("list is", carsList.data.toString())

        }
    }

    private fun displayLoading() {

    }

}