package com.example.myapplicationkotlin.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.myapplicationkotlin.MAIN
import com.example.myapplicationkotlin.MainViewModel
import com.example.myapplicationkotlin.R
import com.example.myapplicationkotlin.databinding.ActivityMainBinding
import com.example.myapplicationkotlin.databinding.Fragment1Binding


class Fragment1 : Fragment() {

    private lateinit var binding: Fragment1Binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = Fragment1Binding.inflate(layoutInflater, container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.PlayButton.setOnClickListener{
            MAIN.navController.navigate(R.id.action_fragment1_to_fragment3)
        }

        binding.SettingsButton.setOnClickListener{
            MAIN.navController.navigate(R.id.action_fragment1_to_fragment2)
        }

        binding.SixPRButton.setOnClickListener{
            MAIN.navController.navigate(R.id.action_fragment1_to_fragment4)
        }

        binding.FourPRButton.setOnClickListener{
            MAIN.navController.navigate(R.id.action_fragment1_to_fragment5)
        }
    }
}