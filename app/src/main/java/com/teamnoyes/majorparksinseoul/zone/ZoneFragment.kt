package com.teamnoyes.majorparksinseoul.zone

import android.os.Bundle
import android.os.Parcelable
import android.view.View
import android.widget.Toast
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.teamnoyes.majorparksinseoul.ParkApplication
import com.teamnoyes.majorparksinseoul.R
import com.teamnoyes.majorparksinseoul.adapter.ZoneAdapter
import com.teamnoyes.majorparksinseoul.databinding.ZoneFragmentBinding
import com.teamnoyes.majorparksinseoul.model.ModelZone
import com.teamnoyes.majorparksinseoul.util.NetworkLiveData
import java.util.ArrayList

class ZoneFragment : Fragment(R.layout.zone_fragment) {
    private var binding: ZoneFragmentBinding? = null
    private val zoneViewModel: ZoneViewModel by activityViewModels{
        ZoneViewModelFactory((requireActivity().application as ParkApplication).parkRepository)
    }
    private lateinit var adapter: ZoneAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val zoneFragmentBinding = ZoneFragmentBinding.bind(view)
        binding = zoneFragmentBinding

        initRecyclerView(zoneFragmentBinding)
        setOnLoadZoneObserver(zoneFragmentBinding)

        if (zoneViewModel.loadZone.value == null) {
            zoneViewModel.getZone()
        }
    }

    private fun initRecyclerView(zoneFragmentBinding: ZoneFragmentBinding) {
        adapter = ZoneAdapter {
            if (NetworkLiveData.isNetworkAvailable())
                moveToParkList(it.name)
            else
                Toast.makeText(requireContext(), requireContext().getText(R.string.plz_check_network), Toast.LENGTH_SHORT).show()
        }
        val manager = GridLayoutManager(context, 3, GridLayoutManager.VERTICAL, false)
        zoneFragmentBinding.recycleZone.layoutManager = manager
        zoneFragmentBinding.recycleZone.adapter = adapter
    }

    private fun setOnLoadZoneObserver(zoneFragmentBinding: ZoneFragmentBinding) {
        zoneViewModel.loadZone.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
                zoneFragmentBinding.progressbar.isGone = true
                zoneFragmentBinding.recycleZone.isVisible = true
            }
        })
    }

    private fun moveToParkList(regionName: String) {
        findNavController().navigate(ZoneFragmentDirections.actionZoneFragmentToParkListFragment(regionName))
    }
}