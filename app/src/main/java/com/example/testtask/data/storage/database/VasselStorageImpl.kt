package com.example.testtask.data.storage.database

import com.example.testtask.data.storage.VasselStorage
import javax.inject.Inject

class VasselStorageImpl @Inject constructor() : VasselStorage {

    override fun getVasselList(): List<String> {
        return listOf(
            "DROMEAS", "ESTIA", "EVRIDIKI", "FIDIAS", "GEA",
            "KOUROS", "KRITON", "LYSIAS", "ORFEAS", "PLOUTOS", "THEO T"
        )
    }
}