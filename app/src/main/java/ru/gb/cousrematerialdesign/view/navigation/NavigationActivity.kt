package ru.gb.cousrematerialdesign.view.navigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import ru.gb.cousrematerialdesign.R
import ru.gb.cousrematerialdesign.databinding.ActivityMainBinding
import ru.gb.cousrematerialdesign.databinding.ActivityNavigationBinding
import ru.gb.cousrematerialdesign.databinding.BottomSheetLayoutBinding
import ru.gb.cousrematerialdesign.view.main.PictureOfTheDayFragment


class NavigationActivity : AppCompatActivity(){

    private lateinit var binding: ActivityNavigationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNavigationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.viewPager.adapter = ViewPagerAdapter(supportFragmentManager)
    }
}