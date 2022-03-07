package ru.gb.cousrematerialdesign.utils

import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

// показ диалога SnackBar с передаваемыми параметрами
fun View.showSnackBar(
    fragment: Fragment,
    text: Int,
    actionText: Int,
    action: (View) -> Unit,
    length: Int = Snackbar.LENGTH_INDEFINITE
) {
    Snackbar.make(this, fragment.getString(text), length)
        .setAction(fragment.getString(actionText), action)
        .show()
}

// показ диалога Toast с передаваемыми параметрами
fun View.showToastMessageText(
    message: String,
    context: Context
) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}