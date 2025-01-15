package com.example.session14_firebase.ui.navigation

interface DestinasiNavigasi {
    val route: String
    val titleRes: String
}

object DestinasiHome : DestinasiNavigasi {
    override val route: String = "home"
    override val titleRes: String = "Home"
}

object DestinasiDetail: DestinasiNavigasi {
    override val route = "detail"
    override val titleRes = "Detail Mahasiswa"
    const val NIM = "nim"
    val routesWithArg = "$route/{$NIM}"
}

object DestinasiEntry: DestinasiNavigasi {
    override val route ="item_entry"
    override val titleRes = "Insert Mahasiswa"
}

object DestinasiUpdate: DestinasiNavigasi {
    override val route = "update"
    override val titleRes = "Edit Mahasiswa"
    const val NIM = "nim"
    val routesWithArg = "$route/{$NIM}"
}