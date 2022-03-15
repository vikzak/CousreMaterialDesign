package ru.gb.cousrematerialdesign.view.navigation

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

private const val EARTH_KEY = 0
private const val MARS_KEY = 1
private const val SYSTEM_KEY = 2

class ViewPagerAdapter(
    private val fragmentManager: FragmentManager
) : FragmentPagerAdapter(fragmentManager) {

    private val fragments = arrayOf(EarthFragment(), MarsFragment(), SystemFragment())

    override fun getCount() = fragments.size


    override fun getItem(position: Int) = fragments[position]

}