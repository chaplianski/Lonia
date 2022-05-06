package com.example.lonia.data.storage.database

import com.example.lonia.data.storage.storagies.VasselStorage
import javax.inject.Inject

class VasselStorageImpl @Inject constructor() : VasselStorage {

    override fun getVasselList(): List<String> {
        return listOf(
            "DREAMS", "ESTIA", "EVRIDIKI", "FIDIAS", "GEA",
            "KOUROS", "KRITON", "LYSIAS", "ORFEAS", "PLOUTOS", "THEO T"
        )
    }
}