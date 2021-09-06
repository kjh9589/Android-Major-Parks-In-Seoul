package com.teamnoyes.majorparksinseoul.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.teamnoyes.majorparksinseoul.model.ModelPark
import com.teamnoyes.majorparksinseoul.views.ParkView

class ParkListAdapter(private val onItemClicked: (ModelPark) -> Unit) :
    ListAdapter<ModelPark, ParkListAdapter.ViewHolder>(diffUtil) {
    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<ModelPark>() {
            override fun areItemsTheSame(
                oldItem: ModelPark,
                newItem: ModelPark
            ): Boolean {
                return oldItem.P_IDX == newItem.P_IDX
            }

            override fun areContentsTheSame(
                oldItem: ModelPark,
                newItem: ModelPark
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    inner class ViewHolder(private val parkView: ParkView) :
        RecyclerView.ViewHolder(parkView.binding.root) {
        fun bind(modelPark: ModelPark){
            parkView.modelPark = modelPark
            parkView.binding.root.setOnClickListener {
                onItemClicked(modelPark)
            }
            parkView.binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ParkView(parent.context, attachToRoot = false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position])
    }
}