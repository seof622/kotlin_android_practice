package com.example.amphibians.data

import com.example.amphibians.network.AmphibianApiService
import com.example.amphibians.network.AmphibianInfo

interface AmphibianInfoRepository {
    suspend fun getAmphibianInfo(): List<AmphibianInfo>
}

class NetworkAmphibianInfoRepository (
    private val amphibianApiService: AmphibianApiService) : AmphibianInfoRepository {
    override suspend fun getAmphibianInfo(): List<AmphibianInfo> = amphibianApiService.getInfo()
}