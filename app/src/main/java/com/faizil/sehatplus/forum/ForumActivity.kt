package com.faizil.sehatplus.forum

import android.content.Intent
import android.os.Bundle
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.faizil.sehatplus.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_detail_forum.*
import kotlinx.android.synthetic.main.activity_forum.*
import kotlinx.android.synthetic.main.activity_forum.addForumBtn

class ForumActivity : AppCompatActivity() {
    //private lateinit var binding: ActivityForumBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var forumRecyclerView: RecyclerView
    private lateinit var dbref: DatabaseReference
    private lateinit var forumArrayList: ArrayList<Forum>
    private lateinit var adapter: Adapter

    lateinit var topicFr : Array<String>
    lateinit var descFr: Array<String>
    lateinit var unameFr : Array<String>
    lateinit var dateFr : Array<String>



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //binding = ActivityForumBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_forum)
        firebaseAuth = FirebaseAuth.getInstance()

        titleActTv.text = "Forum Discussion"

        backBtn.setOnClickListener {
            onBackPressed()
            finish()
        }


        forumRecyclerView = findViewById(R.id.forumRv)
        forumRecyclerView.layoutManager = LinearLayoutManager(this)
        forumRecyclerView.setHasFixedSize(true)

        forumArrayList = arrayListOf<Forum>()
        getForumData()

        val forumBottomSheet = ForumBottomSheet()

        forumRecyclerView.setOnClickListener {
            startActivity(Intent(this, DetailForumActivity::class.java))
        }

        addForumBtn.setOnClickListener {
            forumBottomSheet.show(supportFragmentManager, "fragmentDialog")
        }
    }

    private fun getForumData() {
        forumArrayList = ArrayList()
        val usrNm = firebaseAuth.currentUser!!.displayName
        dbref = FirebaseDatabase.getInstance().getReference("Forums")

        dbref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (forumSnapshot in snapshot.children) {
                        val forum = forumSnapshot.getValue(Forum::class.java)
                        forumArrayList.add(forum!!)
                    }
                    var adapter = ForumAdapter(forumArrayList)
                    forumRecyclerView.adapter = adapter

                    adapter.setOnItemClickListener(object : ForumAdapter.onItemClickListener{
                        override fun onItemClick(position: Int) {
                            val intent = Intent(this@ForumActivity, DetailForumActivity::class.java)
                            intent.putExtra("topic", forumArrayList[position].topicFr)
                            intent.putExtra("descr", forumArrayList[position].descFr)
                            intent.putExtra("uname",forumArrayList[position].unameFr)
                            intent.putExtra("date", forumArrayList[position].dateFr)
                            startActivity(intent)
                        }

                    })
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@ForumActivity, "Failed", Toast.LENGTH_SHORT).show()
            }

        })
    }
}