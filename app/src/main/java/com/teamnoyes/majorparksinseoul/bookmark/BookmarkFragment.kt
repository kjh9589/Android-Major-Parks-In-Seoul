package com.teamnoyes.majorparksinseoul.bookmark

import android.os.Bundle
import android.view.View
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.teamnoyes.majorparksinseoul.ParkApplication
import com.teamnoyes.majorparksinseoul.R
import com.teamnoyes.majorparksinseoul.adapter.ParkListAdapter
import com.teamnoyes.majorparksinseoul.databinding.BookmarkFragmentBinding

class BookmarkFragment : Fragment(R.layout.bookmark_fragment) {
    private var binding: BookmarkFragmentBinding? = null
    private val viewModel: BookmarkViewModel by activityViewModels {
        BookmarkViewModelFactory((requireActivity().application as ParkApplication).parkRepository)
    }
    private lateinit var adapter: ParkListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bookmarkFragmentBinding = BookmarkFragmentBinding.bind(view)
        binding = bookmarkFragmentBinding

        initRecyclerView(bookmarkFragmentBinding)
        setOnBookmarkListObserver(bookmarkFragmentBinding)
    }

    private fun initRecyclerView(bookmarkFragmentBinding: BookmarkFragmentBinding) {
        adapter = ParkListAdapter {
            moveToDetailPark(it.P_IDX, it.region)
        }

        bookmarkFragmentBinding.recycleBookmark.adapter = adapter
    }

    private fun setOnBookmarkListObserver(bookmarkFragmentBinding: BookmarkFragmentBinding) {
        viewModel.bookmarks.observe(viewLifecycleOwner, Observer {
            viewModel.loadBookmarks()
        })

        viewModel.loadData.observe(viewLifecycleOwner, Observer {
            if (it.isNotEmpty()) {
                adapter.submitList(it)
                bookmarkFragmentBinding.linearNoBookmark.isGone = true
                bookmarkFragmentBinding.recycleBookmark.isVisible = true
            } else {
                bookmarkFragmentBinding.recycleBookmark.isGone = true
                bookmarkFragmentBinding.linearNoBookmark.isVisible = true
            }

        })
    }

    private fun moveToDetailPark(pIdx: Int, zoneName: String) {
        findNavController().navigate(
            BookmarkFragmentDirections.actionBookmarkFragmentToDetailParkFragment2(
                pIdx,
                zoneName
            )
        )
    }
}