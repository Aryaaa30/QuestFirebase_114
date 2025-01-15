package com.example.session14_firebase.repository

import com.example.session14_firebase.model.Dosen_1
import kotlinx.coroutines.flow.Flow

interface Dosen_1Repository {
    suspend fun getAllDsn(): Flow<List<Dosen_1>>
    suspend fun insertDsn(Dosen1: Dosen_1)
    suspend fun updateDsn(nim: String,dosen1: Dosen_1)
    suspend fun deleteDsn(nim: String)
    suspend fun getDsn(nim: String): Flow<Dosen_1>
}