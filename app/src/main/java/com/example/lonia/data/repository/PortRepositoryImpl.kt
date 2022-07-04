package com.example.lonia.data.repository

import com.example.lonia.data.storage.network.retrofit.InfoBriefcaseApiHelper
import com.example.lonia.domain.repository.PortRepository
import javax.inject.Inject

class PortRepositoryImpl @Inject constructor (
    private val infoBriefcaseApiHelper: InfoBriefcaseApiHelper
 //   private val portStorage: PortStorageImpl
    ):
    PortRepository {

    override suspend fun getPort(): List<String> {
        return infoBriefcaseApiHelper.getInfoBriefcase().port
  //      return portStorage.getPortList()
    }
}