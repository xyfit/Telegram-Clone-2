package com.example.telgramclone2.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.telgramclone2.R
import com.example.telgramclone2.adapters.ItemColback
import com.example.telgramclone2.adapters.UsersAdapter
import com.example.telgramclone2.databinding.FragmentBaseBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase

class BaseFragment : Fragment() {
    private var _binding: FragmentBaseBinding? = null
    private val binding get() = _binding!!
    private val TAG = "dfasdgh"
    private val adapterUsers by lazy { UsersAdapter(object : ItemColback{
        override fun itemClick(str: String) {
            val bundle = bundleOf("my_key" to "Bexruz")
            findNavController().navigate(R.id.action_baseFragment_to_chatFragment2, bundle)
            Toast.makeText(requireContext(), str, Toast.LENGTH_SHORT).show()
        }
    }) }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding= FragmentBaseBinding.inflate(inflater, container , false)
        // Inflate the layout for this fragment
        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.usersRec.run {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = adapterUsers
        }

        val database = Firebase.database
        val myRef = database.getReference("message")

        val list = arrayListOf<String>()
        for (i in 1..12){
            list.add("User: $i")
        }

        myRef.child("users").setValue(list)

        myRef.child("users").addValueEventListener(object: ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {

                val value = snapshot.getValue<List<String>>()
                adapterUsers.baseList = value?: emptyList()

            }

            override fun onCancelled(error: DatabaseError) {
                Log.w(TAG, "Failed to read value.", error.toException())
            }

        })
//        requireActivity().findViewById<Button>(R.id.button).setOnClickListener {
//            findNavController().navigate(R.id.chatFragment)
//        }
        binding.topAppBar.setNavigationOnClickListener {
                binding.drawerLayout.open()
            }

            binding.navId.setNavigationItemSelectedListener { menuItem ->
                // Handle menu item selected
                binding.drawerLayout.close()
                true
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}