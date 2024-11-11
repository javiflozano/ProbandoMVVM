package com.iessanalberto.dam2.probandomvvm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.iessanalberto.dam2.probandomvvm.ui.theme.ProbandoMVVMTheme
import com.iessanalberto.dam2.probandomvvm.ui.viewmodels.MainViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProbandoMVVMTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    BodyContent(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun BodyContent(modifier: Modifier = Modifier,
                mainViewModel: MainViewModel = viewModel()
                ) {
    // VERSIÓN BY REMEMBER
    //var miTexto by rememberSaveable { mutableStateOf("") }
    //var otroTexto by rememberSaveable { mutableStateOf("") }
    //var contador by rememberSaveable { mutableIntStateOf(0) }

    // VERSIÓN VIEWMODEL
    val mainUiState by mainViewModel.uiState.collectAsState()

    Column (modifier = modifier.fillMaxSize().padding(30.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
        ){
        Text(text = mainUiState.miTexto)
        Text(text = "Contador: ${mainUiState.contador}")
        OutlinedTextField(value = mainUiState.miTexto, onValueChange = {mainViewModel.onMiTextoChanged(it)}, label = { Text(text = "Escribe algo")})
        Button(onClick = {mainViewModel.addCounter()}) { Text(text = "Púlsame")}

        OutlinedTextField(value = mainViewModel.otroTexto, onValueChange = {mainViewModel.onOtroTextoChanged(it)}, label = { Text(text = "Escribe lo que quieras")})
    }
}

