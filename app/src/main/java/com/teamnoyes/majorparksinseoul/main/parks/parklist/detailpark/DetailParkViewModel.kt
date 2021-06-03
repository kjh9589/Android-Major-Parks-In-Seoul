package com.teamnoyes.majorparksinseoul.main.parks.parklist.detailpark

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teamnoyes.majorparksinseoul.database.Bookmark
import com.teamnoyes.majorparksinseoul.database.BookmarkDatabase
import com.teamnoyes.majorparksinseoul.database.BookmarkDatabaseDao
import com.teamnoyes.majorparksinseoul.datamodel.ModelAllParkDataInfo
import com.teamnoyes.majorparksinseoul.utils.ParksData
import kotlinx.coroutines.launch

class DetailParkViewModel(private val region: String, private val pIdx: Int, private val database: BookmarkDatabaseDao) : ViewModel() {
    private val _regionName = MutableLiveData<String>()
    val regionName: LiveData<String>
        get() = _regionName

    private val _parkName = MutableLiveData<String>()
    val parkName: LiveData<String>
        get() = _parkName

    private val _parkAddr = MutableLiveData<String>()
    val parkAddr: LiveData<String>
        get() = _parkAddr

    private val _content = MutableLiveData<String>()
    val content: LiveData<String>
        get() = _content

    private val _mainEquip = MutableLiveData<String>()
    val mainEquip: LiveData<String>
        get() = _mainEquip

    private val _area = MutableLiveData<String>()
    val area: LiveData<String>
        get() = _area

    private val _mainPlant = MutableLiveData<String>()
    val mainPlant: LiveData<String>
        get() = _mainPlant

    private val _visitLoad = MutableLiveData<String>()
    val visitLoad: LiveData<String>
        get() = _visitLoad

    private val _userRefer = MutableLiveData<String>()
    val userRefer: LiveData<String>
        get() = _userRefer

    private val _management = MutableLiveData<String>()
    val management: LiveData<String>
        get() = _management

    private val _tel = MutableLiveData<String>()
    val tel: LiveData<String>
        get() = _tel

    private val _pos = MutableLiveData<Pair<Double, Double>>()
    val pos: LiveData<Pair<Double, Double>>
        get() = _pos

    private val _visible = MutableLiveData<Boolean>()
    val visible: LiveData<Boolean>
        get() = _visible

    private val _bookmarkStar = MutableLiveData<Bookmark>()
    val bookmarkStar: LiveData<Bookmark>
        get() = _bookmarkStar

    private val _mapInfo = MutableLiveData<Triple<String, String, String>>()
    val mapInfo: LiveData<Triple<String, String, String>>
        get() = _mapInfo

    init {
        initializeBookmarkStar()
    }

    fun loadData(){
        val dataList = ParksData.getData(region)

        for (data in dataList)
            if (data.P_IDX == pIdx){
                setData(data)
                break
            }
    }

    private fun setData(data: ModelAllParkDataInfo){
        _regionName.value = region
        _parkName.value = data.P_PARK
        _parkAddr.value = data.P_ADDR
        _content.value = data.P_LIST_CONTENT
        _mainEquip.value = data.MAIN_EQUIP
        _area.value = data.AREA
        _mainPlant.value = data.MAIN_PLANTS
        _visitLoad.value = data.VISIT_ROAD
        _userRefer.value = data.USE_REFER
        _management.value = data.P_NAME
        _tel.value = data.P_ADMINTEL
        if (data.LATITUDE != "" && data.LONGITUDE != "")
            _pos.value = Pair(data.LATITUDE.toDouble(), data.LONGITUDE.toDouble())
        else
            _visible.value = true

        _mapInfo.value = Triple(data.LATITUDE, data.LONGITUDE, data.P_ADDR)
    }

    private fun initializeBookmarkStar(){
        viewModelScope.launch {
            _bookmarkStar.value = getBookmarkFromDatabase()
        }
    }

    private suspend fun getBookmarkFromDatabase(): Bookmark?{
        var bookmark = database.getBookmark(pIdx)
        if (bookmark == null){
            bookmark = null
        }
        return bookmark
    }

    private suspend fun insert(star: Bookmark){
        database.insert(star)
    }

    fun likeStar(){
        viewModelScope.launch {
            val dataList = ParksData.getData(region)

            for (item in dataList)
                if (item.P_IDX == pIdx){
                    val star = Bookmark(item.P_IDX, item.P_PARK, item.P_LIST_CONTENT, item.AREA,
                        item.OPEN_DT, item.MAIN_EQUIP, item.MAIN_PLANTS, item.GUIDANCE, item.VISIT_ROAD, item.USE_REFER,
                        item.P_IMG, item.P_ZONE, item.P_ADDR, item.P_NAME, item.P_ADMINTEL, item.G_LONGITUDE, item.G_LATITUDE,
                        item.LONGITUDE, item.LATITUDE, item.TEMPLATE_URL, region)

                    insert(star)
                    break
                }

            _bookmarkStar.value = getBookmarkFromDatabase()
        }
    }

    private suspend fun delete(key: Int){
        database.deleteBookmark(key)
    }

    fun defaultStar(){
        viewModelScope.launch {
            delete(pIdx)

            _bookmarkStar.value = getBookmarkFromDatabase()
        }
    }
}