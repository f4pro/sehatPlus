package com.faizil.sehatplus

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.faizil.sehatplus.databinding.ActivityHomeBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}