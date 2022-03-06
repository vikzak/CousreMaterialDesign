package ru.gb.cousrematerialdesign.view.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import coil.load
import ru.gb.cousrematerialdesign.databinding.FragmentMainBinding
import ru.gb.cousrematerialdesign.viewmodel.PictureOfTheDayDataState
import ru.gb.cousrematerialdesign.viewmodel.PictureOfTheDayViewModel

class PictureOfTheDayFragment :Fragment() {
    private var _binding: FragmentMainBinding?=null
    private val binding:FragmentMainBinding
    get() = _binding!!

    companion object{
        fun newInstance() = PictureOfTheDayFragment()
    }

    private val viewModel: PictureOfTheDayViewModel by lazy {
        ViewModelProvider(this).get(PictureOfTheDayViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getLiveData().observe(viewLifecycleOwner, Observer { renderData(it) })
        viewModel.sendServerRequest()
    }

    fun renderData(pictureOfTheDayDataState: PictureOfTheDayDataState){
        when(pictureOfTheDayDataState){
            is PictureOfTheDayDataState.Error -> {
                //HW
            }
            is PictureOfTheDayDataState.Loading -> {
                //HW
            }
            is PictureOfTheDayDataState.Success -> {
                binding.imageView.load(pictureOfTheDayDataState.serverResponseData.hdurl)
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}