package com.houssam.guidomia.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.houssam.guidomia.R
import com.houssam.guidomia.databinding.ItemRowBinding
import com.houssam.guidomia.pojo.CarModel
import com.houssam.guidomia.util.toFormattedPricing


class CarsAdapter : RecyclerView.Adapter<CarsAdapter.CarViewHolder>()  {

    var carsList: List<CarModel> = listOf()

    class CarViewHolder(val context:Context , private val binding: ItemRowBinding) : ViewHolder(binding.root) {

        fun bind(carToSee: CarModel) {
            binding.carName.text = context.getString(R.string.car_name, carToSee.model)
            binding.carPrice.text = context.getString(R.string.car_name, carToSee.marketPrice.toFormattedPricing())
            binding.rating.rating = carToSee.rating.toFloat()
            binding.rowImageView.background = when(carToSee.image)
            {
                context.resources.getResourceEntryName(R.drawable.range_rover) -> AppCompatResources.getDrawable(context ,R.drawable.range_rover)
                context.resources.getResourceEntryName(R.drawable.alpine_roadster) -> AppCompatResources.getDrawable(context ,R.drawable.alpine_roadster)
                context.resources.getResourceEntryName(R.drawable.mercedez_benz_glc) -> AppCompatResources.getDrawable(context ,R.drawable.mercedez_benz_glc)
                else -> AppCompatResources.getDrawable(context ,R.drawable.bmw_330i)
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemRowBinding.inflate(layoutInflater, parent, false)
        return CarViewHolder(parent.context, binding)
    }

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        holder.bind(carsList[position])
    }

    override fun getItemCount() = carsList.size
}