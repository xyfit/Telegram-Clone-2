package com.example.telgramclone2.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.telgramclone2.databinding.FragmentBaseBinding
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class BaseFragment : Fragment() {
    private var _binding: FragmentBaseBinding? = null
    private val binding get() = _binding!!

//    lateinit var appBarMainBinding: AppBarMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= FragmentBaseBinding.inflate(inflater, container , false)
//        appBarMainBinding = binding.mainAppBarId
        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar()


    }





    private fun initToolbar(){
//        setSupportActionBar(binding.mToolbar)
    }
    private fun writeDataFCM(){
        // Write a message to the database
        val database = Firebase.database
        val myRef = database.getReference("message")

        myRef.setValue("Hello, World!")
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}