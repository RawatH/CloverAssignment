package com.clover.harish.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.clover.harish.R
import com.clover.harish.databinding.CharacterRowBinding
import com.clover.harish.models.CharacterVO

class CharacterPagedAdapter(val itemClickListener: ItemClickListener<CharacterVO>) :
    PagingDataAdapter<CharacterVO, CharacterPagedAdapter.CharactersViewHolder>(PassengersComparator) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: CharacterRowBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.character_row_layout, parent, false)
        return CharactersViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharactersViewHolder, position: Int) {
        val item = getItem(position)
        item?.let { holder.bind(it) }
    }

    inner class CharactersViewHolder(private val binding: CharacterRowBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: CharacterVO) {
           binding.characterVO = item
            binding.root.setOnClickListener{
                itemClickListener.onItemClicked(item)
            }
        }
    }

    object PassengersComparator : DiffUtil.ItemCallback<CharacterVO>() {
        override fun areItemsTheSame(oldItem: CharacterVO, newItem: CharacterVO): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CharacterVO, newItem: CharacterVO): Boolean {
            return oldItem == newItem
        }
    }
}