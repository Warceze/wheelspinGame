package com.app.spinwheel.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.app.spinwheel.databinding.SpinnerFragmentBinding
import kotlin.random.Random

class SpinnerFragment : Fragment() {

    private var _binding: SpinnerFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var spinnerViewModel: SpinnerViewModel

    private var isSpinning = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = SpinnerFragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root

        spinnerViewModel = ViewModelProvider(this).get(SpinnerViewModel::class.java)

        val textViewTitle: TextView = binding.titleTextView
        spinnerViewModel.text.observe(viewLifecycleOwner) {
            textViewTitle.text = it
        }

        val questionText: TextView = binding.questionTextView
        spinnerViewModel.questText.observe(viewLifecycleOwner) {
            questionText.text = it
        }

        val spinnerImage: ImageView = binding.spinnerImageView
        spinnerViewModel.spinnerImage.observe(viewLifecycleOwner) {
            spinnerImage.setImageResource(it)
        }

        val rectangleWte: ImageView = binding.rectangleWhattoeat
        spinnerViewModel.rectangleImage.observe(viewLifecycleOwner) {
            rectangleWte.setImageResource(it)
        }

        val resetButton: Button = binding.resetButton
        resetButton.setOnClickListener {
            spinnerViewModel.resetValues()
        }

        spinnerImage.setOnClickListener {
            if (!isSpinning) {
                spinWheel()
            }
        }

        spinnerViewModel.selectedSector.observe(viewLifecycleOwner) { sector ->
            // Update UI with selected sector name
            sector?.let {
                binding.questionTextView.text = it.name
            }
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun spinWheel() {
        isSpinning = true // Устанавливаем флаг, указывающий на то, что анимация в процессе

        // Получаем текущее значение угла вращения, либо используем 0, если значение отсутствует
        val fromDegree = spinnerViewModel.rotationDegree.value ?: 0f

        // Вычисляем конечный угол вращения, добавляя случайное значение от 0 до 360 и 720 для дополнительных оборотов
        val toDegree = fromDegree + (Random.nextFloat() * 360) + 720

        // Создаем анимацию вращения от текущего угла до нового угла
        val rotateAnimation = RotateAnimation(
            fromDegree, toDegree,
            Animation.RELATIVE_TO_SELF, 0.5f,
            Animation.RELATIVE_TO_SELF, 0.5f
        )

        rotateAnimation.duration = 3000 // Продолжительность анимации в миллисекундах
        rotateAnimation.fillAfter = true // Сохраняем конечное состояние анимации

        rotateAnimation.interpolator = AccelerateDecelerateInterpolator()

        rotateAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {}

            override fun onAnimationEnd(animation: Animation?) {
                // После завершения анимации определяем сектор, который находится в верхней части колеса
                val degree = toDegree % 360
                spinnerViewModel.updateRotationDegree(degree) // Сохраняем текущий угол вращения

                // Определяем сектор по текущему углу и обновляем ViewModel
                spinnerViewModel.spinWheel(degree)

                isSpinning = false // Сбрасываем флаг вращения
            }

            override fun onAnimationRepeat(animation: Animation?) {}
        })

        binding.spinnerImageView.startAnimation(rotateAnimation)
    }
}
