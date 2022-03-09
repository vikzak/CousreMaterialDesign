package ru.gb.cousrematerialdesign.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.gb.cousrematerialdesign.R
import ru.gb.cousrematerialdesign.view.main.PictureOfTheDayFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.MyIndigo)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null){
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, PictureOfTheDayFragment.newInstance())
                .commit()
        }
    }
}