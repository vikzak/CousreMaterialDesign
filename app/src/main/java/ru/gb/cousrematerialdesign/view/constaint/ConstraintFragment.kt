package ru.gb.cousrematerialdesign.view.constaint

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import coil.load
import ru.gb.cousrematerialdesign.R
import ru.gb.cousrematerialdesign.databinding.FragmentChipsBinding
import ru.gb.cousrematerialdesign.databinding.FragmentConstraintBinding
import ru.gb.cousrematerialdesign.databinding.FragmentEarthBinding
import ru.gb.cousrematerialdesign.databinding.FragmentMainBinding
import ru.gb.cousrematerialdesign.utils.showSnackBar
import ru.gb.cousrematerialdesign.utils.showToastMessageText
import ru.gb.cousrematerialdesign.viewmodel.PictureOfTheDayDataState
import ru.gb.cousrematerialdesign.viewmodel.PictureOfTheDayViewModel

class ConstraintFragment : Fragment() {

    private var _binding: FragmentConstraintBinding? = null
    private val binding: FragmentConstraintBinding
        get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentConstraintBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() = ConstraintFragment()
    }

}