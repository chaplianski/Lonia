package com.example.lonia.domain.repository

interface VesselsRepository {

    suspend fun getVessels(): List<String>
}