package com.app.spinwheel.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PickerHandViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Lorem Ipsum"
    }
    val text: LiveData<String> = _text
}