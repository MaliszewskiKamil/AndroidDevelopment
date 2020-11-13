package com.example.madlevel3task2.fragments

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.browser.customtabs.CustomTabsIntent
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.madlevel3task2.R
import com.example.madlevel3task2.adapters.PortalAdapter
import com.example.madlevel3task2.data.Portal
import kotlinx.android.synthetic.main.fragment_portals.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */

private val portals = arrayListOf<Portal>()
private val portalsAdapter = PortalAdapter(portals)

class PortalsFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //fab.visibility = View.VISIBLE
        return inflater.inflate(R.layout.fragment_portals, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        observeAddReminderResults()

    }

    private fun observeAddReminderResults() {
        val portalName = arguments?.getString(ARG_PORTAL_NAME)
        val portalUrl = arguments?.getString(ARG_PORTAL_URL)

        if(!portalName.isNullOrEmpty() && !portalUrl.isNullOrEmpty()) {
            val portal = Portal(portalName, portalUrl)
            portals.add(portal)
            portalsAdapter.notifyDataSetChanged()
        }
        setFragmentResultListener(ARG_PORTAL_KEY) {
            key, bundle -> bundle.get(ARG_PORTAL)?.let {



            portals.add(it as Portal)
            portalsAdapter.notifyDataSetChanged()

        }
        }

    }

    private fun initViews() {
        rvPortals.layoutManager = StaggeredGridLayoutManager(2, RecyclerView.VERTICAL)
        rvPortals.adapter = portalsAdapter

        fab.setOnClickListener {
            findNavController().navigate(R.id.action_portalsFragment_to_addPortalsFragment)
        }


    }



    companion object{
        const val PORTAL_EXTRA = "PORTAL_EXTRA"
        const val ADD_PORTAL_REQUEST_CODE = 100
    }
}