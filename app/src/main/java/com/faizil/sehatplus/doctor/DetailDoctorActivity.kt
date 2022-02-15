package com.faizil.sehatplus.doctor

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.faizil.sehatplus.R
import kotlinx.android.synthetic.main.activity_detail_doctor.*
import kotlinx.android.synthetic.main.activity_detail_forum.*

class DetailDoctorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_doctor)

        val nameDr: TextView = findViewById(R.id.docNameTv)
        val descrDr: TextView = findViewById(R.id.docTitleTv)


        val bundle: Bundle? = intent.extras
        val topic = bundle!!.getString("name")
        val desc = bundle.getString("descr")

        nameDr.text = topic
        descrDr.text = desc



        backBtn.setOnClickListener {
            onBackPressed()
            finish()
        }
    }
}