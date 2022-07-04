package com.example.lonia.data.repository

import com.example.lonia.data.storage.network.retrofit.InfoBriefcaseApiHelper
import com.example.lonia.domain.repository.InspectionSourceRepository
import javax.inject.Inject

class InspectionSourceRepositoryImpl @Inject constructor(
 //   private val inspectorSourceStorage: InspectionSourceStorage
    private val infoBriefcaseApiHelper: InfoBriefcaseApiHelper
) :
    InspectionSourceRepository {

    override suspend fun getInspectionSource(): List<String> {
        return infoBriefcaseApiHelper.getInfoBriefcase().inspecstion_source
        //return inspectorSourceStorage.getInspectorSource()
    }
}