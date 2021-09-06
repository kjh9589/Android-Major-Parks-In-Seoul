package com.teamnoyes.majorparksinseoul.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.teamnoyes.majorparksinseoul.databinding.DetailParkViewBinding
import com.teamnoyes.majorparksinseoul.model.ModelDetailPark

class DetailParkView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    attachToRoot: Boolean = true
): FrameLayout(context, attrs, defStyleAttr) {
    val binding = DetailParkViewBinding.inflate(LayoutInflater.from(context), this, attachToRoot)
    var modelDetailPark: ModelDetailPark?
        get() = binding.detailPark
        set(value) {
            binding.detailPark = value
        }
}