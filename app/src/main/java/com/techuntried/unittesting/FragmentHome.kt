package com.techuntried.unittesting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.techuntried.unittesting.databinding.FragmentHomeBinding
import com.techuntried.unittesting.sharedflow.FragmentFlow
import kotlinx.coroutines.launch

class FragmentHome : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnClickListeners()
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                FragmentFlow.sharedFlow.collect {
                    Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                }
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                FragmentFlow.stateFlow.collect {
                    Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun setOnClickListeners() {
        binding.loginButton.setOnClickListener {
            findNavController().navigate(R.id.action_fragmentHome_to_fragmentLogin)
        }
        binding.sharedFlowButton.setOnClickListener {
            findNavController().navigate(R.id.action_fragmentHome_to_fragmentFlow)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}