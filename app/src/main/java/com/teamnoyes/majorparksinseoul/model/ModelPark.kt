package com.teamnoyes.majorparksinseoul.model

data class ModelPark(
    val P_IDX: Int,
    val P_PARK: String?,
    val GUIDANCE: String?,
    val P_ADDR: String?,
    val region: String
) {
    companion object {
        fun rowToModelPark(row: Row, zone: String): ModelPark {
            return ModelPark(row.pIDX, row.pPARK, row.gUIDANCE, row.pADDR, zone)
        }
    }
}
