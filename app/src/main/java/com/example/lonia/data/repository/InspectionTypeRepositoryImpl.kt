package com.example.lonia.data.repository

import com.example.lonia.data.storage.network.retrofit.InfoBriefcaseApiHelper
import com.example.lonia.domain.repository.InspectionTypeRepository
import javax.inject.Inject

class InspectionTypeRepositoryImpl @Inject constructor(
//    private val inspectionTypeStorage: InspectionTypeStorageImpl
    private val infoBriefcaseApiHelper: InfoBriefcaseApiHelper
    ) :
    InspectionTypeRepository {

    override suspend fun getInspectionType(): List<String> {
        return infoBriefcaseApiHelper.getInfoBriefcase().inspection_type
        //return inspectionTypeStorage.getInspectionTypeList()
    }
}