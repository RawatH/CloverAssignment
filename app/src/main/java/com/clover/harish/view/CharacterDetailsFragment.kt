package com.clover.harish.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.clover.harish.R
import com.clover.harish.app.CloverApplication
import com.clover.harish.databinding.LocationBinding
import com.clover.harish.models.viewmodels.AppViewModelFactory
import com.clover.harish.models.viewmodels.CharacterDetailViewModel

class CharacterDetailsFragment : BaseFragment() {
    private lateinit var viewModel: CharacterDetailViewModel
    private lateinit var binding: LocationBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewModelFactory = AppViewModelFactory(activity?.application as CloverApplication)
        viewModel =
            ViewModelProvider(this, viewModelFactory).get(CharacterDetailViewModel::class.java)
        binding =
            DataBindingUtil.inflate(
                layoutInflater,
                R.layout.location_detail_layout,
                container,
                false
            )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewModel.locationDetailLiveData.observe(viewLifecycleOwner, {
            binding.locationDetailVO = it
        })

        arguments?.getString("avatarUrl")?.let {
            binding.charaterImgUrl = it
        }

        arguments?.getString("locationUrl")?.let {
            viewModel.fetchLocationDetail(it)
        }

    }

}