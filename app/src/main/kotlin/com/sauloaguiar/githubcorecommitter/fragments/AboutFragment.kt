package com.sauloaguiar.githubcorecommitter.fragments

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sauloaguiar.githubcorecommitter.R

/**
 * Created by sauloaguiar on 1/8/17.
 */
class AboutFragment : DialogFragment(){

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        makeDialogTransparent()
        return inflater?.inflate(R.layout.fragment_dialog_about, container, false)
    }

    private fun makeDialogTransparent() {
        dialog.window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }
}