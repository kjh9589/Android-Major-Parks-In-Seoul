package com.teamnoyes.majorparksinseoul.detail_park

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teamnoyes.majorparksinseoul.database.Bookmark
import com.teamnoyes.majorparksinseoul.model.Row
import com.teamnoyes.majorparksinseoul.repository.ParkRepository
import kotlinx.coroutines.launch

class DetailParkViewModel(private val repository: ParkRepository) : ViewModel() {
    private var _park: Row? = null
    private var _zoneName: String = ""

    private val _pos = MutableLiveData<Pair<Double, Double>>()
    val pos: LiveData<Pair<Double, Double>>
        get() = _pos

    private val _visible = MutableLiveData<Boolean>()
    val visible: LiveData<Boolean>
        get() = _visible

    private val _mapInfo = MutableLiveData<Triple<String?, String?, String?>>()
    val mapInfo: LiveData<Triple<String?, String?, String?>>
        get() = _mapInfo

    private var pIdx = -1

    fun setData(data: Row?, zoneName: String) {
        _park = data

        _park?.let {
            if (it.lATITUDE != "" && it.lONGITUDE != "")
                _pos.value = Pair(it.lATITUDE.toDouble(), it.lONGITUDE.toDouble())
            else
                _visible.value = true

            pIdx = it.pIDX
            _zoneName = zoneName

            _mapInfo.value = Triple(it.lATITUDE, it.lONGITUDE, it.pADDR)
            getBookmark()
        }

    }

    private val _bookmarkStar = MutableLiveData<Bookmark>()
    val bookmarkStar: LiveData<Bookmark>
        get() = _bookmarkStar

    private fun getBookmark() {
        viewModelScope.launch {
            _bookmarkStar.value = repository.getBookmark(pIdx)
        }
    }

    fun likeStar() {
        viewModelScope.launch {
            _park?.let {
                repository.insertBookmark(Bookmark.rowToBookmark(it, _zoneName))
            }

            getBookmark()
        }
    }

    fun defaultStar() {
        viewModelScope.launch {
            repository.deleteBookmark(pIdx)

            getBookmark()
        }
    }
}