package com.example.session14_firebase.repository

import com.example.session14_firebase.model.Mahasiswa
import kotlinx.coroutines.flow.Flow

interface MahasiswaRepository {
    suspend fun getAllMhs(): Flow<List<Mahasiswa>>
    suspend fun insertMhs(mahasiswa: Mahasiswa)
    suspend fun updateMhs(mahasiswa: Mahasiswa)
    suspend fun deleteMhs(mahasiswa: Mahasiswa)
    suspend fun getMhs(nim: String): Flow<Mahasiswa>
}