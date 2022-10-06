package com.finalproject.cinephile.ui.newRelease

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import com.finalproject.cinephile.R
import com.finalproject.cinephile.ui.MainActivity
import com.finalproject.cinephile.ui.viewmodel.SearchViewModel.Companion.SEARCH_QUERY_KEY
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewReleaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        val toolbar = findViewById<Toolbar>(R.id.toolbar_search)
        setSupportActionBar(toolbar)
        if (supportActionBar != null) {
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
            supportActionBar!!.setDisplayShowTitleEnabled(false)
        }
        val title = getString(R.string.new_released_movies)
        toolbar.title = title
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, NewReleaseFragment()).commit()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        finish()
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.options_menu, menu)
        super.onCreateOptionsMenu(menu)
        return true
    }
}