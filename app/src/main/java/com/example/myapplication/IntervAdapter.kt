package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.MainActivity

class IntervAdapter(val activity: MainActivity,var clickListner: OnIntervListener) : RecyclerView.Adapter<IntervAdapter.TachViewHolder>() {


    class TachViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val IntervNmr = v.findViewById<TextView>(com.example.myapplication.R.id.intervNmrView)
        val IntervDate = v.findViewById<TextView>(com.example.myapplication.R.id.intervDateView)
        val IntervType = v.findViewById<TextView>(com.example.myapplication.R.id.IntervTypeView)
        val NamePlo =v.findViewById<TextView>(com.example.myapplication.R.id.IntervNomView)
        val tacheLayout = v.findViewById<RelativeLayout>(com.example.myapplication.R.id.tacheLayoutView)
        fun initialize( item:Interv, action: OnIntervListener){
            IntervNmr.text = item.num
            IntervDate.text = item.date
            IntervType.text = item.Type
            NamePlo.text = item.nom
            tacheLayout.setOnClickListener {
                action.OnItemClick(item,adapterPosition)
            }

        }
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TachViewHolder {
        return TachViewHolder(
            LayoutInflater.from(activity).inflate(com.example.myapplication.R.layout.interv_view, parent, false
            ))
    }

    override fun getItemCount(): Int {
        return activity.intervList!!.size
    }

    override fun onBindViewHolder(holder: TachViewHolder, position: Int) {

        holder.initialize(activity.intervList!!.get(position),clickListner)



    }
    public interface OnIntervListener{
        fun OnItemClick(item:Interv,position: Int)
    }


}


