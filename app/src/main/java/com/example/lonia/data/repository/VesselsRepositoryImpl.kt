package com.example.lonia.data.repository

import com.example.lonia.data.storage.database.VasselStorageImpl
import com.example.lonia.domain.repository.VesselsRepository
import javax.inject.Inject

class VesselsRepositoryImpl @Inject constructor (private val vasselStorage: VasselStorageImpl):
    VesselsRepository {

    override fun getVessels(): List<String> {
        return vasselStorage.getVasselList()
    }
}