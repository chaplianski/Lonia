package com.example.testtask.data.repository

import com.example.testtask.data.storage.PortStorage
import com.example.testtask.data.storage.database.PortStorageImpl
import com.example.testtask.domain.repository.PortRepository

class PortRepositoryImpl (private val portStorage: PortStorageImpl): PortRepository {

    override fun getPort(): List<String> {
        return portStorage.getPortList()
    }
}