package com.clover.harish.adapter

import com.clover.harish.models.CharacterVO
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.clover.harish.R
import com.clover.harish.databinding.CharacterRowBinding

class CharacterAdapter(val itemClickListener: ItemClickListener<CharacterVO>) :
    RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {

    val dataList: MutableList<CharacterVO> = mutableListOf()

    fun setResult(newDataList:List<CharacterVO>){
        dataList.addAll(newDataList)
        notifyDataSetChanged()
    }

    class CharacterViewHolder(val binding: CharacterRowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var characterVO: CharacterVO? = null

        fun bind(characterVO: CharacterVO,itemClickListener: ItemClickListener<CharacterVO>) {
            this.characterVO = characterVO
            binding.characterVO = characterVO
            binding.root.setOnClickListener{
                itemClickListener.onItemClicked(characterVO)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: CharacterRowBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.character_row_layout, parent, false)
        return CharacterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind(dataList.get(position),itemClickListener)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}



interface ItemClickListener<T> {
    fun onItemClicked(t: T)
}