package com.example.session14_firebase.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.session14_firebase.model.Dosen_1
import com.example.session14_firebase.model.Dosen_2
import com.example.session14_firebase.model.Mahasiswa
import com.example.session14_firebase.repository.MahasiswaRepository
import kotlinx.coroutines.launch

class InsertViewModel(
    private val mhs: MahasiswaRepository,
): ViewModel(){

    var uiEvent: InsertUiState by mutableStateOf(InsertUiState())
        private set

    var uiState: FormState by mutableStateOf(FormState.Idle)
        private set

    // Memperbarui state berdasarkan input pengguna
    fun updateState(mahasiswaEvent: MahasiswaEvent) {
        uiEvent = uiEvent.copy(
            insertUiEvent = mahasiswaEvent,
        )
    }

    fun validateFields(): Boolean{
        val event = uiEvent.insertUiEvent
        val errorState = FormErrorState(
            nim = if (event.nim.isNotEmpty()) null else "NIM tidak boleh kosong",
            nama = if (event.nama.isNotEmpty()) null else "Nama tidak boleh kosong",
            jenisKelamin = if (event.jenisKelamin.isNotEmpty()) null else "Jenis Kelamin tidak boleh kosong",
            alamat = if (event.alamat.isNotEmpty()) null else "Alamat tidak boleh kosong",
            kelas = if (event.kelas.isNotEmpty()) null else "Kelas tidak boleh kosong",
            judul_sktipsi = if (event.judul_sktipsi.isNotEmpty()) null else "Judul Skripsi tidak boleh kosong",
            angkatan = if (event.angkatan.isNotEmpty()) null else "Angkatan tidak boleh kosong",
            dosen1 = if (event.dosen1.isNotEmpty()) null else "Angkatan tidak boleh kosong",
            dosen2 = if (event.dosen2.isNotEmpty()) null else "Angkatan tidak boleh kosong"
            )

        uiEvent = uiEvent.copy(isEntryValid = errorState)
        return errorState.isValid()
    }


    fun insertMhs() {
        if (validateFields()) {
            viewModelScope.launch {
                uiState = FormState.Loading
                try {
                    mhs.insertMhs(uiEvent.insertUiEvent.toMhsModel())
                    uiState = FormState.Success("Data berhasil disimpan")
                } catch (e: Exception) {
                    uiState = FormState.Error("Data gagal disimpan")
                }
            }
        } else {
            uiState = FormState.Error("Data tidak valid")
        }
    }

    fun resetForm() {
        uiEvent = InsertUiState()
        uiState = FormState.Idle
    }

    fun resetSnackBarMessage() {
        uiState = FormState.Idle
    }
}

sealed class FormState {
    object Idle : FormState()
    object Loading : FormState()
    data class Success(val message: String) : FormState()
    data class Error(val message: String) : FormState()
}

data class InsertUiState(
    val insertUiEvent: MahasiswaEvent = MahasiswaEvent(),
    val isEntryValid: FormErrorState = FormErrorState(),
)

data class FormErrorState(
    val nim: String? = null,
    val nama: String? = null,
    val jenisKelamin: String? = null,
    val alamat: String? = null,
    val kelas: String? = null,
    val judul_sktipsi: String? = null,
    val angkatan: String? = null,
    val dosen1: String? = null,
    val dosen2: String? = null,
) {
    fun isValid(): Boolean {
        return nim == null && nama == null && jenisKelamin == null &&
                alamat == null && judul_sktipsi == null && kelas == null && angkatan == null &&
        dosen1 == null && dosen2 == null
    }
}

// data class Variabel yang menyimpan data input form
data class MahasiswaEvent(
    val nim: String = "",
    val nama: String = "",
    val jenisKelamin: String = "",
    val alamat: String = "",
    val kelas: String = "",
    val judul_sktipsi: String = "",
    val angkatan: String = "",
    val dosen1: String = "",
    val dosen2: String = "",

)

// Menyimpan input form ke dalam entity
fun MahasiswaEvent.toMhsModel(): Mahasiswa = Mahasiswa(
    nim = nim,
    nama = nama,
    jenis_kelamin = jenisKelamin,
    alamat = alamat,
    kelas = kelas,
    judul_skripsi = judul_sktipsi,
    angkatan = angkatan,
    dosen1 = dosen1,
    dosen2 = dosen2
)



