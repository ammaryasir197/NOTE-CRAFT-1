package com.example.calculator

import Newpakage.RomanEmpire
import Newpakage.Splashes
import Newpakage.imageview
import Newpakage.shamt
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.delay
import newwork.Home
import newwork.Profilez
import newwork.Randomss
import newwork.search
import okhttp3.internal.http2.Http2Reader
import kotlin.random.Random
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import data.DatabaseProvider
import data.NoteRepository
import data.NoteViewModel
import data.NoteViewModelFactory
import newwork.Home
import newwork.Notificationz
import newwork.search
import newwork.Profilez
import newwork.Scaffoldz
import ui.NotesScreen
import viewmodel.viewz
import kotlin.jvm.java

class MainActivity : ComponentActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
         installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
         val database = DatabaseProvider.getDatabase(applicationContext)
        val dao = database.notesDao()
        val repository = NoteRepository(dao)
        val factory = NoteViewModelFactory(repository)
        val viewModel =  ViewModelProvider(
            this,
            factory
        )[NoteViewModel::class.java]

        setContent {
            NotesScreen(
                viewModel = viewModel
            )



        }
    }




    }

