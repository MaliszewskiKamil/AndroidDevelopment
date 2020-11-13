package com.example.madlevel3task2.fragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import com.example.madlevel3task2.R
import com.example.madlevel3task2.data.Portal
import com.example.madlevel3task2.fragments.PortalsFragment.Companion.PORTAL_EXTRA
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_add_portals.*
import kotlinx.android.synthetic.main.item_portal.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */

const val ARG_PORTAL_KEY = "arg_portal_key"
const val ARG_PORTAL_NAME = "arg_portal_name"
const val ARG_PORTAL_URL = "arg_portal_url"
const val ARG_PORTAL = "arg_portal"

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
            onAddPortal()
        }
    }

    private fun onAddPortal(){
//
//        val args = Bundle()
//
//        args.putString(ARG_PORTAL_NAME, etTitle.text.toString())
//        args.putString(ARG_PORTAL_URL, etUrl.text.toString())
////        if(portalName.isNotBlank() && portalUrl.isNotBlank()){
////            setFragmentResult(ARG_PORTAL_KEY, bundleOf(Pair(ARG_PORTAL_NAME, portalName), Pair(
////                ARG_PORTAL_URL, portalUrl)))
////            findNavController().popBackStack()
////        }

        if(etTitle.text.toString().isNotBlank() && etUrl.text.toString().isNotBlank()) {
            val portal = Portal(etTitle.text.toString(), etUrl.text.toString())
            val resultIntent = Intent()
            resultIntent.putExtra(PORTAL_EXTRA, portal)
            setFragmentResult(ARG_PORTAL_KEY, bundleOf(Pair(ARG_PORTAL, portal)))
        } else {
            Toast.makeText(context, "Fields cannot be blank", Toast.LENGTH_LONG).show()
        }

        findNavController().navigate(
            R.id.action_addPortalsFragment_to_portalsFragment
        )


    }
}