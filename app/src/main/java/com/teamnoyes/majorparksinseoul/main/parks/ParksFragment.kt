package com.teamnoyes.majorparksinseoul.main.parks

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.teamnoyes.majorparksinseoul.R
import com.teamnoyes.majorparksinseoul.databinding.ParksFragmentBinding

class ParksFragment : Fragment() {
    private lateinit var parksFragmentBinding: ParksFragmentBinding
    private lateinit var parksViewModel: ParksViewModel
    private lateinit var parksViewModelFactory: ParksViewModelFactory
    private lateinit var adapter: ParksAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        parksFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.parks_fragment, container, false)
        val application = requireNotNull(this.activity).application
        parksViewModelFactory = ParksViewModelFactory(application)

        initParksRecyclerView()
        return parksFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        parksViewModel = ViewModelProvider(this, parksViewModelFactory).get(ParksViewModel::class.java)

        setLoadRegionObserver()
    }

    private fun setLoadRegionObserver(){
        parksViewModel.loadRegion.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        parksViewModel.getRegion()
    }

    private fun initParksRecyclerView(){
        adapter = ParksAdapter()
        val manager = GridLayoutManager(context, 3, GridLayoutManager.VERTICAL, false)
        parksFragmentBinding.recycleParks.layoutManager = manager
        parksFragmentBinding.recycleParks.adapter = adapter

    }
}