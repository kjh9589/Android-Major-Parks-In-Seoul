package com.teamnoyes.majorparksinseoul.main.bookmark

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.teamnoyes.majorparksinseoul.R
import com.teamnoyes.majorparksinseoul.database.BookmarkDatabase
import com.teamnoyes.majorparksinseoul.databinding.BookmarkFragmentBinding
import com.teamnoyes.majorparksinseoul.main.parks.parklist.ParklistAdapter

class BookmarkFragment : Fragment() {
    private lateinit var bookmarkFragmentBinding: BookmarkFragmentBinding
    private lateinit var bookmarkViewModel: BookmarkViewModel
    private lateinit var bookmarkViewModelFactory: BookmarkViewModelFactory

    private lateinit var adapter: ParklistAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bookmarkFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.bookmark_fragment, container, false)
        val application = requireNotNull(this.activity).application
        val dataSource = BookmarkDatabase.getInstance(application).bookmarkDatabaseDao
        bookmarkViewModelFactory = BookmarkViewModelFactory(dataSource, application)

        initRecyclerView()
        return bookmarkFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bookmarkViewModel = ViewModelProvider(this, bookmarkViewModelFactory).get(BookmarkViewModel::class.java)

        getBookmarkData()
    }

    private fun initRecyclerView(){
        adapter = ParklistAdapter()
        bookmarkFragmentBinding.recycleBookmark.adapter = adapter
    }

    private fun getBookmarkData(){
        bookmarkViewModel.bookmarks.observe(viewLifecycleOwner, Observer {
            bookmarkViewModel.transformData()
        })

        bookmarkViewModel.loadData.observe(viewLifecycleOwner, Observer {
            // null도 받아야하기 때문에 let 사용 안 함
            adapter.submitList(it)
        })
    }

}