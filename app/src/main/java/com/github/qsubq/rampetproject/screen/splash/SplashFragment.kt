package com.github.qsubq.rampetproject.screen.splash

import android.animation.ObjectAnimator
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.qsubq.rampetproject.APP
import com.github.qsubq.rampetproject.R
import com.github.qsubq.rampetproject.databinding.FragmentSplashBinding
import kotlinx.coroutines.*


class SplashFragment : Fragment() {
   lateinit var binding : FragmentSplashBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSplashBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        binding.imageView.setImageResource(R.mipmap.ic_splash_image_foreground)
        CoroutineScope(Dispatchers.Main).launch {
            binding.ProgressBarSplash.max = 100
            val value = 90
            ObjectAnimator.ofInt(binding.ProgressBarSplash, "progress", value).setDuration(2000).start()

            delay(2000)
            APP.navController.navigate(R.id.action_splashFragment_to_characterFragment)
        }
    }

}