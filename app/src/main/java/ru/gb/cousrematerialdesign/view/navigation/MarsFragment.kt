package ru.gb.cousrematerialdesign.view.navigation

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
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.chip.Chip
import com.google.android.material.snackbar.Snackbar
import ru.gb.cousrematerialdesign.BuildConfig
import ru.gb.cousrematerialdesign.R
import ru.gb.cousrematerialdesign.databinding.FragmentMainBinding
import ru.gb.cousrematerialdesign.databinding.FragmentMarsBinding
import ru.gb.cousrematerialdesign.remoterepository.MarsServerResponseData
import ru.gb.cousrematerialdesign.utils.showSnackBar
import ru.gb.cousrematerialdesign.utils.showToastMessageText
import ru.gb.cousrematerialdesign.view.MainActivity
import ru.gb.cousrematerialdesign.view.chips.ChipsFragment
import ru.gb.cousrematerialdesign.viewmodel.PictureOfTheDayDataState
import ru.gb.cousrematerialdesign.viewmodel.PictureOfTheDayViewModel
import java.util.*

class MarsFragment : Fragment() {

    private var _binding: FragmentMarsBinding? = null
    private val binding: FragmentMarsBinding
        get() = _binding!!

    lateinit var oneBigFatViewModel: PictureOfTheDayViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMarsBinding.inflate(inflater, container, false)
        return binding.root
    }


    private val viewModel: PictureOfTheDayViewModel by lazy {
        ViewModelProvider(this).get(PictureOfTheDayViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getLiveData().observe(viewLifecycleOwner, Observer { renderData(it) })
        //viewModel.sendServerRequest()
        //oneBigFatViewModel.getMarsPicture()

    }

    private fun setData(data: PictureOfTheDayDataState.Success)  {
        val url = data.serverResponseData.hdurl
        if (url.isNullOrEmpty()) {
            val videoUrl = data.serverResponseData.hdurl
            videoUrl?.let { showAVideoUrl(it) }
        } else {
            binding.imageView.load(url)
        }
    }

    private fun renderData(appState: PictureOfTheDayDataState) {
        when(appState){
            is PictureOfTheDayDataState.Error ->
                Snackbar.make(binding.root, appState.error.toString(), Snackbar.LENGTH_SHORT).show()
//            is PictureOfTheDayDataState.Loading -> {
//                binding.imageView.load(R.drawable.progress_animation)
//            }
//            is PictureOfTheDayDataState.Success -> {
//                setData(appState)
//            }
            is PictureOfTheDayDataState.SuccessMars -> {
                if(appState.serverResponseData.photos.isEmpty()){
                    Snackbar.make(binding.root, "В этот день curiosity не сделал ни одного снимка", Snackbar.LENGTH_SHORT).show()
                }else{
                    val url = appState.serverResponseData.photos.first().imgSrc
                    binding.imageView.load(url)
                }

            }
        }
    }

    private fun showAVideoUrl(videoUrl: String) = with(binding) {
        imageView.visibility = View.GONE
        videoOfTheDay.visibility = View.VISIBLE
        videoOfTheDay.text = "Сегодня у нас без картинки дня, но есть  видео дня! " +
                "${videoUrl.toString()} \n кликни >ЗДЕСЬ< чтобы открыть в новом окне"
        videoOfTheDay.setOnClickListener {
            val i = Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse(videoUrl)
            }
            startActivity(i)
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() = MarsFragment()

    }


}