package com.teamnoyes.majorparksinseoul.bookmark

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teamnoyes.majorparksinseoul.database.Bookmark
import com.teamnoyes.majorparksinseoul.model.ModelPark
import com.teamnoyes.majorparksinseoul.repository.ParkRepository
import kotlinx.coroutines.launch

class BookmarkViewModel(private val repository: ParkRepository) : ViewModel() {
    private val _bookmarks = repository.getAllBookmark()
    val bookmarks: LiveData<List<Bookmark>>
        get() = _bookmarks

    private val _loadData = MutableLiveData<List<ModelPark>>()
    val loadData: LiveData<List<ModelPark>>
        get() = _loadData

    fun loadBookmarks() {
        viewModelScope.launch {
            _bookmarks.value?.let {
                val list = mutableListOf<ModelPark>()
                for (bookmark in it) {
                    list.add(Bookmark.bookmarkToModelPark(bookmark))
                }
                _loadData.value = list
            }
        }
    }
}