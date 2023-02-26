package com.github.qsubq.rampetproject.app.presentation.utils

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class MyDialogFragment(private val messageText: String) : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setTitle("Error")
            builder.setPositiveButton("Текст поз кнопки"){
                    dialog, _ -> dialog.cancel()
            }
            builder.setMessage(messageText)
            builder.create()
        }?: throw IllegalStateException("Activity cannot be null")


    }
}