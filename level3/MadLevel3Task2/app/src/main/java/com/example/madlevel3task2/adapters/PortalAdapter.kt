package com.example.madlevel3task2.adapters

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.RecyclerView
import com.example.madlevel3task2.R
import com.example.madlevel3task2.data.Portal
import com.example.madlevel3task2.fragments.PortalsFragment
import kotlinx.android.synthetic.main.item_portal.view.*




class PortalAdapter(private val portals: List<Portal>) :
        RecyclerView.Adapter<PortalAdapter.ViewHolder>(){

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        fun databind(portal: Portal){
            itemView.tvTitle.text = portal.portalName
            itemView.tvURL.text = portal.portalUrl
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_portal, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.databind(portals[position])

        holder.itemView.setOnClickListener {
            openUrl(portals[position].portalUrl, holder)
        }
    }

    override fun getItemCount(): Int {
        return portals.size
    }
    private fun openUrl(url: String, holder: ViewHolder){
        var itemView: View
        var builder: CustomTabsIntent.Builder = CustomTabsIntent.Builder()
        var customTabsIntent: CustomTabsIntent = builder.build()
        customTabsIntent.launchUrl(holder.itemView.context, Uri.parse(url))

    }

}