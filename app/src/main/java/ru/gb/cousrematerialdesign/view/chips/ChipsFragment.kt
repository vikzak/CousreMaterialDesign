package ru.gb.cousrematerialdesign.view.chips

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.chip.Chip
import ru.gb.cousrematerialdesign.R
import ru.gb.cousrematerialdesign.databinding.FragmentChipsBinding
import ru.gb.cousrematerialdesign.databinding.FragmentMainBinding
import ru.gb.cousrematerialdesign.view.MainActivity
import ru.gb.cousrematerialdesign.view.ThemeGreen
import ru.gb.cousrematerialdesign.view.ThemeIndigo
import ru.gb.cousrematerialdesign.view.ThemeOrange
import ru.gb.cousrematerialdesign.viewmodel.PictureOfTheDayDataState
import ru.gb.cousrematerialdesign.viewmodel.PictureOfTheDayViewModel

class ChipsFragment : Fragment(), View.OnClickListener {
    private var _binding: FragmentChipsBinding? = null
    private val binding: FragmentChipsBinding
        get() = _binding!!

    companion object {
        fun newInstance() = ChipsFragment()
    }

    private lateinit var parrentActivity:MainActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        parrentActivity = (context as MainActivity)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChipsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.chipGroup.setOnCheckedChangeListener { group, checkedId ->
            binding.chipGroup.findViewById<Chip>(checkedId)?.let {
                Toast.makeText(requireContext(),"chip $checkedId ${it.text}",Toast.LENGTH_SHORT)
                    .show()
            }
        }

        binding.chipSelectIndigoTheme.setOnClickListener {
            parrentActivity.setCurrentTheme(ThemeIndigo)
            parrentActivity.recreate()
        }
        binding.chipSelectGreenTheme.setOnClickListener {
            parrentActivity.setCurrentTheme(ThemeGreen)
            parrentActivity.recreate()
        }
        binding.chipSelectOrangeTheme.setOnClickListener {
            parrentActivity.setCurrentTheme(ThemeOrange)
            parrentActivity.recreate()
        }

        binding.selectBnW.setOnClickListener {
            if (binding.selectBnW.isChecked == true){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
    }



    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}