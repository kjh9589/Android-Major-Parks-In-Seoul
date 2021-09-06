package com.teamnoyes.majorparksinseoul.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.teamnoyes.majorparksinseoul.model.ModelDetailPark
import com.teamnoyes.majorparksinseoul.views.DetailParkView

class DetailParkAdapter: ListAdapter<ModelDetailPark, DetailParkAdapter.ViewHolder>(diffUtil) {
    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<ModelDetailPark>() {
            override fun areItemsTheSame(
                oldItem: ModelDetailPark,
                newItem: ModelDetailPark
            ): Boolean {
                return oldItem.headString == newItem.headString
            }

            override fun areContentsTheSame(
                oldItem: ModelDetailPark,
                newItem: ModelDetailPark
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    inner class ViewHolder(private val detailParkView: DetailParkView): RecyclerView.ViewHolder(detailParkView.binding.root) {
        fun bind(modelDetailPark: ModelDetailPark) {
            detailParkView.modelDetailPark = modelDetailPark
            detailParkView.binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(DetailParkView(parent.context, attachToRoot = false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position])
    }
}