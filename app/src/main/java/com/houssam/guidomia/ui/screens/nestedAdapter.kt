package com.houssam.guidomia.ui.screens

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.houssam.guidomia.R
import com.houssam.guidomia.databinding.NestedRowItemBinding


class NestedAdapter : RecyclerView.Adapter<NestedAdapter.CarViewHolder>()  {

    var prosOrConsList: List<String> = listOf()

    class CarViewHolder(val context:Context , val binding: NestedRowItemBinding) : ViewHolder(binding.root) {

        fun bind(list: List<String>, position: Int) {
            binding.desc.text = context.getString(R.string.description, list[position])
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = NestedRowItemBinding.inflate(layoutInflater, parent, false)
        return CarViewHolder(parent.context, binding)
    }

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        holder.bind(prosOrConsList,position)
    }

    override fun getItemCount() = prosOrConsList.size
}

