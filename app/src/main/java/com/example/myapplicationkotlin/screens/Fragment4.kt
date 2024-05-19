package com.example.myapplicationkotlin.screens

import android.content.ContentResolver
import android.content.ContentValues
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.media.MediaScannerConnection
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.core.graphics.drawable.toBitmap
import androidx.core.view.drawToBitmap
import androidx.navigation.fragment.findNavController
import com.example.myapplicationkotlin.R
import com.example.myapplicationkotlin.databinding.Fragment4Binding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream
import java.util.UUID

class Fragment4 : Fragment() {

    lateinit var binding: Fragment4Binding;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = Fragment4Binding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.showImageButton.setOnClickListener {
            if (binding.URLeditText.text != null) {
                val imageURL = binding.URLeditText.text.toString()

                GlobalScope.launch(Dispatchers.Main) {
                    Picasso.get().load(imageURL).into(binding.imageView)
                }
                GlobalScope.launch(Dispatchers.Default){
                    saveImage(imageURL)
                }

                //Thread.sleep(10000)
                //val bitmap = binding.imageView.drawable.toBitmap()
                //val fileOutputStream = FileOutputStream(Environment.DIRECTORY_PICTURES)
                //bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream)
                //MediaStore.Images.Media.insertImage(context.contentResolver, bitmap,"MyScreen", null)

            } else {
                Toast.makeText(context, "Неверная ссылка", Toast.LENGTH_SHORT)
            }
        }
    }

    private fun saveImage(ImageUrl : String) {
        val imageURL = binding.URLeditText.text.toString()
        val fileName = UUID.randomUUID().toString()+".jpg"
        val directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
        try {
            val bitmap : Bitmap
            val inputStream = java.net.URL(imageURL).openStream()
            bitmap = BitmapFactory.decodeStream(inputStream)

            if (!directory.exists()){
                directory.mkdirs()
            }
            val file = File(directory,fileName)
            val outputStream : OutputStream = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
            outputStream.flush()
            outputStream.close()

            val contentValues = ContentValues().apply {
                put(MediaStore.MediaColumns.DATA, file.absolutePath)
                put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
                put(MediaStore.MediaColumns.TITLE, fileName)
            }
            val uri = context?.contentResolver?.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
            MediaScannerConnection.scanFile(context, arrayOf(file.absolutePath), null, null)
        }catch (e:Exception){
            e.printStackTrace()
        }
    }
}