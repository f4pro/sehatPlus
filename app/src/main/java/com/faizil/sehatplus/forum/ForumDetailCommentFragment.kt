package com.faizil.sehatplus.forum

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.faizil.sehatplus.R
import com.faizil.sehatplus.databinding.FragmentForumBottomSheetBinding
import com.faizil.sehatplus.databinding.FragmentForumDetailCommentBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.time.LocalDate

class ForumDetailCommentFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentForumDetailCommentBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var database: DatabaseReference
    private lateinit var firestore: FirebaseFirestore

    var db = Firebase.firestore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        firebaseAuth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()
        binding = FragmentForumDetailCommentBinding.inflate(
            LayoutInflater.from(context), container, false
        )

        binding.PostBtn.setOnClickListener {
            saveData()
        }
        return binding.root
    }

    private fun saveData() {
        database =  FirebaseDatabase.getInstance().getReference("Forums")
        val commentFr = binding.insideEt.text.toString()
        val unameFr = firebaseAuth.currentUser!!.displayName.toString()
        val dateFr = LocalDate.now().toString()

        db.collection("frComment").document()

    }

}