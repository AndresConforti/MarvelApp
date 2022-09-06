package com.example.marvelapp

import android.animation.Animator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.example.marvelapp.databinding.SplashScreenBinding
import com.example.marvelapp.mvvm.viewmodel.SplashScreenActivityViewModel

class SplashScreenActivity : AppCompatActivity() {
    private lateinit var binding: SplashScreenBinding
    private val viewModel: SplashScreenActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.splashScreenState.observe({ lifecycle }, ::changingState)
        setListener()
    }

    private fun changingState(SplashScreenData: SplashScreenActivityViewModel.SplashScreenData) {
        when (SplashScreenData.state) {
            SplashScreenActivityViewModel.SplashScreenState.DONE -> launchMainActivity()
        }
    }

    private fun launchMainActivity() {
        startActivity(MainActivity.newInstance(this))
        finish()
    }

    private fun setListener(){
        val animatorListener = object : Animator.AnimatorListener{
            override fun onAnimationEnd(animation: Animator?) = viewModel.splashDone()
            override fun onAnimationStart(animation: Animator?) {}
            override fun onAnimationCancel(animation: Animator?) {}
            override fun onAnimationRepeat(animation: Animator?) {}
        }
        binding.lottieSplashScreen.addAnimatorListener(animatorListener)
    }



}



