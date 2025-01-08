package com.example.session14_firebase

import android.app.Application
import com.example.session14_firebase.dependenciesinjection.AppContainer
import com.example.session14_firebase.dependenciesinjection.MahasiswaContainer

class MahasiswaApplications: Application(){
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = MahasiswaContainer()
    }
}