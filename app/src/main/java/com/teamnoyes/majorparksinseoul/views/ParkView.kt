package com.teamnoyes.majorparksinseoul.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.teamnoyes.majorparksinseoul.databinding.ParkViewBinding
import com.teamnoyes.majorparksinseoul.model.ModelPark

class ParkView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    attachToRoot: Boolean = true
): FrameLayout(context, attrs, defStyleAttr) {
    val binding = ParkViewBinding.inflate(LayoutInflater.from(context), this, attachToRoot)
    var modelPark: ModelPark?
        get() = binding.modelPark
        set(value) {
            binding.modelPark = value
        }
}