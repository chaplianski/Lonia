package com.example.testtask.data.repository

import com.example.testtask.data.storage.database.PortStorageImpl
import com.example.testtask.domain.repository.PortRepository
import javax.inject.Inject

class PortRepositoryImpl @Inject constructor (private val portStorage: PortStorageImpl): PortRepository {

    override fun getPort(): List<String> {
        return portStorage.getPortList()
    }
}