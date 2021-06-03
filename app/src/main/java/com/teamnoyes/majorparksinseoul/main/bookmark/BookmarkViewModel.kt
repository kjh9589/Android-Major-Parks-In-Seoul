package com.teamnoyes.majorparksinseoul.main.bookmark

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.teamnoyes.majorparksinseoul.database.Bookmark
import com.teamnoyes.majorparksinseoul.database.BookmarkDatabaseDao
import com.teamnoyes.majorparksinseoul.datamodel.ModelAllParkDataInfo
import com.teamnoyes.majorparksinseoul.datamodel.ModelParklist
import kotlinx.coroutines.launch

class BookmarkViewModel(
    private val database: BookmarkDatabaseDao, application: Application) : AndroidViewModel(application) {

    private val _bookmarks = database.getAllBookmark()
    val bookmarks: LiveData<List<Bookmark>>
        get() = _bookmarks

    private val _loadData = MutableLiveData<List<ModelParklist>>()
    val loadData: LiveData<List<ModelParklist>>
        get() = _loadData

    fun transformData(){
        viewModelScope.launch {
            if (bookmarks.value?.isNotEmpty() == true){
                val list = mutableListOf<ModelParklist>()
                for (bookmark in bookmarks.value!!){
                    list.add(ModelParklist(bookmark.P_IDX, bookmark.P_PARK, bookmark.GUIDANCE, bookmark.P_ADDR, bookmark.RegionName))
                }
                _loadData.value = list
            }
            else{
                _loadData.value = null
            }
        }
    }
}