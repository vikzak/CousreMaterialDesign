package ru.gb.cousrematerialdesign.view.navigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import ru.gb.cousrematerialdesign.R
import ru.gb.cousrematerialdesign.databinding.ActivityBottomNavigationBinding
import ru.gb.cousrematerialdesign.databinding.ActivityMainBinding
import ru.gb.cousrematerialdesign.databinding.ActivityNavigationBinding
import ru.gb.cousrematerialdesign.databinding.BottomSheetLayoutBinding
import ru.gb.cousrematerialdesign.view.main.PictureOfTheDayFragment


class BottomNavigationActivity : AppCompatActivity(){

    private lateinit var binding: ActivityBottomNavigationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBottomNavigationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.bottomNavigationView.setOnItemSelectedListener{
            when(it.itemId){
                R.id.bottom_view_earth ->{true}
                R.id.bottom_view_mars ->{true}
                R.id.bottom_view_system ->{true}
                else -> false
            }
        }


    }
}