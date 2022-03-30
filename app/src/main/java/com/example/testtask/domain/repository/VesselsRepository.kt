package com.example.testtask.domain.repository

import com.example.testtask.domain.model.Vessel

interface VesselsRepository {

    fun getVessels(): List<String>
}