package ru.gb.cousrematerialdesign.view.coordinator

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.appbar.AppBarLayout
import kotlin.math.abs

class BottomBehavior(context: Context, attributeSet: AttributeSet?=null) :
    CoordinatorLayout.Behavior<View>(context, attributeSet) {

    override fun layoutDependsOn(
        parent: CoordinatorLayout,
        child: View,
        dependency: View
    ) = dependency is AppBarLayout

    override fun onDependentViewChanged(
        parent: CoordinatorLayout,
        child: View,
        dependency: View
    ): Boolean {

        val appBarLayout = dependency as AppBarLayout
        val appBarHeight = appBarLayout.height.toFloat()
        val appBarY = appBarLayout.y
        if (abs(appBarY) > (appBarHeight * 2 / 3)) {
            child.visibility = View.GONE
        } else {
            child.visibility = View.VISIBLE
            child.alpha = ((appBarHeight * 2 / 3) - abs(appBarY)) / (appBarHeight * 2 / 3)

        }

        return super.onDependentViewChanged(parent, child, dependency)
    }
}

