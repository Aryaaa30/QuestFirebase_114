package com.example.session14_firebase.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.session14_firebase.model.Mahasiswa
import com.example.session14_firebase.repository.MahasiswaRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import kotlin.Exception

sealed class HomeUiState{
    data class Success(val mahasiswa: List<Mahasiswa>): HomeUiState()
    data class Error(val exception: Throwable) : HomeUiState()
    object Loading: HomeUiState()
}

class HomeViewModel(
    private val mhs : MahasiswaRepository
): ViewModel(){
    var mhsUIState: HomeUiState by mutableStateOf(HomeUiState.Loading)
        private set

    init {
        getMhs()
    }

    fun getMhs(){
        viewModelScope.launch {
            mhs.getAllMhs()
                .onStart {
                    mhsUIState = HomeUiState.Loading
                }
                .catch {
                    mhsUIState = HomeUiState.Error(it)
                }
                .collect{
                    mhsUIState = if (it.isEmpty()){
                        HomeUiState.Error(Exception("Belum Ada Daftar Mahasiswa"))
                    }else{
                        HomeUiState.Success(it)
                    }
                }
        }
    }

    fun deleteMhs(nim: String){
        viewModelScope.launch {
            try {
                mhs.deleteMhs(nim)
            }catch (e : Exception){
                mhsUIState = HomeUiState.Error(e)
            }
        }
    }
}
