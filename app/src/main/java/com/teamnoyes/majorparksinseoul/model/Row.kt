package com.teamnoyes.majorparksinseoul.model


import com.google.gson.annotations.SerializedName

data class Row(
    @SerializedName("AREA")
    val aREA: String,
    @SerializedName("G_LATITUDE")
    val gLATITUDE: String,
    @SerializedName("G_LONGITUDE")
    val gLONGITUDE: String,
    @SerializedName("GUIDANCE")
    val gUIDANCE: String,
    @SerializedName("LATITUDE")
    val lATITUDE: String,
    @SerializedName("LONGITUDE")
    val lONGITUDE: String,
    @SerializedName("MAIN_EQUIP")
    val mAINEQUIP: String,
    @SerializedName("MAIN_PLANTS")
    val mAINPLANTS: String,
    @SerializedName("OPEN_DT")
    val oPENDT: String,
    @SerializedName("P_ADDR")
    val pADDR: String,
    @SerializedName("P_ADMINTEL")
    val pADMINTEL: String,
    @SerializedName("P_IDX")
    val pIDX: Int,
    @SerializedName("P_IMG")
    val pIMG: String,
    @SerializedName("P_LIST_CONTENT")
    val pLISTCONTENT: String,
    @SerializedName("P_NAME")
    val pNAME: String,
    @SerializedName("P_PARK")
    val pPARK: String,
    @SerializedName("P_ZONE")
    val pZONE: String,
    @SerializedName("TEMPLATE_URL")
    val tEMPLATEURL: String,
    @SerializedName("USE_REFER")
    val uSEREFER: String,
    @SerializedName("VISIT_ROAD")
    val vISITROAD: String
)