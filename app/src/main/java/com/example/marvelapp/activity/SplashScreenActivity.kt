package com.example.marvelapp.activity

import android.animation.Animator
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.marvelapp.activity.MainActivity
import com.example.marvelapp.databinding.SplashScreenBinding
import com.example.marvelapp.mvvm.viewmodel.SplashScreenViewModel

class SplashScreenActivity : AppCompatActivity() {
    private lateinit var binding: SplashScreenBinding
    private val viewModel: SplashScreenViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.splashScreenState.observe({ lifecycle }, ::changingState)
        viewModel.startAnimation()
    }

    private fun changingState(SplashScreenData: SplashScreenViewModel.SplashScreenData) {
        when (SplashScreenData.state) {
            SplashScreenViewModel.SplashScreenState.START -> setListener()
            SplashScreenViewModel.SplashScreenState.DONE -> launchMainActivity()
        }
    }

    private fun launchMainActivity() {
        startActivity(MainActivity.newInstance(this))
        finish()
    }

    private fun setListener() {
        val animatorListener = object : Animator.AnimatorListener {
            override fun onAnimationEnd(animation: Animator?) = viewModel.splashDone()
            override fun onAnimationStart(animation: Animator?) {}
            override fun onAnimationCancel(animation: Animator?) {}
            override fun onAnimationRepeat(animation: Animator?) {}
        }
        binding.lottieSplashScreen.addAnimatorListener(animatorListener)
    }
}
