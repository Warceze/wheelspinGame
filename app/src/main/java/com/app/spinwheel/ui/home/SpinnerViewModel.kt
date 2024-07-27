package com.app.spinwheel.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.spinwheel.R

class SpinnerViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "What to eat tonight?"
    }
    val text: LiveData<String> = _text

    private val _questText = MutableLiveData<String>().apply {
        value = "Tap the wheel to spin!"
    }
    val questText: LiveData<String> = _questText

    private val _spinnerImage = MutableLiveData<Int>().apply {
        value = R.drawable.spinner // Initial image resource
    }
    val spinnerImage: LiveData<Int> = _spinnerImage

    private val _rectangleImage = MutableLiveData<Int>().apply {
        value = R.drawable.rectangle_eat // Initial image resource
    }
    val rectangleImage: LiveData<Int> = _rectangleImage

    private val _selectedSector = MutableLiveData<Sectors?>()
    val selectedSector: LiveData<Sectors?> = _selectedSector

    private val _rotationDegree = MutableLiveData<Float>()
    val rotationDegree: LiveData<Float> = _rotationDegree

    fun spinWheel(degree: Float) {
        _rotationDegree.value = degree
        _selectedSector.value = getSectorByDegree(degree)
        _questText.value = _selectedSector.value?.name ?: "Try Again!"
    }

    fun updateRotationDegree(degree: Float) {
        _rotationDegree.value = degree
    }


    fun resetValues() {
        _text.value = "What to eat tonight?"
        _questText.value = "Tap the wheel to spin!"
        _spinnerImage.value = R.drawable.spinner
        _rectangleImage.value = R.drawable.rectangle_eat
        _selectedSector.value = null
    }
}
