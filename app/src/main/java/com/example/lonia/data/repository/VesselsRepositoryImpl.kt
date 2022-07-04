package com.example.lonia.data.repository

import com.example.lonia.data.storage.network.retrofit.InfoBriefcaseApiHelper
import com.example.lonia.domain.repository.VesselsRepository
import javax.inject.Inject

class VesselsRepositoryImpl @Inject constructor (
 //  private val vasselStorage: VasselStorageImpl,
    private val infoBriefcaseApiHelper: InfoBriefcaseApiHelper):
    VesselsRepository {

    override suspend fun getVessels(): List<String> {
        return infoBriefcaseApiHelper.getInfoBriefcase().vessel
   //     return vasselStorage.getVasselList()
    }
}