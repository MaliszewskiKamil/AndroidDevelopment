package com.example.madlevel3task2.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Portal(var portalName: String, var portalUrl: String) : Parcelable