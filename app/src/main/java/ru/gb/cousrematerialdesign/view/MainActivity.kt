package ru.gb.cousrematerialdesign.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import ru.gb.cousrematerialdesign.R
import ru.gb.cousrematerialdesign.databinding.ActivityMainBinding
import ru.gb.cousrematerialdesign.databinding.BottomSheetLayoutBinding
import ru.gb.cousrematerialdesign.view.main.PictureOfTheDayFragment

const val ThemeIndigo = 1
const val ThemeGreen = 2
const val ThemeOrange = 3

class MainActivity : AppCompatActivity(){

    private lateinit var binding: ActivityMainBinding
    private val KEY_SP = "sp"
    private val KEY_CURRENT_THEME = "current_theme"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setTheme(getRealStyle(getCurrentTheme()))

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null){
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, PictureOfTheDayFragment.newInstance())
                .commit()
        }
    }

    override fun onResume() {
        super.onResume()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container,PictureOfTheDayFragment.newInstance())
            .commit()
    }

    fun setCurrentTheme(currentTheme: Int){
        val sharedPreferences = getSharedPreferences(KEY_SP, MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putInt(KEY_CURRENT_THEME,currentTheme)
        editor.apply()
    }

    fun getCurrentTheme(): Int {
        val sharedPreferences = getSharedPreferences(KEY_SP, MODE_PRIVATE)
        return sharedPreferences.getInt(KEY_CURRENT_THEME,-1)
    }

    private fun getRealStyle(currentTheme: Int):Int{
        return when(currentTheme){
            ThemeGreen -> R.style.MyGreen
            ThemeIndigo -> R.style.MyIndigo
            ThemeOrange -> R.style.MyOrange
            else -> 0
        }
    }
//    private fun init() {
//
//    }
}