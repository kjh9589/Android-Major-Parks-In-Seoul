package com.teamnoyes.majorparksinseoul.binding_adpater

import android.text.util.Linkify
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.teamnoyes.majorparksinseoul.R
import com.teamnoyes.majorparksinseoul.model.ModelDetailPark
import com.teamnoyes.majorparksinseoul.model.ModelPark
import com.teamnoyes.majorparksinseoul.model.ModelZone

@BindingAdapter("zoneName")
fun TextView.setZoneName(item: ModelZone) {
    text = item.name
}

@BindingAdapter("zoneLogo")
fun ImageView.setZoneLogo(item: ModelZone) {
    when (item.id) {
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

@BindingAdapter("parkName")
fun TextView.setParkName(item: ModelPark) {
    text = item.P_PARK
}

@BindingAdapter("parkAddress")
fun TextView.setParkAddress(item: ModelPark) {
    text = item.P_ADDR
}

@BindingAdapter("parkGuidance")
fun ImageView.setParkGuidance(item: ModelPark) {
    if (item.GUIDANCE != "")
        Glide.with(this).load(item.GUIDANCE).into(this)
    else
        Glide.with(this).load(R.drawable.logo).into(this)
}

@BindingAdapter("headText")
fun TextView.setHeadText(item: ModelDetailPark) {
    text = item.headString
    when(item.type) {
        0 -> {
            setTextAppearance(android.R.style.TextAppearance_Material_Headline)
        }
        1 -> {
            setTextAppearance(android.R.style.TextAppearance_Material_Body2)
            setTextColor(ContextCompat.getColor(this.context, android.R.color.darker_gray))
        }
        2 -> {
            setTextAppearance(android.R.style.TextAppearance_Material_Body2)
            setTextColor(ContextCompat.getColor(this.context, android.R.color.darker_gray))
        }
    }
}

@BindingAdapter("bodyText")
fun TextView.setBodyText(item: ModelDetailPark) {
    text = item.bodyString
    when(item.type) {
        0 -> {
            setTextAppearance(android.R.style.TextAppearance_Material_Body2)
            setTextColor(ContextCompat.getColor(this.context, android.R.color.darker_gray))
        }
        1 -> {
            setTextAppearance(android.R.style.TextAppearance_Medium)
        }
        2 -> {
            setTextAppearance(android.R.style.TextAppearance_Medium)
            Linkify.addLinks(this, Linkify.PHONE_NUMBERS)
            this.linksClickable = true
        }
    }
}