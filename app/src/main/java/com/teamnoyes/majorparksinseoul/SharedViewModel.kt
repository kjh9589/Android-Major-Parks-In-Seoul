package com.teamnoyes.majorparksinseoul

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teamnoyes.majorparksinseoul.model.ModelDetailPark
import com.teamnoyes.majorparksinseoul.model.ModelPark
import com.teamnoyes.majorparksinseoul.model.Row
import com.teamnoyes.majorparksinseoul.repository.ParkRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SharedViewModel(private val repository: ParkRepository) : ViewModel() {
    companion object {
        const val LOADING = "LOADING"
        const val SUCCESS = "SUCCESS"
        const val CLIENT_ERROR = "CLIENT_ERROR"
        const val SERVER_ERROR = "SERVER_ERROR"
        const val UNKNOWN = "UNKNOWN"
    }

    private val _state = MutableLiveData<String>()
    val state: LiveData<String>
        get() = _state

    private var zoneHashMap: HashMap<String?, List<Row>>? = null

    fun getParksData() {
        viewModelScope.launch {
            _state.value = LOADING
            try {
                val data = repository.getParksData()

                data?.let { parkInfoDTO ->
                    parkInfoDTO.searchParkInfoService?.let { searchParkInfoService ->
                        searchParkInfoService.rESULT?.let { result ->
                            result.cODE.let { info ->
                                when(info) {
                                    "INFO-000" -> {
                                        searchParkInfoService.row?.let { row ->
                                            zoneHashMap = row.sortedBy { it.pIDX }.groupBy { it.pZONE } as HashMap<String?, List<Row>>
                                        }

                                        delay(1000)
                                        _state.value = SUCCESS
                                    }
                                    "ERROR-500" -> {
                                        _state.value = SERVER_ERROR
                                    }
                                    "ERROR-600" -> {
                                        _state.value = SERVER_ERROR
                                    }
                                    "ERROR-601" -> {
                                        _state.value = SERVER_ERROR
                                    }
                                    else -> {
                                        _state.value = CLIENT_ERROR
                                    }
                                }
                            }
                        }
                    }
                }
            } catch (e: Exception) {
                _state.value = UNKNOWN
            }
        }
    }

    private val _parkList = MutableLiveData<List<ModelPark>>()
    val parkList: LiveData<List<ModelPark>>
        get() = _parkList

    fun getParkList(zoneName: String) {
        viewModelScope.launch {
            val list = mutableListOf<ModelPark>()
            zoneHashMap?.let { hashMap ->
                hashMap[zoneName]?.let {
                    for (item in it) {
                        list.add(ModelPark.rowToModelPark(item, zoneName))
                    }
                }
            }
            _parkList.value = list
        }
    }

    private val _park = MutableLiveData<Row>()
    val park: LiveData<Row>
        get() = _park

    private val _detailPark = MutableLiveData<List<ModelDetailPark>>()
    val detailPark: LiveData<List<ModelDetailPark>>
        get() = _detailPark

    fun getDetailPark(zoneName: String, pIdx: Int) {
        val list = mutableListOf<ModelDetailPark>()

        zoneHashMap?.let { hashMap ->
            hashMap[zoneName]?.let { row ->
                for (item in row) {
                    if (item.pIDX == pIdx) {
                        _park.value = item
                        break
                    }
                }
            }
        }

        _park.value?.let { data ->
            list.add(ModelDetailPark(data.pPARK, data.pADDR, 0))
            list.add(ModelDetailPark("공원개요", data.pLISTCONTENT, 1))
            list.add(ModelDetailPark("주요시설", data.mAINEQUIP, 1))
            list.add(ModelDetailPark("면적", data.aREA, 1))
            list.add(ModelDetailPark("주요식물", data.mAINPLANTS, 1))
            list.add(ModelDetailPark("오시는길", data.vISITROAD, 1))
            list.add(ModelDetailPark("이용시 참고사항", data.uSEREFER, 1))
            list.add(ModelDetailPark("관리부서", data.pNAME, 1))
            list.add(ModelDetailPark("전화번호", data.pADMINTEL, 2))
        }

        _detailPark.value = list
    }


}