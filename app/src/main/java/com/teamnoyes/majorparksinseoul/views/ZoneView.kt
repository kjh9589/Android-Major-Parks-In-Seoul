package com.teamnoyes.majorparksinseoul.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.teamnoyes.majorparksinseoul.databinding.ZoneViewBinding
import com.teamnoyes.majorparksinseoul.model.ModelZone

class ZoneView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    attachToRoot: Boolean = true // recylcerView 내에서 쓸 때에는 false
) : FrameLayout(context, attrs, defStyleAttr) {
    val binding = ZoneViewBinding.inflate(LayoutInflater.from(context), this, attachToRoot)
    var modelZone: ModelZone?
        get() = binding.modelZone
        set(value) {
            binding.modelZone = value
        }
}