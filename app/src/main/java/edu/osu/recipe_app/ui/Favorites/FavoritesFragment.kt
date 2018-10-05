package edu.osu.recipe_app.ui.Favorites

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import edu.osu.recipe_app.R

class FavoritesFragment : Fragment() {

    private val s = "Favorites"

    companion object {
        fun newInstance() = FavoritesFragment()
    }

    private lateinit var viewModel: FavoritesViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        Log.d(s, "Fragment View Created")
        return inflater.inflate(R.layout.favorites_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(FavoritesViewModel::class.java)

        Log.d(s, "Activity Created")

        // TODO: Use the ViewModel
    }

    override fun onStart() {
        super.onStart()
        Log.d(s, "Activity Started")
    }

    override fun onResume() {
        super.onResume()
        Log.d(s, "Activity Resumed")
    }

    override fun onPause() {
        super.onPause()
        Log.d(s, "Activity Paused")
    }

    override fun onStop() {
        super.onStop()
        Log.d(s, "Activity Stopped")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(s, "Activity Destroyed")
    }
}
