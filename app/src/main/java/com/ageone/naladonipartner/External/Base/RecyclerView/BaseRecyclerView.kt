package com.ageone.naladonipartner.External.Base.RecyclerView

import android.content.Context
import android.content.Intent
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ageone.naladonipartner.Application.currentActivity

class BaseRecyclerView: RecyclerView(currentActivity as Context) {

}

open class BaseViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

}

abstract class BaseAdapter<T : BaseViewHolder>: RecyclerView.Adapter<T>(){
    abstract override fun getItemCount(): Int
    abstract override fun getItemViewType(position: Int): Int
    abstract override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): T
    abstract override fun onBindViewHolder(holder: T, position: Int)
}