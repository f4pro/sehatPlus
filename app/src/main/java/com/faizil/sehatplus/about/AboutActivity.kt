package com.faizil.sehatplus.about

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.faizil.sehatplus.R
import kotlinx.android.synthetic.main.activity_forum.*

class AboutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        backBtn.setOnClickListener {
            onBackPressed()
            finish()
        }
    }
}