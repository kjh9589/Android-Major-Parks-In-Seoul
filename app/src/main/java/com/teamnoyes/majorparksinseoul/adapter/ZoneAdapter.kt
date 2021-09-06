package com.teamnoyes.majorparksinseoul.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.teamnoyes.majorparksinseoul.model.ModelZone
import com.teamnoyes.majorparksinseoul.views.ZoneView

class ZoneAdapter(private val onItemClicked: (ModelZone) -> Unit) :
    ListAdapter<ModelZone, ZoneAdapter.ViewHolder>(diffUtil) {
    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<ModelZone>() {
            override fun areItemsTheSame(oldItem: ModelZone, newItem: ModelZone): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: ModelZone, newItem: ModelZone): Boolean {
                return oldItem == newItem
            }
        }
    }

    inner class ViewHolder(private val zoneView: ZoneView) :
        RecyclerView.ViewHolder(zoneView.binding.root) {
        fun bind(modelZone: ModelZone) {
            zoneView.modelZone = modelZone
            zoneView.binding.root.setOnClickListener {
                onItemClicked(modelZone)
            }
            zoneView.binding.executePendingBindings()

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ZoneView(parent.context, attachToRoot = false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position])
    }
}