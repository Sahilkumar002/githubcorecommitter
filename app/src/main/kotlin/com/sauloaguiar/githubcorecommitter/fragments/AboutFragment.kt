package com.sauloaguiar.githubcorecommitter.fragments

import android.app.DialogFragment
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sauloaguiar.githubcorecommitter.R
import kotlinx.android.synthetic.main.fragment_dialog_about.*

/**
 * Created by sauloaguiar on 1/8/17.
 */
class AboutFragment : DialogFragment(){

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_dialog_about, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configureClicks()
        makeDialogTransparent()
    }

    private fun configureClicks() {
        github.setOnClickListener { view -> openProfile(getString(R.string.github_profile)) }
        linkedin.setOnClickListener { view -> openProfile(getString(R.string.linkedin_profile)) }
        twitter.setOnClickListener { view -> openProfile(getString(R.string.twitter_profile)) }
    }

    private fun openProfile(string: String?) {
        val profile = Intent(Intent.ACTION_VIEW, Uri.parse(string))
        profile.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(profile)
    }

    private fun makeDialogTransparent() {
        dialog.window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

}