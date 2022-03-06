package ru.gb.cousrematerialdesign.view.main

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.google.android.material.bottomsheet.BottomSheetBehavior
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


    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>//прописываем в соответствии типом layout
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getLiveData().observe(viewLifecycleOwner, Observer { renderData(it) })
        viewModel.sendServerRequest()
        setEndIconOnClickListener() // вынес отдельно
        bottomSheetBehavior = BottomSheetBehavior.from(binding.includedBSL.bottomSheetContainer)
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_HALF_EXPANDED
        bottomSheetBehavior.addBottomSheetCallback(object: BottomSheetBehavior.BottomSheetCallback(){
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when(newState){
//                    BottomSheetBehavior.STATE_COLLAPSED -> {
//                        TODO()
//                    }
//                    BottomSheetBehavior.STATE_DRAGGING -> {
//                        TODO()
//                    }
//                    BottomSheetBehavior.STATE_EXPANDED -> {
//                        TODO()
//                    }
//                    BottomSheetBehavior.STATE_HALF_EXPANDED -> {
//                        TODO()
//                    }
//                    BottomSheetBehavior.STATE_HIDDEN -> {
//                        TODO()
//                    }
//                    BottomSheetBehavior.STATE_SETTLING -> {
//                        TODO()
//                    }
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                Log.d("logCMD","slideOffset: $slideOffset")
            }

        })


    }

    private fun setEndIconOnClickListener() {
        binding.inputLayout.setEndIconOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse("https://ru.wikipedia.org/wiki/${binding.inputEditText.text.toString()}")
            })
        }
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
                binding.includedBSL.bottomSheetDescriptionHeader.text = pictureOfTheDayDataState.serverResponseData.title
                binding.includedBSL.bottomSheetDescription.text = pictureOfTheDayDataState.serverResponseData.explanation
                //HW
                //pictureOfTheDayDataState.serverResponseData.title
                //pictureOfTheDayDataState.serverResponseData.explanation

            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}