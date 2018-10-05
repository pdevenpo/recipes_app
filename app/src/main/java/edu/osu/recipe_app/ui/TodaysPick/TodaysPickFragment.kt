package edu.osu.recipe_app.ui.TodaysPick

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import edu.osu.recipe_app.R

class TodaysPickFragment : Fragment() {

    private val s = "TodayPick"

    companion object {
        fun newInstance() = TodaysPickFragment()
    }

    private lateinit var viewModel: TodaysPickViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        Log.d(s, "Fragment View Created")

        return inflater.inflate(R.layout.todays_pick_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(TodaysPickViewModel::class.java)
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
