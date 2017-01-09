package com.sauloaguiar.githubcorecommitter

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import kotlinx.android.synthetic.main.activity_commiters.*

class CommitterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_commiters)
        val toolbar = findViewById(R.id.toolbar) as Toolbar

        // update icon
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_18dp)

        toolbar.title = ""

        //Toast.makeText(applicationContext, extractProjectName(), Toast.LENGTH_SHORT).show()
        toolbar_title.text = extractProjectName()
        setSupportActionBar(toolbar)

        val ab = supportActionBar
        ab?.setDisplayShowHomeEnabled(true)
        ab?.setDisplayHomeAsUpEnabled(true)
    }

    private fun extractProjectName(): String {
        return intent.extras.getString("full_name").split("/")[1] ?: getString(R.string.title_activity_projects)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }


}
