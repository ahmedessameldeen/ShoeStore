package com.udacity.shoestore.ui.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentOnboardingBinding

class OnBoardingFragment : Fragment() {

    private lateinit var onBoardingViewModel: OnBoardingViewModel
    private var _binding: FragmentOnboardingBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentOnboardingBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onBoardingViewModel = ViewModelProvider(
            this,
            OnBoardingViewModelFactory()
        )[OnBoardingViewModel::class.java]


        binding.btnStart.setOnClickListener {
            requireView().findNavController()
                .navigate(R.id.action_onBoardingFragment_to_instructionsFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}