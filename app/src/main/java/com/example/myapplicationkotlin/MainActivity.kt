package com.example.myapplicationkotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.myapplicationkotlin.databinding.ActivityMainBinding
import com.example.myapplicationkotlin.screens.Fragment1
import com.example.myapplicationkotlin.screens.Fragment2
import com.example.myapplicationkotlin.screens.Fragment3
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.GlobalContext
import org.koin.dsl.module
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var navController: NavController
    //val transaction  = supportFragmentManager.beginTransaction()
    var LastPoints = 0
    private val mViewModel : MainViewModel by viewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        GlobalContext.startKoin{
            androidLogger()
            androidContext(this@MainActivity)
            modules(module {
                viewModel{MainViewModel()}
            })
        }
        navController = Navigation.findNavController(this,R.id.nav_host_fragment)
        MAIN = this
    }

    override fun onBackPressed() {
        if (navController.navigateUp())
        {
            return
        }
        else
        {
            super.onBackPressed()
        }
    }
}