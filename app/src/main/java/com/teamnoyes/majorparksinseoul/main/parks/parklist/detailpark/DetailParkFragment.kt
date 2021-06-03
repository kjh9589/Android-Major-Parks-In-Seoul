package com.teamnoyes.majorparksinseoul.main.parks.parklist.detailpark

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.annotation.UiThread
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.*
import com.naver.maps.map.overlay.Marker
import com.teamnoyes.majorparksinseoul.BuildConfig
import com.teamnoyes.majorparksinseoul.R
import com.teamnoyes.majorparksinseoul.database.BookmarkDatabase
import com.teamnoyes.majorparksinseoul.databinding.DetailParkFragmentBinding

class DetailParkFragment : Fragment(), OnMapReadyCallback {
    private lateinit var detailParkFragmentBinding: DetailParkFragmentBinding
    private lateinit var detailParkViewModel: DetailParkViewModel
    private lateinit var detailParkViewModelFactory: DetailParkViewModelFactory

    private var regionName = ""
    private var pIdx = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        detailParkFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.detail_park_fragment, container, false)

        regionName = arguments?.getString("regionName") ?: ""
        pIdx = arguments?.getInt("pIdx") ?: 0
        val application = requireNotNull(this.activity).application
        val dataSource = BookmarkDatabase.getInstance(application).bookmarkDatabaseDao
        detailParkViewModelFactory = DetailParkViewModelFactory(regionName, pIdx, dataSource)

        initToolbar()
        initNaverMap()

        return detailParkFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        detailParkViewModel = ViewModelProvider(this, detailParkViewModelFactory).get(DetailParkViewModel::class.java)
        detailParkFragmentBinding.detailParkViewModel = detailParkViewModel
        initStar()
        controlNaverMapVisible()
        actionViewMap()

        detailParkViewModel.loadData()
    }

    private fun initToolbar(){
        detailParkFragmentBinding.toolbarDetailpark.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)
        detailParkFragmentBinding.toolbarDetailpark.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun initStar(){
        detailParkViewModel.bookmarkStar.observe(viewLifecycleOwner, Observer {
            if (it == null){
                detailParkFragmentBinding.toolbarDetailpark.inflateMenu(R.menu.detailpark_default_item)
            }
            else{
                detailParkFragmentBinding.toolbarDetailpark.inflateMenu(R.menu.detailpark_like_item)
            }
        })

        detailParkFragmentBinding.toolbarDetailpark.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.detailPark_default -> {
                    detailParkFragmentBinding.toolbarDetailpark.menu.clear()
                    detailParkViewModel.likeStar()
                    true
                }
                R.id.detailPark_like -> {
                    detailParkFragmentBinding.toolbarDetailpark.menu.clear()
                    detailParkViewModel.defaultStar()
                    true
                }
                else -> false
            }
        }
    }

    private lateinit var mapFragment: MapFragment

    private fun initNaverMap(){
        val fm = childFragmentManager
        mapFragment = fm.findFragmentById(R.id.naverMap) as MapFragment? ?:
        MapFragment.newInstance().also {
            fm.beginTransaction().add(R.id.naverMap, it).commit()
        }

        mapFragment.getMapAsync(this)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onMapReady(naverMap: NaverMap) {
        mapFragment.mapView!!.setOnTouchListener { view, motionEvent ->
            when(motionEvent.action){
                MotionEvent.ACTION_MOVE -> detailParkFragmentBinding.scrollDetailpark.requestDisallowInterceptTouchEvent(true)
                MotionEvent.ACTION_CANCEL -> detailParkFragmentBinding.scrollDetailpark.requestDisallowInterceptTouchEvent(false)
            }
            mapFragment.mapView!!.onTouchEvent(motionEvent)
        }
        moveToLocation(naverMap)
    }

    @UiThread
    private fun moveToLocation(naverMap: NaverMap){
        detailParkViewModel.pos.observe(viewLifecycleOwner, Observer {
            naverMap.cameraPosition = CameraPosition(LatLng(it.first, it.second), 12.0)

            val marker = Marker()
            marker.position = LatLng(it.first, it.second)
            marker.map = naverMap
        })
    }

    private fun controlNaverMapVisible(){
        detailParkViewModel.visible.observe(viewLifecycleOwner, Observer {
            if (it){
                detailParkFragmentBinding.naverMap.visibility = View.GONE
            }
        })
    }

    private fun actionViewMap(){
        detailParkViewModel.mapInfo.observe(viewLifecycleOwner, Observer {
            detailParkFragmentBinding.btnDetailparkSearch.setOnClickListener {view ->
                if (it.first != "" && it.second != ""){
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("geo:0, 0?q=${it.first},${it.second}&z=12"))
                    startActivity(intent)
                }
                else{
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("geo:0, 0?q=${it.third}&z=12"))
                    startActivity(intent)
                }
            }
        })
    }

}