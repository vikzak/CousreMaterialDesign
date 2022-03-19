package ru.gb.cousrematerialdesign.view.navigation

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import coil.load
import ru.gb.cousrematerialdesign.R
import ru.gb.cousrematerialdesign.databinding.FragmentEarthBinding
import ru.gb.cousrematerialdesign.databinding.FragmentMainBinding
import ru.gb.cousrematerialdesign.utils.showSnackBar
import ru.gb.cousrematerialdesign.utils.showToastMessageText
import ru.gb.cousrematerialdesign.viewmodel.PictureOfTheDayDataState
import ru.gb.cousrematerialdesign.viewmodel.PictureOfTheDayViewModel

class EarthFragment : Fragment() {

    private var _binding: FragmentEarthBinding? = null
    private val binding: FragmentEarthBinding
        get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEarthBinding.inflate(inflater, container, false)
        return binding.root
    }


    private val viewModel: PictureOfTheDayViewModel by lazy {
        ViewModelProvider(this).get(PictureOfTheDayViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getLiveData().observe(viewLifecycleOwner, Observer { renderData(it) })
        viewModel.sendServerRequest()
    }

    private fun renderData(pictureOfTheDayDataState: PictureOfTheDayDataState) {
        when (pictureOfTheDayDataState) {
            is PictureOfTheDayDataState.Error -> {//функция описана в utisl->AppDialog
                binding.main.showSnackBar(
                    fragment = this,
                    text = R.string.error,
                    actionText = R.string.reload,
                    { viewModel.sendServerRequest() }
                )
            }
            is PictureOfTheDayDataState.Loading -> {//функция описана в utisl->AppDialog
                binding.main.showToastMessageText("LOADING...",requireContext())
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

    companion object {
        @JvmStatic
        fun newInstance() = EarthFragment()
    }

}