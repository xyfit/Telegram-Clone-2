package com.example.telgramclone2.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.telgramclone2.App
import com.example.telgramclone2.PrefUtils
import com.example.telgramclone2.R
import com.example.telgramclone2.databinding.FragmentLogInBinding


class LogInFragment : Fragment() {
    private var _binding: FragmentLogInBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLogInBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val getUserName = PrefUtils.firstRegister
        initBtn()

    }
    private fun initBtn() {
        binding.startTelegram.setOnClickListener {
            if (binding.userName.text!!.isNotEmpty()) {
                val user1 = binding.userName.text.toString()
                val user2 = binding.inputPhoneNumber.text.toString()

                App.user1 = user1
                App.user2 = user2
                findNavController().navigate(R.id.action_logInFragment_to_chatFragment)
            } else {
                Toast.makeText(requireContext(),"Username should not be empty", Toast.LENGTH_SHORT).show()
            }

        }
    }
}