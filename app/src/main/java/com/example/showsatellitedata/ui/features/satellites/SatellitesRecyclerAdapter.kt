package com.example.showsatellitedata.ui.features.satellites

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.showsatellitedata.R
import com.example.showsatellitedata.databinding.ItemSatelliteListBinding
import com.example.showsatellitedata.entity.SatelliteModel

class SatellitesRecyclerAdapter(
    private var itemList: List<SatelliteModel> = emptyList(),
    private val onItemClicked: (SatelliteModel) -> Unit
): RecyclerView.Adapter<SatellitesRecyclerAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemSatelliteListBinding): RecyclerView.ViewHolder(binding.root) {
        operator fun invoke(item: SatelliteModel) {
            binding.tvSatelliteName.text = item.name
            binding.ivDot.setImageDrawable(
                ContextCompat.getDrawable(
                    binding.root.context,
                    if (item.active) R.drawable.ic_green_dot else R.drawable.ic_red_dot
                )
            )
            binding.tvSatelliteActivity.text = if (item.active) "Active" else "Passive"
            binding.tvSatelliteName.alpha = if (item.active) 1.0F else 0.2F
            binding.tvSatelliteActivity.alpha = if (item.active) 1.0F else 0.2F
            binding.root.setOnClickListener {
                onItemClicked(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {
        val itemLayoutBinding = DataBindingUtil.inflate<ItemSatelliteListBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_satellite_list,
            parent,
            false
        )

        return ViewHolder(itemLayoutBinding)
    }

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.invoke(itemList[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(list: List<SatelliteModel>) {
        this.itemList = list
        notifyDataSetChanged()
    }

}