package ru.gb.cousrematerialdesign.view.main

import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.icu.util.TimeZone
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
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
import ru.gb.cousrematerialdesign.databinding.FragmentMainBinding
import ru.gb.cousrematerialdesign.utils.showSnackBar
import ru.gb.cousrematerialdesign.utils.showToastMessageText
import ru.gb.cousrematerialdesign.view.MainActivity
import ru.gb.cousrematerialdesign.view.chips.ChipsFragment
import ru.gb.cousrematerialdesign.viewmodel.PictureOfTheDayDataState
import ru.gb.cousrematerialdesign.viewmodel.PictureOfTheDayViewModel
import java.util.*

class PictureOfTheDayFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val binding: FragmentMainBinding
        get() = _binding!!

    companion object {
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
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }


    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>//прописываем в соответствии типом layout
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        (requireActivity() as MainActivity).setSupportActionBar(binding.bottomAppBar)
        viewModel.getLiveData().observe(viewLifecycleOwner, Observer { renderData(it) })
        viewModel.sendServerRequest()

        showBottomMenu() //добавляем меню

        viewModel.getLiveData().observe(viewLifecycleOwner, Observer { renderData(it) })
        setEndIconOnClickListener() // вынес отдельно
        viewModel.sendServerRequest()

        bottomSheetBehavior = BottomSheetBehavior.from(binding.includedBSL.bottomSheetContainer)
        bottomSheetBehavior.addBottomSheetCallback(object :
            BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when (newState) {
//                    BottomSheetBehavior.STATE_COLLAPSED -> { }
//                    BottomSheetBehavior.STATE_DRAGGING -> { }
//                    BottomSheetBehavior.STATE_EXPANDED -> { }
//                    BottomSheetBehavior.STATE_HALF_EXPANDED -> { }
//                    BottomSheetBehavior.STATE_HIDDEN -> { }
//                    BottomSheetBehavior.STATE_SETTLING -> { }
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                Log.d("logCMD", "slideOffset: $slideOffset")
            }
        })
        var isMain:Boolean = true
        binding.fab.setOnClickListener {
            if (isMain){
                binding.bottomAppBar.navigationIcon = null
                binding.bottomAppBar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_END
                binding.fab.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_back_fab))
                binding.bottomAppBar.replaceMenu(R.menu.menu_bottom_bar_other_screen)
            } else{
                binding.bottomAppBar.navigationIcon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_hamburger_menu_bottom_bar)
                binding.bottomAppBar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_CENTER
                binding.fab.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_plus_fab))
                binding.bottomAppBar.replaceMenu(R.menu.menu_bottom_bar)
            }
            isMain = !isMain
        }

        binding.chipGroupPicture.setOnCheckedChangeListener { group, checkedId ->
            binding.chipGroupPicture.findViewById<Chip>(checkedId)?.let {
                when (checkedId){
                    R.id.todayChipPicture -> {
                        viewModel.sendServerRequest(takeDate(0))
                    }
                    R.id.yesterdayChipPicture -> {
                        viewModel.sendServerRequest(takeDate(-1))
                    }
                    R.id.beforeYesterdayChipPicture -> {
                        viewModel.sendServerRequest(takeDate(-2))
                    }
                }
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



    private fun showBottomMenu() {
        setHasOptionsMenu(true)
        (requireActivity() as MainActivity).setSupportActionBar(binding.bottomAppBar)
    }

    private fun setEndIconOnClickListener() {
        binding.inputLayout.setEndIconOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW).apply {
                data =
                    Uri.parse("https://ru.wikipedia.org/wiki/${binding.inputEditText.text.toString()}")
            })
        }
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
                binding.includedBSL.bottomSheetDescriptionHeader.text =
                    pictureOfTheDayDataState.serverResponseData.title
                binding.includedBSL.bottomSheetDescription.text =
                    pictureOfTheDayDataState.serverResponseData.explanation
            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_bottom_bar, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.app_bar_fav -> {
                showDescriptionPictureOfDayResum() // если скрыли описание картинки дня свайпом,
                // покажем его еще раз
                //binding.main.showToastMessageText("app_bar_fav",requireContext())
            }
            R.id.app_bar_setting -> {
                requireActivity()
                    .supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.container,ChipsFragment.newInstance())
                    .addToBackStack("")
                    .commit()
                //binding.main.showToastMessageText("app_bar_setting",requireContext())
            }
            android.R.id.home -> {
                BottomNavigationDrawerFragment().show(requireActivity().supportFragmentManager, "")
                //binding.main.showToastMessageText("home",requireContext())
            }
        }
        return super.onOptionsItemSelected(item)
    }


    fun showDescriptionPictureOfDayResum(){
        bottomSheetBehavior = BottomSheetBehavior.from(binding.includedBSL.bottomSheetContainer)
        if (bottomSheetBehavior.state == BottomSheetBehavior.STATE_HIDDEN){
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}