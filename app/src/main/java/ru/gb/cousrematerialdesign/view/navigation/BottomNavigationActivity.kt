package ru.gb.cousrematerialdesign.view.navigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import ru.gb.cousrematerialdesign.R
import ru.gb.cousrematerialdesign.databinding.ActivityBottomNavigationBinding
import ru.gb.cousrematerialdesign.databinding.ActivityMainBinding
import ru.gb.cousrematerialdesign.databinding.ActivityNavigationBinding
import ru.gb.cousrematerialdesign.databinding.BottomSheetLayoutBinding
import ru.gb.cousrematerialdesign.view.main.PictureOfTheDayFragment


class  BottomNavigationActivity : AppCompatActivity(){

    private lateinit var binding: ActivityBottomNavigationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBottomNavigationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initBottomNavigationView()


    }

    private fun initBottomNavigationView() {
        val badgeEarth = binding.bottomNavigationView.getOrCreateBadge(R.id.bottom_view_earth)
        val badgeMars = binding.bottomNavigationView.getOrCreateBadge(R.id.bottom_view_mars)
        val badgeSystem = binding.bottomNavigationView.getOrCreateBadge(R.id.bottom_view_system)
        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.bottom_view_earth -> {
                    binding.bottomNavigationView.removeBadge(R.id.bottom_view_earth)
                    navigationTo(EarthFragment())
                    true
                }
                R.id.bottom_view_mars -> {
                    binding.bottomNavigationView.removeBadge(R.id.bottom_view_mars)
                    navigationTo(MarsFragment())
                    true
                }
                R.id.bottom_view_system -> {
                    binding.bottomNavigationView.removeBadge(R.id.bottom_view_system)
                    navigationTo(SystemFragment())
                    true
                }
                else -> false
            }
        }
        binding.bottomNavigationView.selectedItemId = R.id.bottom_view_earth
        badgeEarth.number = 10
        badgeMars.number = 11
        badgeSystem.number = 77
    }

    private fun navigationTo(f: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, f)
            .commit()
    }
}