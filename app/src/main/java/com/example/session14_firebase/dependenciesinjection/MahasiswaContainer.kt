package com.example.session14_firebase.dependenciesinjection

import com.example.session14_firebase.repository.MahasiswaRepository
import com.example.session14_firebase.repository.NetworkRepositoryMhs
import com.google.firebase.firestore.FirebaseFirestore

interface AppContainer{
    val mahasiswaRepository: MahasiswaRepository
}

class MahasiswaContainer : AppContainer{
    private val firebase: FirebaseFirestore = FirebaseFirestore.getInstance()

    override val mahasiswaRepository: MahasiswaRepository by lazy {
        NetworkRepositoryMhs(firebase)
    }
}