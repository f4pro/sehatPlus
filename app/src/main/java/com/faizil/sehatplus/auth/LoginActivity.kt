package com.faizil.sehatplus.auth

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.ProgressBar
import android.widget.Toast
import com.faizil.sehatplus.R
import com.faizil.sehatplus.databinding.ActivityLoginBinding
import com.faizil.sehatplus.home
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeout
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit
import java.util.regex.Pattern

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    //private lateinit var progressDialog: ProgressDialog

    private lateinit var firebaseAuth: FirebaseAuth

    private var email = ""
    private var password = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
/*
        progressDialog.setTitle("Please wait")
        progressDialog.setCanceledOnTouchOutside(false)
*/


        firebaseAuth = FirebaseAuth.getInstance()
        checkUser()

        binding.signUpTe.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
        }

        binding.loginBtn.setOnClickListener {
            validateData()

        }
    }

    private fun validateData() {
        //Getting Data
        email = binding.emailEt.text.toString().trim()
        password = binding.passwordEt.text.toString().trim()

        //Validation

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this, "Email cannot be empty!", Toast.LENGTH_SHORT).show()
        } else if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Password cannot be empty!", Toast.LENGTH_SHORT).show()
        } else {
            firebaseLogin()
        }

    }


    private fun firebaseLogin() {
        //progressDialog.show()
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                val fireUser = firebaseAuth.currentUser
                val name = fireUser!!.displayName
                Toast.makeText(this, "Hello $name!", Toast.LENGTH_SHORT).show()

                startActivity(Intent(this, home::class.java))
                finish()
            }
            .addOnFailureListener { e ->
                //progressDialog.dismiss()
                Toast.makeText(this, "Failed, reason: ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }


    private fun checkUser() {
        val fireUser = firebaseAuth.currentUser
        if (fireUser != null) {
            startActivity(Intent(this, home::class.java))
            finish()
        }

    }


}