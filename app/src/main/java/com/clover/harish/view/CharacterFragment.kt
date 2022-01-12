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
import com.clover.harish.adapter.CharacterPagedAdapter
import com.clover.harish.adapter.ItemClickListener
import com.clover.harish.app.CloverApplication
import com.clover.harish.databinding.CharacterBinding
import com.clover.harish.models.CharacterVO
import com.clover.harish.models.viewmodels.AppViewModelFactory
import com.clover.harish.models.viewmodels.CharacterViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class CharacterFragment : BaseFragment(), ItemClickListener<CharacterVO> {
    private lateinit var viewModel: CharacterViewModel
    private lateinit var binding: CharacterBinding
    private lateinit var adapter: CharacterPagedAdapter//CharacterAdapter

    lateinit var queryFlow:Flow<String>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewModelFactory = AppViewModelFactory(activity?.application as CloverApplication)
        viewModel = ViewModelProvider(this, viewModelFactory).get(CharacterViewModel::class.java)
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_character, container, false)
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
            if(it == null){
              Toast.makeText(requireContext(),"No response ",Toast.LENGTH_SHORT).show()
            }else {
                it.results?.let {
                    lifecycleScope.launch {
                        viewModel.characters.collectLatest { pagedData ->
                            val pagingData: PagingData<CharacterVO> = PagingData.from(it)
                            adapter.submitData(pagingData)
                        }
                    }
                }
            }
        })

//        viewModel.fetchCharacters()

        lifecycleScope.launch {
            viewModel.characters.collectLatest { pagedData ->
                adapter.submitData(pagedData)
            }
        }

//        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//            override fun onQueryTextSubmit(query: String?): Boolean {
//                return false
//            }
//
//            override fun onQueryTextChange(newText: String?): Boolean {
//                viewModel.findCharacterByName(newText)
//                return true
//            }
//
//        })

        viewLifecycleOwner.lifecycleScope.launch {
            binding.searchView
                .getQueryText()
                .debounce(300)
                .filter { query ->
                    if (query.isEmpty()) {
                        return@filter false
                    } else {
                        return@filter true
                    }
                }
                .distinctUntilChanged()

                .flowOn(Dispatchers.Default)
                .collect {
                    Log.d("QueryFlow","==>"+it)
                    viewModel.findCharacterByName(it)
                }
        }


    }

    //EXTENSION FUNCTION ON SEARCH VIEW
    fun SearchView.getQueryText(): StateFlow<String> {
        val queryFlow = MutableStateFlow("")

        setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                queryFlow.value = newText
                return true
            }
        })

        return queryFlow

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