package com.faizil.sehatplus.forum

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.faizil.sehatplus.databinding.FragmentForumBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.time.LocalDate

class ForumBottomSheet : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentForumBottomSheetBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var database: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        firebaseAuth = FirebaseAuth.getInstance()
        binding = FragmentForumBottomSheetBinding.inflate(
            LayoutInflater
                .from(context), container, false
        )
        binding.PostBtn.setOnClickListener {
            saveData()
        }

        return binding.root
    }

    private fun reset() {
        binding.topicEt.text?.clear()
        binding.insideEt.text?.clear()
        dismiss()
    }


    private fun saveData() {
        val topicFr = binding.topicEt.text.toString()
        val descriptionFr = binding.insideEt.text.toString()
        val unameFr = firebaseAuth.currentUser!!.displayName.toString()
        val dateFr = LocalDate.now().toString()

        database = FirebaseDatabase.getInstance().getReference("Forums")
        val Forum = Forum(topicFr, descriptionFr, unameFr, dateFr)
        database.push().setValue(Forum)
            .addOnSuccessListener {
                binding.topicEt.text?.clear()
                binding.insideEt.text?.clear()
                dismiss()
                Toast.makeText(activity, "Success", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                Toast.makeText(activity, "Failed", Toast.LENGTH_SHORT).show()
            }
    }
}
