package com.udacity.shoestore.ui.home

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentHomeBinding
import com.udacity.shoestore.databinding.ListItemShoeBinding
import com.udacity.shoestore.ui.MainViewModel

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private val mainViewModel: MainViewModel by activityViewModels()

    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeViewModel = ViewModelProvider(
            this,
            HomeViewModelFactory()
        )[HomeViewModel::class.java]
        binding.fab.setOnClickListener {
            requireView().findNavController().navigate(R.id.action_homeFragment_to_addShoeFragment)
        }
        mainViewModel.shoesList.observe(viewLifecycleOwner) { shoesList ->
            shoesList?.forEach {
                val itemBinding = ListItemShoeBinding.inflate(layoutInflater)
                itemBinding.shoe = it
                itemBinding.executePendingBindings()
                binding.layoutShoes.addView(itemBinding.root)
            }
        }
        setupOptionMenu()
    }

    private fun setupOptionMenu() {
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.home_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.logout -> {
                        logout()
                        true
                    }

                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    private fun logout() {
        mainViewModel.logout()
        requireView().findNavController()
            .navigate(R.id.action_homeFragment_to_loginFragment)
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return
        with(sharedPref.edit()) {
            putBoolean(getString(R.string.login_key), false)
            apply()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}