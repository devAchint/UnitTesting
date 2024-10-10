package com.techuntried.unittesting.sharedflow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.techuntried.unittesting.databinding.FragmentFlowBinding
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FragmentFlow : Fragment() {

    companion object {
        private val _sharedFlow = MutableSharedFlow<String>()
        val sharedFlow = _sharedFlow.asSharedFlow()

        private val _stateFlow = MutableStateFlow("state flow value")
        val stateFlow = _stateFlow.asStateFlow()
    }

    private var _binding: FragmentFlowBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFlowBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnClickListeners()
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                sharedFlow.collect {
                    binding.sharedFlowCounterText.text = it
                }
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                stateFlow.collect {
                    binding.stateFlowCounterText.text = it
                }
            }
        }
    }

    private fun setOnClickListeners() {
        var count = 0
        binding.increaseCount.setOnClickListener {
            count++
            lifecycleScope.launch {
                _sharedFlow.emit("Shared Count is $count")
                _stateFlow.emit("State Count is $count")
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}