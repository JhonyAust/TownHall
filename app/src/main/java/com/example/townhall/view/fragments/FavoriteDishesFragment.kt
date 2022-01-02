package com.example.townhall.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.townhall.R
import com.example.townhall.application.TownHallApplication
import com.example.townhall.databinding.FragmentFavoriteDishesBinding
import com.example.townhall.model.entities.TownHall
import com.example.townhall.view.activities.MainActivity
import com.example.townhall.view.adapters.TownHallAdapter
import com.example.townhall.viewmodel.DashboardViewModel
import com.example.townhall.viewmodel.TownHallViewModel
import com.example.townhall.viewmodel.TownHallViewModelFactory

class FavoriteDishesFragment : Fragment() {

    private var mBinding: FragmentFavoriteDishesBinding? = null

    private val mTownHallViewModel: TownHallViewModel by viewModels {
        TownHallViewModelFactory(((requireActivity().application)as TownHallApplication).repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // TODO Step: Initialize the mBinding.
        // START
        mBinding = FragmentFavoriteDishesBinding.inflate(inflater, container, false)
        return mBinding!!.root
        // END
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /**
         * Add an observer on the LiveData returned by getFavoriteDishesList.
         * The onChanged() method fires when the observed data changes and the activity is in the foreground.
         */
        mTownHallViewModel.favoriteDishes.observe(viewLifecycleOwner) { dishes ->
            dishes.let {

                // TODO Step: Remove the Logs and display the list of Favorite Dishes using RecyclerView. Here we will not create a separate adapter class we cas use the same that we have created for AllDishes.
                // START

                // Set the LayoutManager that this RecyclerView will use.
                mBinding!!.rvFavoriteDishesList.layoutManager =
                    GridLayoutManager(requireActivity(), 2)
                // Adapter class is initialized and list is passed in the param.
                val adapter = TownHallAdapter(this@FavoriteDishesFragment)
                // adapter instance is set to the recyclerview to inflate the items.
                mBinding!!.rvFavoriteDishesList.adapter = adapter

                if (it.isNotEmpty()) {
                    mBinding!!.rvFavoriteDishesList.visibility = View.VISIBLE
                    mBinding!!.tvNoFavoriteDishesAvailable.visibility = View.GONE

                    adapter.dishesList(it)
                } else {
                    mBinding!!.rvFavoriteDishesList.visibility = View.GONE
                    mBinding!!.tvNoFavoriteDishesAvailable.visibility = View.VISIBLE
                }
                // END
            }
        }
    }

    fun dishDetails(townHall: TownHall){
        findNavController().navigate(FavoriteDishesFragmentDirections.actionNavigationFavoriteDishesToNavigationDishDetails(townHall))

        if (requireActivity() is MainActivity){
            (activity as MainActivity?)!!.hideBottomNavigationView()
        }
    }

    // TODO Step: Override the onDestroy method and make the mBinding null where the method is executed.
    // START
    override fun onDestroy() {
        super.onDestroy()
        mBinding = null
    }

    override fun onResume() {
        super.onResume()
        if (requireActivity() is MainActivity){
            (activity as MainActivity?)!!.showBottomNavigationView()
        }
    }
}