package com.example.lonia.domain.repository

interface PortRepository {

    suspend fun getPort (): List<String>
}