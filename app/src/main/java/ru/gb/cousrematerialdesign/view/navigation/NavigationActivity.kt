package ru.gb.cousrematerialdesign.view.navigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
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

        binding.viewPager.adapter = ViewPagerAdapter(this)
        // binding.tableLayot.setupWithViewPager(binding.viewPager) = это от обычного ViewPager с ДЗ№3
        TabLayoutMediator(binding.tableLayot,binding.viewPager, object : TabLayoutMediator.TabConfigurationStrategy{
            override fun onConfigureTab(tab: TabLayout.Tab, position: Int) {
                tab.text = when(position){
                    EARTH_KEY -> "Earth"
                    MARS_KEY -> "Mars"
                    SYSTEM_KEY -> "System"
                    else -> "Earth"
                }
            }
        }).attach()

//        binding.tableLayot.getTabAt(EARTH_KEY)?.setIcon(R.drawable.ic_earth)
//        binding.tableLayot.getTabAt(MARS_KEY)?.setIcon(R.drawable.ic_mars)
//        binding.tableLayot.getTabAt(SYSTEM_KEY)?.setIcon(R.drawable.ic_system)
// с ДЗ№3 viewPager
//        binding.tableLayot.getTabAt(EARTH_KEY)?.setCustomView(R.layout.activity_navigation_custom_tab_earth)
//        binding.tableLayot.getTabAt(MARS_KEY)?.setCustomView(R.layout.activity_navigation_custom_tab_mars)
//        binding.tableLayot.getTabAt(SYSTEM_KEY)?.setCustomView(R.layout.activity_navigation_custom_tab_system)
    }
}