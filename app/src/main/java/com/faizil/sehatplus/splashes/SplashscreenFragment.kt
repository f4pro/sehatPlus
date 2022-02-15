package com.faizil.sehatplus.splashes

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.faizil.sehatplus.R

class SplashscreenFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Handler().postDelayed({
            if(onBoardFinised()){
                findNavController().navigate(R.id.action_splashscreenFragment_to_getStartedFragment)
            }else{
                findNavController().navigate(R.id.action_splashscreenFragment_to_home3)
            }
        },2000)
        return inflater.inflate(R.layout.fragment_splashscreen, container, false)
    }

    private fun onBoardFinised(): Boolean {
        val sharedPref = requireActivity()
            .getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        return sharedPref.getBoolean("Selesai",false)
    }
}