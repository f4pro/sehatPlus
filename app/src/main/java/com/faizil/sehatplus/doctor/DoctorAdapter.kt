package com.faizil.sehatplus.doctor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.faizil.sehatplus.R

class DoctorAdapter(private val doctorRv: MutableList<Doctor>) :
    RecyclerView.Adapter<DoctorAdapter.MyViewHolder>() {

    private lateinit var mListener: onItemClickListener

    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener){
        mListener = listener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_row_doctor,
        parent, false)
        return MyViewHolder(itemView, mListener)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentitem = doctorRv[position]
        holder.nameDr.text = currentitem.nameDr
        holder.descDr.text = currentitem.descDr
    }

    override fun getItemCount(): Int {
        return doctorRv.size
    }
    class MyViewHolder(itemView: View, listener: onItemClickListener) : RecyclerView.ViewHolder(itemView){
        val nameDr : TextView =itemView.findViewById(R.id.nameTv)
        val descDr : TextView = itemView.findViewById(R.id.descriptionTv)

        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)

            }
        }

    }
}