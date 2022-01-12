package com.clover.harish.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.PagingData
import com.clover.harish.R
import com.clover.harish.adapter.CharacterAdapter
import com.clover.harish.adapter.CharacterPagedAdapter
import com.clover.harish.adapter.ItemClickListener
import com.clover.harish.app.CloverApplication
import com.clover.harish.databinding.CharacterBinding
import com.clover.harish.models.CharacterVO
import com.clover.harish.models.viewmodels.AppViewModelFactory
import com.clover.harish.models.viewmodels.CharacterViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class CharacterFragment : BaseFragment(), ItemClickListener<CharacterVO> {
    private lateinit var viewModel: CharacterViewModel
    private lateinit var binding: CharacterBinding
    private lateinit var adapter: CharacterPagedAdapter//CharacterAdapter

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

    override fun onStart() {
        super.onStart()
        init()
    }

    fun init() {
        adapter = CharacterPagedAdapter(this)
        //CharacterAdapter(this)


        binding.characterList.adapter = adapter
        viewModel.charactersLiveData.observe(viewLifecycleOwner, {
            Log.d("", "")
            it.results?.let {
                lifecycleScope.launch {
                    viewModel.characters.collectLatest { pagedData ->
                        val pagingData: PagingData<CharacterVO> = PagingData.from(it)
                        adapter.submitData(pagingData)
                    }
                }
            }
        })
//
//        viewModel.fetchCharacters()

        lifecycleScope.launch {
            viewModel.characters.collectLatest { pagedData ->
                adapter.submitData(pagedData)
            }
        }

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.findCharacterByName(newText)
                return true
            }

        })
    }

    override fun onItemClicked(characterVO: CharacterVO) {
        val bundle = Bundle()
        bundle.putString("locationUrl", characterVO.location.url)
        bundle.putString("avatarUrl", characterVO.image)
        findNavController().navigate(
            R.id.action_characterFragment_to_characterDetailsFragment,
            bundle
        )

    }
}