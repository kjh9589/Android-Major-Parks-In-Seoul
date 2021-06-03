package com.teamnoyes.majorparksinseoul.main.parks.parklist

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.teamnoyes.majorparksinseoul.R
import com.teamnoyes.majorparksinseoul.databinding.ParklistItemBinding
import com.teamnoyes.majorparksinseoul.datamodel.ModelParklist
import com.teamnoyes.majorparksinseoul.main.MainFragmentDirections
import com.teamnoyes.majorparksinseoul.utils.NetworkState

class ParklistAdapter: ListAdapter<ModelParklist, ParklistAdapter.ParklistViewHolder>(ParklistDiffCallback()){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParklistViewHolder {
        return ParklistViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ParklistViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class ParklistViewHolder private constructor(val binding: ParklistItemBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(item: ModelParklist){
            moveToDetailPark(item)
            binding.parklistItemViewModel = item
            binding.executePendingBindings()
        }

        private fun moveToDetailPark(item: ModelParklist){
            binding.cardParklist.setOnClickListener {
                if (NetworkState.state){
                    if (it.findNavController().currentDestination!!.id == R.id.parklistFragment){
                        it.findNavController().navigate(ParklistFragmentDirections.actionParklistFragmentToDetailParkFragment(item.region, item.P_IDX))
                    }
                    else if (it.findNavController().currentDestination!!.id == R.id.mainFragment){
                        it.findNavController().navigate(MainFragmentDirections.actionMainFragmentToDetailParkFragment(item.region, item.P_IDX))
                    }
                }
                else{
                    Toast.makeText(it.context, it.context.getString(R.string.check_network), Toast.LENGTH_SHORT).show()
                }
            }
        }

        companion object{
            fun from(parent: ViewGroup): ParklistViewHolder{
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = ParklistItemBinding.inflate(layoutInflater, parent, false)
                return ParklistViewHolder(view)
            }
        }
    }
}

class ParklistDiffCallback: DiffUtil.ItemCallback<ModelParklist>(){
    override fun areItemsTheSame(oldItem: ModelParklist, newItem: ModelParklist): Boolean {
        return oldItem.P_IDX == newItem.P_IDX
    }

    override fun areContentsTheSame(oldItem: ModelParklist, newItem: ModelParklist): Boolean {
        return oldItem == newItem
    }
}