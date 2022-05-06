package com.example.lonia.data.repository

import com.example.lonia.data.storage.database.PortStorageImpl
import com.example.lonia.domain.repository.PortRepository
import javax.inject.Inject

class PortRepositoryImpl @Inject constructor (private val portStorage: PortStorageImpl):
    PortRepository {

    override fun getPort(): List<String> {
        return portStorage.getPortList()
    }
}