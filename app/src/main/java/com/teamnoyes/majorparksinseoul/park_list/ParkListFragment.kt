package com.teamnoyes.majorparksinseoul.park_list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.teamnoyes.majorparksinseoul.ParkApplication
import com.teamnoyes.majorparksinseoul.R
import com.teamnoyes.majorparksinseoul.SharedViewModel
import com.teamnoyes.majorparksinseoul.SharedViewModelFactory
import com.teamnoyes.majorparksinseoul.adapter.ParkListAdapter
import com.teamnoyes.majorparksinseoul.databinding.FragmentParkListBinding
import com.teamnoyes.majorparksinseoul.util.NetworkLiveData

class ParkListFragment : Fragment(R.layout.fragment_park_list) {
    private var zoneName: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            zoneName = it.getString(ZONE_NAME)
        }
    }

    private var binding: FragmentParkListBinding? = null
    private val sharedViewModel: SharedViewModel by activityViewModels {
        SharedViewModelFactory((requireActivity().application as ParkApplication).parkRepository)
    }
    private lateinit var adapter: ParkListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fragmentParkListBinding = FragmentParkListBinding.bind(view)
        binding = fragmentParkListBinding

        initRecyclerView(fragmentParkListBinding)
        setOnLoadParkListObserver()

        zoneName?.let {
            sharedViewModel.getParkList(it)
        }
    }

    private fun initRecyclerView(fragmentParkListBinding: FragmentParkListBinding) {
        adapter = ParkListAdapter { modelPark ->
            if (NetworkLiveData.isNetworkAvailable())
                moveToDetailPark(modelPark.P_IDX)
            else
                Toast.makeText(requireContext(), requireContext().getText(R.string.plz_check_network), Toast.LENGTH_SHORT).show()
        }
        val manager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        fragmentParkListBinding.recycleParkList.layoutManager = manager
        fragmentParkListBinding.recycleParkList.adapter = adapter
    }

    private fun moveToDetailPark(pIdx: Int) {
        findNavController().navigate(ParkListFragmentDirections.actionParkListFragmentToDetailParkFragment(pIdx, zoneName))
    }

    private fun setOnLoadParkListObserver() {
        sharedViewModel.parkList.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })
    }

    companion object {
        const val ZONE_NAME = "zoneName"
    }
}