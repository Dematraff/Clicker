package com.example.myapplicationkotlin.screens

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplicationkotlin.MAIN
import com.example.myapplicationkotlin.MainViewModel
import com.example.myapplicationkotlin.R
import com.example.myapplicationkotlin.databinding.Fragment3Binding
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.GlobalContext
import org.koin.core.context.startKoin
import org.koin.dsl.module

class Fragment3 : Fragment() {

    lateinit var binding: Fragment3Binding
    var Points = 0
    lateinit var mViewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mViewModel= ViewModelProvider(MAIN).get(MainViewModel::class.java)
        binding=Fragment3Binding.inflate(layoutInflater,container,false)

        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Points = MAIN.LastPoints
        binding.PointsText.text = Points.toString()
        binding.ClickButton.setOnClickListener{
            Points += 1
            mViewModel.liveData.value = Points.toString()
            mViewModel.liveData.observe(MAIN, Observer {
                binding.PointsText.text = it
            })
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        MAIN.LastPoints = Points
        Log.d("msg", MAIN.LastPoints.toString())
    }
}
