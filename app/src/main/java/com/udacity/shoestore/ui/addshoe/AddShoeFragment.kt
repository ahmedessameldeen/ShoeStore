package com.udacity.shoestore.ui.addshoe

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
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
        binding.btnAddShoe.setOnClickListener {
            addShoeViewModel.addShoe(
                binding.etShoeName.text.toString(),
                binding.etShoeCompany.text.toString(),
                binding.etShoeSize.text.toString().takeIf {
                    it.isNotEmpty()
                }?.toDouble() ?: 0.0,
                binding.etShoePrice.text.toString().takeIf {
                    it.isNotEmpty()
                }?.toDouble() ?: 0.0,
                binding.etShoeDescription.text.toString(),
            )
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addShoeViewModel = ViewModelProvider(
            this,
            AddShoeViewModelFactory()
        )[AddShoeViewModel::class.java]

        observeAddingShoeResult()

        observeFormState()

        addChangeListeners()
    }

    private fun addChangeListeners() {
        val afterTextChangedListener = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                // ignore
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                // ignore
            }

            override fun afterTextChanged(s: Editable) {
                validateCredentials()
            }
        }
        binding.etShoeName.addTextChangedListener(afterTextChangedListener)
        binding.etShoeCompany.addTextChangedListener(afterTextChangedListener)
        binding.etShoeSize.addTextChangedListener(afterTextChangedListener)
        binding.etShoePrice.addTextChangedListener(afterTextChangedListener)
        binding.etShoeDescription.addTextChangedListener(afterTextChangedListener)
    }

    private fun validateCredentials() {
        addShoeViewModel.validateCredentials(
            binding.etShoeName.text.toString(),
            binding.etShoeCompany.text.toString(),
            binding.etShoeSize.text.toString().takeIf {
                it.isNotEmpty()
            }?.toDouble() ?: 0.0,
            binding.etShoePrice.text.toString().takeIf {
                it.isNotEmpty()
            }?.toDouble() ?: 0.0,
            binding.etShoeDescription.text.toString(),
        )
    }

    private fun observeFormState() {
        addShoeViewModel.addShoeFormState.observe(viewLifecycleOwner,
            Observer { addShoeFormState ->
                if (addShoeFormState == null) {
                    return@Observer
                }
                binding.btnAddShoe.isEnabled = addShoeFormState.isDataValid

                addShoeFormState.shoeNameError?.let {
                    binding.etShoeName.error = getString(it)
                }
                addShoeFormState.shoeCompanyError?.let {
                    binding.etShoeCompany.error = getString(it)
                }
                addShoeFormState.shoePriceError?.let {
                    binding.etShoePrice.error = getString(it)
                }
                addShoeFormState.shoeSizeError?.let {
                    binding.etShoeSize.error = getString(it)
                }
                addShoeFormState.shoeDescriptionError?.let {
                    binding.etShoeDescription.error = getString(it)
                }
            })
    }

    private fun observeAddingShoeResult() {
        addShoeViewModel.addShoeResult.observe(viewLifecycleOwner,
            Observer { loginResult ->
                loginResult ?: return@Observer
                loginResult.error?.let {
                    showAddingShoeFailed(it)
                }
                loginResult.success?.let {
                    mainViewModel.addShoe(it)
                    showAddingShoeSucceeded()
                    findNavController().popBackStack()
                }
                addShoeViewModel._addShoeResult.value = null
            })
    }

    private fun showAddingShoeFailed(@StringRes errorString: Int) {
        val appContext = context?.applicationContext ?: return
        Toast.makeText(appContext, errorString, Toast.LENGTH_SHORT).show()
    }

    private fun showAddingShoeSucceeded() {
        val appContext = context?.applicationContext ?: return
        Toast.makeText(appContext, R.string.shoe_added_successfully, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}