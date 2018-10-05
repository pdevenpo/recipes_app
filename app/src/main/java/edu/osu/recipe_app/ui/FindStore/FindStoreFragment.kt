package edu.osu.recipe_app.ui.FindStore

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import edu.osu.recipe_app.R

class FindStoreFragment : Fragment() {

    companion object {
        fun newInstance() = FindStoreFragment()
    }

    private lateinit var viewModel: FindStoreViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.find_store_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(FindStoreViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
