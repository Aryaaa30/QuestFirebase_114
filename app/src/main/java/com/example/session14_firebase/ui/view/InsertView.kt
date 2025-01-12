package com.example.session14_firebase.ui.view

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.session14_firebase.PenyediaViewModel
import com.example.session14_firebase.ui.viewmodel.InsertViewModel

@Composable
fun InsertMhsView(
    onBack: () -> Unit,
    onNavigate: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel : InsertViewModel = viewModel(factory = PenyediaViewModel.Factory)
){

}