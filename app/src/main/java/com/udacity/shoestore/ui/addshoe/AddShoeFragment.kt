package com.udacity.shoestore.ui.addshoe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.udacity.shoestore.databinding.FragmentAddShoeBinding
import com.udacity.shoestore.ui.MainViewModel

class AddShoeFragment : Fragment() {

    private lateinit var addShoeViewModel: AddShoeViewModel
    private val mainViewModel: MainViewModel by activityViewModels()

    private var _binding: FragmentAddShoeBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentAddShoeBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addShoeViewModel = ViewModelProvider(
            this,
            AddShoeViewModelFactory()
        )[AddShoeViewModel::class.java]
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}