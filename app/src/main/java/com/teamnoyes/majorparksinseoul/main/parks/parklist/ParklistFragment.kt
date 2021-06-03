package com.teamnoyes.majorparksinseoul.main.parks.parklist

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.teamnoyes.majorparksinseoul.R
import com.teamnoyes.majorparksinseoul.databinding.ParklistFragmentBinding

class ParklistFragment : Fragment() {
    private lateinit var parklistFragmentBinding: ParklistFragmentBinding
    private lateinit var parklistViewModel: ParklistViewModel
    private lateinit var parklistViewModelFactory: ParkslistViewModelFactory

    private lateinit var adapter: ParklistAdapter
    private var regionName = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        parklistFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.parklist_fragment, container, false)
        initRecyclerView()
        regionName = arguments?.getString("regionName") ?: ""

        parklistViewModelFactory = ParkslistViewModelFactory(regionName)

        return parklistFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        parklistViewModel = ViewModelProvider(this, parklistViewModelFactory).get(ParklistViewModel::class.java)

        getParkThumbnailData()
    }

    private fun getParkThumbnailData(){
        parklistViewModel.loadData.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        parklistViewModel.getData()
    }

    private fun initRecyclerView(){
        adapter = ParklistAdapter()
        parklistFragmentBinding.recycleParklist.adapter = adapter
    }

}