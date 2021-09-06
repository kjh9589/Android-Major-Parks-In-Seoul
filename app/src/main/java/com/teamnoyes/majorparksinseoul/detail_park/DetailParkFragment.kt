package com.teamnoyes.majorparksinseoul.detail_park

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.fragment.findNavController
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraPosition
import com.naver.maps.map.MapFragment
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.naver.maps.map.overlay.Marker
import com.teamnoyes.majorparksinseoul.ParkApplication
import com.teamnoyes.majorparksinseoul.R
import com.teamnoyes.majorparksinseoul.SharedViewModel
import com.teamnoyes.majorparksinseoul.SharedViewModelFactory
import com.teamnoyes.majorparksinseoul.adapter.DetailParkAdapter
import com.teamnoyes.majorparksinseoul.databinding.DetailParkFragmentBinding
import com.teamnoyes.majorparksinseoul.park_list.ParkListFragment.Companion.ZONE_NAME

class DetailParkFragment : Fragment(R.layout.detail_park_fragment), OnMapReadyCallback {

    companion object {
        const val P_IDX = "pIdx"
    }

    private var zoneName: String = ""
    private var pIdx = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            zoneName = it.getString(ZONE_NAME) ?: ""
            pIdx = it.getInt(P_IDX)
        }
    }

    private var mapFragment: MapFragment? = null
    private var binding: DetailParkFragmentBinding? = null
    private val detailParkViewModel: DetailParkViewModel by viewModels {
        DetailParkViewModelFactory((requireActivity().application as ParkApplication).parkRepository)
    }
    private val sharedViewModel: SharedViewModel by activityViewModels {
        SharedViewModelFactory((requireActivity().application as ParkApplication).parkRepository)
    }
    private lateinit var adapter: DetailParkAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val detailParkFragmentBinding = DetailParkFragmentBinding.bind(view)
        binding = detailParkFragmentBinding

        binding?.detailViewModel = detailParkViewModel

        initRecyclerView(detailParkFragmentBinding)
        initToolbar(detailParkFragmentBinding)
        initStarMenu(detailParkFragmentBinding)
        initNaverMap()

        setOnFullDataObserver()
        setOnDetailParkObserver()
        setOnPosObserver(detailParkFragmentBinding)

        zoneName?.let {
            sharedViewModel.getDetailPark(it, pIdx)
        }
    }

    private fun initToolbar(detailParkFragmentBinding: DetailParkFragmentBinding) {
        detailParkFragmentBinding.toolbarDetailPark.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)
        detailParkFragmentBinding.toolbarDetailPark.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
        zoneName?.let {
            detailParkFragmentBinding.toolbarDetailPark.title = it
        }
    }

    private fun initStarMenu(detailParkFragmentBinding: DetailParkFragmentBinding) {
        detailParkViewModel.bookmarkStar.observe(viewLifecycleOwner, Observer {
            detailParkFragmentBinding.toolbarDetailPark.menu.clear()

            if (it == null) {
                detailParkFragmentBinding.toolbarDetailPark.inflateMenu(R.menu.detailpark_default_item)
            } else {
                detailParkFragmentBinding.toolbarDetailPark.inflateMenu(R.menu.detailpark_like_item)
            }
        })

        detailParkFragmentBinding.toolbarDetailPark.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.detailPark_default -> {
                    detailParkFragmentBinding.toolbarDetailPark.menu.clear()
                    detailParkViewModel.likeStar()
                    true
                }
                R.id.detailPark_like -> {
                    detailParkFragmentBinding.toolbarDetailPark.menu.clear()
                    detailParkViewModel.defaultStar()
                    true
                }
                else -> false
            }
        }
    }

    private fun setOnDetailParkObserver() {
        sharedViewModel.detailPark.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })
    }

    private fun setOnFullDataObserver() {
        sharedViewModel.park.observe(viewLifecycleOwner, Observer {
            detailParkViewModel.setData(it, zoneName)
        })
    }

    private fun initRecyclerView(detailParkFragmentBinding: DetailParkFragmentBinding) {
        adapter = DetailParkAdapter()
        detailParkFragmentBinding.recycleDetailPark.adapter = adapter
    }

    private fun initNaverMap() {
        val fm = childFragmentManager
        mapFragment =
            fm.findFragmentById(R.id.naverMap) as MapFragment? ?: MapFragment.newInstance().also {
                fm.beginTransaction().add(R.id.naverMap, it).commit()
            }

        mapFragment?.getMapAsync(this)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onMapReady(naverMap: NaverMap) {
        mapFragment?.mapView?.let { mapView ->
            binding?.let {
                mapView.setOnTouchListener { view, motionEvent ->
                    when (motionEvent.action) {
                        MotionEvent.ACTION_MOVE -> it.nsvDetailPark.requestDisallowInterceptTouchEvent(
                            true
                        )
                        MotionEvent.ACTION_CANCEL -> it.nsvDetailPark.requestDisallowInterceptTouchEvent(
                            false
                        )
                    }
                    mapView.onTouchEvent(motionEvent)
                }
            }
        }

        moveToLocation(naverMap)
    }

    private fun moveToLocation(naverMap: NaverMap) {
        detailParkViewModel.pos.observe(viewLifecycleOwner, Observer { pos ->
            naverMap.cameraPosition = CameraPosition(LatLng(pos.first, pos.second), 12.0)

            val marker = Marker()
            marker.position = LatLng(pos.first, pos.second)
            marker.map = naverMap
        })
    }

    private fun setOnPosObserver(detailParkFragmentBinding: DetailParkFragmentBinding) {
        detailParkViewModel.mapInfo.observe(viewLifecycleOwner, Observer { data ->
            detailParkFragmentBinding.btnDetailparkSearch.setOnClickListener {
                if (data.first != null && data.second != null) {
                    val intent = Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("geo:0, 0?q=${data.first},${data.second}&z=12")
                    )
                    startActivity(intent)
                } else {
                    val intent =
                        Intent(Intent.ACTION_VIEW, Uri.parse("geo:0, 0?q=${data.third}&z=12"))
                    startActivity(intent)
                }
            }
        })
    }

}