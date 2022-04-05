package com.example.testtask.data.repository

import com.example.testtask.data.storage.database.VasselStorageImpl
import com.example.testtask.domain.repository.VesselsRepository
import javax.inject.Inject

class VesselsRepositoryImpl @Inject constructor (private val vasselStorage: VasselStorageImpl): VesselsRepository {

    override fun getVessels(): List<String> {
        return vasselStorage.getVasselList()
    }
}