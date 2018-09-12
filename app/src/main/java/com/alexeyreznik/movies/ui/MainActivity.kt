package com.alexeyreznik.movies.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.alexeyreznik.movies.R
import com.alexeyreznik.movies.ui.list.MoviesListFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.fragment_container, MoviesListFragment.newInstance())
        ft.commit()
    }
}
