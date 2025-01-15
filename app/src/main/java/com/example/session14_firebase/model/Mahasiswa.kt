package com.example.session14_firebase.model

data class Mahasiswa(
    val nim: String,
    val nama: String,
    val alamat: String,
    val jenis_kelamin: String,
    val kelas: String,
    val judul_skripsi : String,
    val angkatan: String
)
{
    constructor(

    ):this("", "","","","","","")
}






