package com.clover.harish.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.clover.harish.R
import com.clover.harish.adapter.CharacterAdapter
import com.clover.harish.adapter.ItemClickListener
import com.clover.harish.app.CloverApplication
import com.clover.harish.databinding.CharacterBinding
import com.clover.harish.models.CharacterVO
import com.clover.harish.models.viewmodels.AppViewModelFactory
import com.clover.harish.models.viewmodels.CharacterViewModel

class CharacterFragment : BaseFragment() , ItemClickListener<CharacterVO> {
    private lateinit var viewModel: CharacterViewModel
    private lateinit var binding: CharacterBinding
    private lateinit var adapter:CharacterAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewModelFactory = AppViewModelFactory(activity?.application as CloverApplication)
        viewModel = ViewModelProvider(this, viewModelFactory).get(CharacterViewModel::class.java)
        binding =
            DataBindingUtil.inflate(layoutInflater, R.layout.fragment_character, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter = CharacterAdapter(this)
        binding.characterList.adapter = adapter

        viewModel.charactersLiveData.observe(viewLifecycleOwner, {
            adapter.setResult(it.results)
        })

        viewModel.fetchCharacters()
    }

    override fun onItemClicked(characterVO: CharacterVO) {
        Toast.makeText(requireContext(),characterVO.name,Toast.LENGTH_SHORT).show()
    }
}