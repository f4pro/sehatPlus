package com.faizil.sehatplus.auth

import android.app.ProgressDialog
import android.content.ContentValues.TAG
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.faizil.sehatplus.databinding.ActivitySignupBinding
import com.faizil.sehatplus.home
import com.google.android.material.tabs.TabLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
//import com.google.firebase.storage.ktx.storage

class SignupActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignupBinding

    private lateinit var progressDialog: ProgressDialog

    private lateinit var firebaseAuth: FirebaseAuth

    private lateinit var firestore: FirebaseFirestore



    private var nameU = ""
    private var emailU = ""
    private var password = ""
    private var password2 = ""

    var db = Firebase.firestore

    //var storage = Firebase.storage



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Please wait")
        progressDialog.setMessage("Creating...")
        progressDialog.setCanceledOnTouchOutside(false)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.signUpBtn.setOnClickListener {
            validateForm()
        }
        binding.goBackTBtn.setOnClickListener {
            navUp()
        }

    }

    private fun validateForm() {
        //Getting Data
        nameU = binding.nameEt.text.toString().trim()
        emailU = binding.emailEt.text.toString().trim()
        password = binding.passwordEt.text.toString().trim()
        password2 = binding.passConfEt.text.toString().trim()

        //Validation
        if (!Patterns.EMAIL_ADDRESS.matcher(emailU).matches()) {
            binding.emailEt.setError("Invalid email")
        } else if (TextUtils.isEmpty(password)) {
            binding.passwordEt.error = "Password is required"
        } else if (TextUtils.isEmpty(nameU)) {
            binding.nameEt.error = "Please enter your name"
        } else if (password2 != password) {
            binding.passConfEt.error = "Re-check your password"
        } else {
            firebaseRegis()
        }
    }


    private fun firebaseRegis() {
        progressDialog.show()

        //nameU = binding.nameEt.text.toString().trim()
        firebaseAuth.createUserWithEmailAndPassword(emailU, password)
            .addOnSuccessListener {
                progressDialog.dismiss()

                val fireUser = firebaseAuth.currentUser
                val email = fireUser!!.email

                val profileNow = userProfileChangeRequest {
                    displayName = nameU
                }
                FirebaseAuth.getInstance().currentUser!!.updateProfile(profileNow)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful){
                            Log.d(TAG, "Profile Name created!")
                        }else{
                            Log.d(TAG, "Failed change profile!")
                        }
                    }
                Toast.makeText(this, "Success, Welcome!", Toast.LENGTH_SHORT).show()

                //Going to firestore
                val fireUID = firebaseAuth.currentUser?.uid
                val dataUser = hashMapOf(
                    "name" to nameU,
                    "email" to emailU
                )
                db.collection("user").document(fireUID.toString())
                    .set(dataUser)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful){
                            Log.d(TAG, "Stored!")
                        }
                    }

                finish()
                startActivity(Intent(this, home::class.java))
            }
            .addOnFailureListener { e ->
                progressDialog.dismiss()
                Toast.makeText(this, "Failed, reason: ${e.message}",
                    Toast.LENGTH_SHORT).show()
            }
    }

    private fun navUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}
