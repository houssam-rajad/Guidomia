package com.houssam.guidomia.ui.screens

import android.content.Context
import android.view.LayoutInflater
import android.view.View.GONE
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.VISIBLE
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.houssam.guidomia.R
import com.houssam.guidomia.databinding.ItemRowBinding
import com.houssam.guidomia.domain.pojo.CarModel
import com.houssam.guidomia.util.toFormattedPricing


class CarsAdapter : RecyclerView.Adapter<CarsAdapter.CarViewHolder>() {

    var carsList: List<CarModel> = listOf()
    var previouslyClicked: Int = -1

    class CarViewHolder(
        val context: Context, val binding: ItemRowBinding, val list: List<CarModel>
    ) : ViewHolder(binding.root) {
        var nestedAdapter = NestedAdapter()

        fun bind(position: Int) {
            val carToSee = list[position]
            binding.carName.text = context.getString(R.string.car_name, carToSee.model)
            binding.carPrice.text =
                context.getString(R.string.car_price, carToSee.marketPrice.toFormattedPricing())
            binding.rating.rating = carToSee.rating.toFloat()
            binding.rowImageView.background = when (carToSee.image) {
                context.resources.getResourceEntryName(R.drawable.range_rover) -> AppCompatResources.getDrawable(
                    context, R.drawable.range_rover
                )

                context.resources.getResourceEntryName(R.drawable.alpine_roadster) -> AppCompatResources.getDrawable(
                    context, R.drawable.alpine_roadster
                )

                context.resources.getResourceEntryName(R.drawable.mercedez_benz_glc) -> AppCompatResources.getDrawable(
                    context, R.drawable.mercedez_benz_glc
                )

                else -> AppCompatResources.getDrawable(context, R.drawable.bmw_330i)
            }

            //animation Can still be added here for Expanding and Collapsing
            binding.nestedLayout.visibility = if (list[position].isExpanded) VISIBLE else GONE

            if (list[position].consList.isEmpty()) {
                binding.consRv.visibility = GONE
                binding.cons.visibility = GONE
            } else {
                nestedAdapter.prosOrConsList = list[position].consList.filter { it.length != 0 }
                binding.consRv.adapter = nestedAdapter
                binding.consRv.layoutManager = LinearLayoutManager(context)
            }
            if (list[position].prosList.isEmpty()) {
                binding.prosRv.visibility = GONE
                binding.pros.visibility = GONE
            } else {
                nestedAdapter = NestedAdapter()
                nestedAdapter.prosOrConsList = list[position].prosList.filter { it.length != 0 }
                binding.prosRv.adapter = nestedAdapter
                binding.prosRv.layoutManager = LinearLayoutManager(context)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemRowBinding.inflate(layoutInflater, parent, false)
        return CarViewHolder(parent.context, binding, carsList)
    }

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        holder.bind(position)
        holder.binding.root.setOnClickListener {
            if (previouslyClicked != -1) notifyItemChanged(previouslyClicked)
            previouslyClicked = position

            holder.binding.nestedLayout.visibility =
                if (!carsList[position].isExpanded) VISIBLE else GONE

            carsList[position].isExpanded = !carsList[position].isExpanded
            carsList.forEach {
                if (carsList[position].id != it.id) it.isExpanded = false
            }
            notifyItemChanged(position)
        }
    }

    override fun getItemCount() = carsList.size
}
