package com.example.myapplicationkotlin.screens

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.myapplicationkotlin.MainActivity
import com.example.myapplicationkotlin.databinding.Fragment5Binding
import com.example.myapplicationkotlin.retrofit.api.ProductApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Fragment5 : Fragment() {

    lateinit var binding: Fragment5Binding
    lateinit var mContext: Context
    var string: String = ""

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = Fragment5Binding.inflate(layoutInflater, container, false)
        val retrofit = Retrofit.Builder().baseUrl("https://dummyjson.com").addConverterFactory(
            GsonConverterFactory.create()
        ).build()
        val productApi = retrofit.create(ProductApi::class.java)
        binding.showButton.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val products = productApi.getProductById()
                activity?.runOnUiThread {
                    binding.productText.text = products.title + " " + products.category
                }

            }
        }

        return binding.root
    }
}