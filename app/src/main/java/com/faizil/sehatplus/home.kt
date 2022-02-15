package com.faizil.sehatplus

import android.animation.LayoutTransition
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import com.faizil.sehatplus.about.AboutActivity
import com.faizil.sehatplus.auth.LoginActivity
import com.faizil.sehatplus.databinding.ActivityHomeBinding
import com.faizil.sehatplus.doctor.DoctorActivity
import com.faizil.sehatplus.forum.ForumActivity
import com.google.firebase.auth.FirebaseAuth
import java.time.LocalTime


class home : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var alertDialog: AlertDialog

    private lateinit var transition: LayoutTransition
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()
        checkUser()

        binding.logoutBtn.setOnClickListener() {

            val builder = AlertDialog.Builder(this)
            builder.setTitle(R.string.titleDialogLO)
            builder.setMessage("You're about to signing out, are you sure?")
            //builder.setIcon(R.drawable.ic_baseline_logout)
            builder.setPositiveButton("Yes") { dialogInterface, which ->
                firebaseAuth.signOut()
                Toast.makeText(this, "Goodbye!", Toast.LENGTH_SHORT).show()
                checkUser()
            }
            builder.setNegativeButton("No") { dialogInterface, which ->
                //closeContextMenu()
            }

            alertDialog = builder.create()
            builder.show()

        }
        binding.aboutBtn.setOnClickListener {
            startActivity(Intent(this, AboutActivity::class.java))
        }

        binding.forumBtn.setOnClickListener {
            startActivity(Intent(this, ForumActivity::class.java))
        }
        binding.doctorBtn.setOnClickListener {
            startActivity(Intent(this, DoctorActivity::class.java))
        }

        val currentTime = LocalTime.now()
        val curretisMorn = LocalTime.of(11, 59)
        val currentisNoon = LocalTime.of(14, 59)
        val currentisEv = LocalTime.MAX
        if (currentTime < curretisMorn) {
            binding.greetTv.text = "Good Morning!"
        } else if (currentTime < currentisNoon) {
            binding.greetTv.text = "Good Afternoon!"
        } else if (currentTime < currentisEv) {
            binding.greetTv.text = "Good Evening!"
        }



        binding.forumBtn.setOnClickListener {
            startActivity(Intent(this, ForumActivity::class.java))
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }


    private fun checkUser() {
        val fireUser = firebaseAuth.currentUser
        if (fireUser != null) {
            val name = fireUser.displayName
            binding.usernameTv.text = name
        } else {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

    }
}
