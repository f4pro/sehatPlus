package com.faizil.sehatplus.doctor

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.faizil.sehatplus.R
import com.faizil.sehatplus.databinding.ActivityDoctorBinding

class DoctorActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDoctorBinding
    private lateinit var doctorRecyclerView: RecyclerView
    private lateinit var doctorArrayList: ArrayList<Doctor>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDoctorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.titleActTv.text = "Doctor"


        binding.backBtn.setOnClickListener {
            onBackPressed()
            finish()
        }

        val dataDoctor = arrayListOf(
            Doctor("Budiman Setya Nov", "Specialis Dalam"),
            Doctor("Tito Hartanto", "Specialis Anak"),
            Doctor("Tika Rahma Nur Vionni", "Specialis Gigi"),
            Doctor("Annisa Fitri Maharani", "Specialis Gizi")
        )

        doctorRecyclerView = findViewById(R.id.doctorRv)
        doctorRecyclerView.layoutManager = LinearLayoutManager(this)
        doctorRecyclerView.setHasFixedSize(true)

        val adapter = DoctorAdapter(dataDoctor)
        doctorRecyclerView.adapter = adapter

        doctorArrayList = ArrayList(dataDoctor)

        adapter.setOnItemClickListener(object : DoctorAdapter.onItemClickListener {
            override fun onItemClick(position: Int) {
                val intent = Intent(this@DoctorActivity, DetailDoctorActivity::class.java)
                intent.putExtra("name", doctorArrayList[position].nameDr)
                intent.putExtra("descr", doctorArrayList[position].descDr)
                startActivity(intent)
            }

        })
    }

}