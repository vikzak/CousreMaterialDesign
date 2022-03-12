package ru.gb.cousrematerialdesign.view.chips

import android.content.Context
import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.icu.util.TimeZone
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
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
import ru.gb.cousrematerialdesign.view.main.PictureOfTheDayFragment
import ru.gb.cousrematerialdesign.viewmodel.PictureOfTheDayDataState
import ru.gb.cousrematerialdesign.viewmodel.PictureOfTheDayViewModel
import java.util.*

class ChipsFragment : Fragment() {
    private var _binding: FragmentChipsBinding? = null
    private val binding: FragmentChipsBinding
        get() = _binding!!

    companion object {
        fun newInstance() = ChipsFragment()
    }
    private val viewModel: PictureOfTheDayViewModel by lazy {
        ViewModelProvider(this).get(PictureOfTheDayViewModel::class.java)
    }

    private lateinit var parrentActivity:MainActivity
    lateinit var context: AppCompatActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        parrentActivity = (context as MainActivity)
        this.context = context as AppCompatActivity
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
                when (checkedId){
//                    R.id.todayChip -> Toast.makeText(requireContext(),"today",Toast.LENGTH_SHORT).show()
//                    R.id.yesterdayChip -> Toast.makeText(requireContext(),"yesterday",Toast.LENGTH_SHORT).show()
//                    R.id.beforeYesterdayChip -> Toast.makeText(requireContext(),"beforeYesterday",Toast.LENGTH_SHORT).show()
                    R.id.todayChip -> {
                        viewModel.sendServerRequest(takeDate(0))
                        context.supportFragmentManager
                            .beginTransaction()
                            .replace(R.id.container,PictureOfTheDayFragment.newInstance())
                            .commit()
                    }
                    R.id.yesterdayChip -> {
                        context.supportFragmentManager
                            .beginTransaction()
                            .replace(R.id.container,PictureOfTheDayFragment.newInstance())
                            .commit()
                        viewModel.sendServerRequest(takeDate(-1))
                    }
                    R.id.beforeYesterdayChip -> {
                        viewModel.sendServerRequest(takeDate(-2))
                        context.supportFragmentManager
                            .beginTransaction()
                            .replace(R.id.container,PictureOfTheDayFragment.newInstance())
                            .commit()
                    }

                }
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


    private fun takeDate(count: Int): String {
        val currentDate = Calendar.getInstance()
        currentDate.add(Calendar.DAY_OF_MONTH, count)
        val format1 = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        format1.timeZone = TimeZone.getTimeZone("EST",0)
        return format1.format(currentDate.time)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}