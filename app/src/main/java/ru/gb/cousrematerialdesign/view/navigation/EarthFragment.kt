package ru.gb.cousrematerialdesign.view.navigation

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import ru.gb.cousrematerialdesign.databinding.FragmentEarthBinding
import ru.gb.cousrematerialdesign.databinding.FragmentMainBinding

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


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() = EarthFragment()
    }

}