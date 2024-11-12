package com.dicoding.asclepius.view

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import com.dicoding.asclepius.R
import com.dicoding.asclepius.databinding.ActivityResultBinding
import java.io.IOException

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding
    private var currentImageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val label = intent.getStringExtra(LABEL)
        val score = intent.getFloatExtra(SCORE, 0F)
        val finalScore = score*100
        currentImageUri = intent.getParcelableExtra<Uri>(IMAGE)

        binding.resultText.text = "Category : $label \nScore = ${finalScore.toInt()}%"
        showImage()

        setSupportActionBar(binding.topAppBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun showImage(){
        currentImageUri?.let { uri ->
            try {
                val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, uri)
                binding.resultImage.setImageBitmap(bitmap)
            } catch (e: IOException){
                e.printStackTrace()
                Log.e("ResultActivity", "Error loading imange from URI: $e")
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    companion object{
        const val LABEL = "label"
        const val SCORE = "score"
        const val IMAGE = "image"
    }


}