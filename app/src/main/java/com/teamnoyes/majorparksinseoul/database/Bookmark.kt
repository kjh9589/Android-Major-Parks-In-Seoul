package com.teamnoyes.majorparksinseoul.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bookmark_table")
data class Bookmark(
    @PrimaryKey(autoGenerate = false)
    val P_IDX: Int,
    @ColumnInfo(name = "p_park")
    val P_PARK: String,
    @ColumnInfo(name = "p_list_content")
    val P_LIST_CONTENT: String,
    @ColumnInfo(name = "area")
    val AREA: String,
    @ColumnInfo(name = "open_dt")
    val OPEN_DT: String,
    @ColumnInfo(name = "main_equip")
    val MAIN_EQUIP: String,
    @ColumnInfo(name = "main_plants")
    val MAIN_PLANTS: String,
    @ColumnInfo(name = "guidance")
    val GUIDANCE: String,
    @ColumnInfo(name = "visit_road")
    val VISIT_ROAD: String,
    @ColumnInfo(name = "use_refer")
    val USE_REFER: String,
    @ColumnInfo(name = "p_img")
    val P_IMG: String,
    @ColumnInfo(name = "p_zone")
    val P_ZONE: String,
    @ColumnInfo(name = "p_addr")
    val P_ADDR: String,
    @ColumnInfo(name = "p_name")
    val P_NAME: String,
    @ColumnInfo(name = "p_admintel")
    val P_ADMINTEL: String,
    @ColumnInfo(name = "g_longitude")
    val G_LONGITUDE: String,
    @ColumnInfo(name = "g_latitude")
    val G_LATITUDE: String,
    @ColumnInfo(name = "longitude")
    val LONGITUDE: String,
    @ColumnInfo(name = "latitude")
    val LATITUDE: String,
    @ColumnInfo(name = "template_url")
    val TEMPLATE_URL: String,
    @ColumnInfo(name = "region_name")
    val RegionName: String
)