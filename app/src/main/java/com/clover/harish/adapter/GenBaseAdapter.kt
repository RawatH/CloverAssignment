package com.clover.harish.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView


abstract class GenBaseAdapter<T> : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    lateinit var dataList:List<T>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return getViewHolder(LayoutInflater.from(parent.context).inflate(viewType,parent,false),viewType)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as Binder<T>).bind(dataList.get(position))
    }

    fun setItems(listItems: List<T>) {
        this.dataList = listItems
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun getItemViewType(position: Int): Int {
        return getLayoutId(position, dataList[position])
    }

    abstract fun getLayoutId(position: Int,obj:T):Int
    abstract fun getViewHolder(view: View, viewType: Int):RecyclerView.ViewHolder

    internal interface Binder<T> {
        fun bind(data: T)
    }

}