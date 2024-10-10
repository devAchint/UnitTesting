package com.techuntried.unittesting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.techuntried.unittesting.databinding.FragmentLoginBinding

class FragmentLogin : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnClickListeners()
    }

    private fun setOnClickListeners() {
        binding.submitButton.setOnClickListener {
            val username = binding.username.text.toString()
            if (!Util.checkIsUsernameValid(username)) {
                Toast.makeText(context, "username is not valid", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val password  = binding.password.text.toString()
            if (!Util.checkPasswordValid(password)){
                Toast.makeText(context, "password is not valid", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val confirmPassword = binding.confirmPassword.text.toString()
            if (password != confirmPassword){
                Toast.makeText(context, "password does not match", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            Toast.makeText(context, "login successful", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}