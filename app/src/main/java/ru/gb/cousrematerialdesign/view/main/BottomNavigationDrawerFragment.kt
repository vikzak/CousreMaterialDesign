package ru.gb.cousrematerialdesign.view.main

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import ru.gb.cousrematerialdesign.R
import ru.gb.cousrematerialdesign.databinding.BottomNavigationLayoutBinding
import ru.gb.cousrematerialdesign.databinding.FragmentMainBinding
import ru.gb.cousrematerialdesign.view.constaint.LayoutActivity
import ru.gb.cousrematerialdesign.view.navigation.BottomNavigationActivity
import ru.gb.cousrematerialdesign.view.navigation.NavigationActivity

class BottomNavigationDrawerFragment : BottomSheetDialogFragment() {
    private var _binding: BottomNavigationLayoutBinding? = null
    private val binding: BottomNavigationLayoutBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = BottomNavigationLayoutBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.navigationView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.navigation_one ->{
                    startActivity(Intent(requireContext(),NavigationActivity::class.java))
                }
                R.id.navigation_two ->{
                    //startActivity(Intent(requireContext(),NavigationActivity::class.java))
                    startActivity(Intent(requireContext(),BottomNavigationActivity::class.java))
                }
                R.id.navigation_three ->{
                    startActivity(Intent(requireContext(),LayoutActivity::class.java))
                }
            }
            true
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}