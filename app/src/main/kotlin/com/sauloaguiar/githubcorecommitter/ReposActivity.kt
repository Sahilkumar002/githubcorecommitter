package com.sauloaguiar.githubcorecommitter

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import com.sauloaguiar.githubcorecommitter.fragments.AboutFragment
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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_repos, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId


        if (id == R.id.menu_about) {
            showDialog()
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    private fun showDialog() {
        AboutFragment().show(fragmentManager, "my string")
    }
}
