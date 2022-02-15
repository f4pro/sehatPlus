package com.faizil.sehatplus.forum

import android.view.LayoutInflater
import android.view.OnReceiveContentListener
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.faizil.sehatplus.R

class ForumAdapter(private val forumRv: ArrayList<Forum>) :
    RecyclerView.Adapter<ForumAdapter.MyViewHolder>() {

    private lateinit var mListener: onItemClickListener

    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener){
        mListener = listener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_bottom_forum,
        parent, false)
        return MyViewHolder(itemView, mListener)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentitem = forumRv[position]
        holder.topicFr.text = currentitem.topicFr
        holder.descFr.text = currentitem.descFr
        holder.unameFr.text = currentitem.unameFr
        holder.dateFr.text = currentitem.dateFr
    }

    override fun getItemCount(): Int {
        return forumRv.size
    }
    class MyViewHolder(itemView: View, listener: onItemClickListener) : RecyclerView.ViewHolder(itemView){
        val topicFr : TextView =itemView.findViewById(R.id.titleTv)
        val descFr : TextView = itemView.findViewById(R.id.contentTv)
        val unameFr : TextView = itemView.findViewById(R.id.userTv)
        val dateFr : TextView = itemView.findViewById(R.id.dateTv)

        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)

            }
        }

    }
}