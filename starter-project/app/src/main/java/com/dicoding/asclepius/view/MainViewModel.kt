package com.dicoding.asclepius.view

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private val _image = MutableLiveData<Uri>()
    val image: LiveData<Uri> = _image

    fun insertImage(image: Uri){
        _image.value = image
    }
}