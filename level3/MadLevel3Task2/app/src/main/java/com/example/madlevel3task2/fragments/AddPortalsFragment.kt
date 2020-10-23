package com.example.madlevel3task2.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.madlevel3task2.R
import kotlinx.android.synthetic.main.fragment_add_portals.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class AddPortalsFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_portals, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnAddPortal.setOnClickListener{
            findNavController().navigate(
                R.id.action_addPortalsFragment_to_portalsFragment
            )
        }


    }
}