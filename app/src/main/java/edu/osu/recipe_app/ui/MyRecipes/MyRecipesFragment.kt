package edu.osu.recipe_app.ui.MyRecipes

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import edu.osu.recipe_app.R
import edu.osu.recipe_app.ui.TodaysPick.MyRecipesViewModel

class MyRecipesFragment : Fragment() {

    private val s = "MyRecipesFragment"

    companion object {
        fun newInstance() = MyRecipesFragment()
    }

    private lateinit var viewModel: MyRecipesViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.my_recipes_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MyRecipesViewModel::class.java)

        Log.d(s, "Activity Created")

        // TODO: Use the ViewModel
    }

    override fun onPause() {
        super.onPause()
        Log.d(s, "Activity Paused")
    }

    override fun onResume() {
        super.onResume()
        Log.d(s, "Activity Resumed")
    }
}
