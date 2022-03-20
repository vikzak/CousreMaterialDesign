package ru.gb.cousrematerialdesign.view.constaint

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import ru.gb.cousrematerialdesign.R
import ru.gb.cousrematerialdesign.databinding.*
import ru.gb.cousrematerialdesign.view.main.PictureOfTheDayFragment
import ru.gb.cousrematerialdesign.viewmodel.PictureOfTheDayViewModel


class  LayoutActivity : AppCompatActivity(){


    private lateinit var binding: ActivityLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initBottomNavigationView()


    }

    private fun initBottomNavigationView() {
//        val badgeEarth = binding.bottomNavigationView.getOrCreateBadge(R.id.bottom_view_earth)
//        val badgeMars = binding.bottomNavigationView.getOrCreateBadge(R.id.bottom_view_mars)
//        val badgeSystem = binding.bottomNavigationView.getOrCreateBadge(R.id.bottom_view_system)
        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.bottom_constraint -> {
                    navigationTo(ConstraintFragment())
                    true
                }
                R.id.bottom_constraint -> {
                    true
                }
                R.id.bottom_motion -> {
                    true
                }
                else -> false
            }
        }
        binding.bottomNavigationView.selectedItemId = R.id.bottom_constraint
    }

    private fun navigationTo(f: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, f)
            .commit()
    }
}