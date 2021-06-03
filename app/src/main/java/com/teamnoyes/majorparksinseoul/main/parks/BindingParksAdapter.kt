package com.teamnoyes.majorparksinseoul.main.parks

import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.teamnoyes.majorparksinseoul.R
import com.teamnoyes.majorparksinseoul.datamodel.ModelParks

@BindingAdapter("parksName")
fun Button.setParksRegionName(item: ModelParks){
    text = item.name
}

@BindingAdapter("regionName")
fun TextView.setRegionName(item: ModelParks){
    text = item.name
}

@BindingAdapter("regionLogo")
fun ImageView.setRegionLogo(item: ModelParks){
    when(item.id){
        "gangnam" -> Glide.with(this).load(R.drawable.gangnam).into(this)
        "gangdong" -> Glide.with(this).load(R.drawable.gangdong).into(this)
        "gangbuk" -> Glide.with(this).load(R.drawable.gangbuk).into(this)
        "gangseo" -> Glide.with(this).load(R.drawable.gangseo).into(this)
        "gwanak" -> Glide.with(this).load(R.drawable.gwanak).into(this)
        "gwangjin" -> Glide.with(this).load(R.drawable.gwangjin).into(this)
        "guro" -> Glide.with(this).load(R.drawable.guro).into(this)
        "geumcheon" -> Glide.with(this).load(R.drawable.geumcheon).into(this)
        "nowon" -> Glide.with(this).load(R.drawable.nowon).into(this)
        "dobong" -> Glide.with(this).load(R.drawable.dobong).into(this)
        "dongdaemoon" -> Glide.with(this).load(R.drawable.dongdaemun).into(this)
        "dongjak" -> Glide.with(this).load(R.drawable.dongjak).into(this)
        "mapo" -> Glide.with(this).load(R.drawable.mapo).into(this)
        "seodaemoon" -> Glide.with(this).load(R.drawable.seodaemun).into(this)
        "seocho" -> Glide.with(this).load(R.drawable.seocho).into(this)
        "seongdong" -> Glide.with(this).load(R.drawable.seongdong).into(this)
        "seongbuk" -> Glide.with(this).load(R.drawable.seongbuk).into(this)
        "songpa" -> Glide.with(this).load(R.drawable.songpa).into(this)
        "yangcheon" -> Glide.with(this).load(R.drawable.yangcheon).into(this)
        "yeongdeungpo" -> Glide.with(this).load(R.drawable.yeongdeungpo).into(this)
        "yongsan" -> Glide.with(this).load(R.drawable.yongsan).into(this)
        "eunpyeong" -> Glide.with(this).load(R.drawable.eunpyeong).into(this)
        "jongro" -> Glide.with(this).load(R.drawable.jongno).into(this)
        "jung" -> Glide.with(this).load(R.drawable.junggu).into(this)
        "jungnang" -> Glide.with(this).load(R.drawable.jungnang).into(this)
    }
}
