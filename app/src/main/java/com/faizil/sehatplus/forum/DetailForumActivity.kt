package com.faizil.sehatplus.forum

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.faizil.sehatplus.R
import com.faizil.sehatplus.databinding.ActivityDetailForumBinding
import kotlinx.android.synthetic.main.activity_detail_forum.*
import org.w3c.dom.Text

class DetailForumActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_forum)

        val topicFr : TextView = findViewById(R.id.tvTitle)
        val descrFr : TextView = findViewById(R.id.tvDescription)
        val unameFr : TextView = findViewById(R.id.tvUsername)
        val dateFr : TextView = findViewById(R.id.tvTimestamp)

        val bundle : Bundle? = intent.extras
        val topic = bundle!!.getString("topic")
        val desc = bundle.getString("descr")
        val uname = bundle.getString("uname")
        val date = bundle.getString("date")

        topicFr.text = topic
        descrFr.text = desc
        unameFr.text = uname
        dateFr.text = date

        ivBackBtn.setOnClickListener {
            onBackPressed()
            finish()
        }

        val forumDetailCommentFragment = ForumDetailCommentFragment()

        addCommentBtn.setOnClickListener {
            forumDetailCommentFragment.show(supportFragmentManager,"fragmentDialog")
        }


    }
}