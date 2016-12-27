package com.sauloaguiar.githubcorecommitter

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import kotlinx.android.synthetic.main.activity_repos.*

class ReposActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repos)
        val toolbar = findViewById(R.id.toolbar) as Toolbar

        toolbar.title = ""

        toolbar_title.setText(R.string.repos_activity_title)
        setSupportActionBar(toolbar)
    }
}
