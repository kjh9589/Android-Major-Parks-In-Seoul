package com.teamnoyes.majorparksinseoul.main.parks

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.teamnoyes.majorparksinseoul.R
import com.teamnoyes.majorparksinseoul.databinding.ParksItemBinding
import com.teamnoyes.majorparksinseoul.datamodel.ModelParks
import com.teamnoyes.majorparksinseoul.main.MainFragmentDirections
import com.teamnoyes.majorparksinseoul.utils.NetworkState

class ParksAdapter: ListAdapter<ModelParks, ParksAdapter.ParksViewHolder>(ParksDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParksViewHolder {
        return ParksViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ParksViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class ParksViewHolder private constructor(val binding: ParksItemBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(item: ModelParks){
            moveToParklist(item)
            binding.parksItemViewModel = item
            binding.executePendingBindings()
        }

        private fun moveToParklist(item: ModelParks){
            binding.cardParksitem.setOnClickListener {
                if (NetworkState.state){
                    it.findNavController().navigate(MainFragmentDirections.actionMainFragmentToParklistFragment(item.name))
                }
                else{
                    Toast.makeText(it.context, it.context.getString(R.string.check_network), Toast.LENGTH_SHORT).show()
                }
            }
        }

        companion object{
            fun from(parent: ViewGroup): ParksViewHolder{
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = ParksItemBinding.inflate(layoutInflater, parent, false)
                return ParksViewHolder(view)
            }
        }
    }

}

class ParksDiffCallback: DiffUtil.ItemCallback<ModelParks>(){
    override fun areItemsTheSame(oldItem: ModelParks, newItem: ModelParks): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ModelParks, newItem: ModelParks): Boolean {
        return oldItem == newItem
    }
}