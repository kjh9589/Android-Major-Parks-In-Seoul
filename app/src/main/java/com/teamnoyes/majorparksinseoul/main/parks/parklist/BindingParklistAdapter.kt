package com.teamnoyes.majorparksinseoul.main.parks.parklist

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.teamnoyes.majorparksinseoul.R
import com.teamnoyes.majorparksinseoul.datamodel.ModelParklist

@BindingAdapter("parkName")
fun TextView.setParkName(item: ModelParklist){
    text = item.P_PARK
}

@BindingAdapter("parkAddr")
fun TextView.setParkAddr(item: ModelParklist){
    text = item.P_ADDR
}

@BindingAdapter("parkGuidance")
fun ImageView.setParkGuidance(item: ModelParklist){
    // 코루틴 필요한가?
    if (item.GUIDANCE != "")
        Glide.with(this).load(item.GUIDANCE).into(this)
    else
        Glide.with(this).load(R.drawable.logo).into(this)
}