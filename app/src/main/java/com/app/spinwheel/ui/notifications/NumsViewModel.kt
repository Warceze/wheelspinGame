package com.app.spinwheel.ui.notifications

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NumsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Dolor sit amet"
    }
    val text: LiveData<String> = _text
}