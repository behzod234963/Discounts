package com.mr.anonym.discounts.presentation.managers

import android.content.Context
import android.location.Address
import android.location.Geocoder
import android.os.Build
import android.util.Log
import com.mr.anonym.discounts.R

class CoordinateManager(
    private val geocoder: Geocoder
) {

    fun convertGeoCode(
        latitude: Double,
        longitude: Double,
        context: Context,
        onResult: (String) -> Unit
    ) {
        if (Build.VERSION.SDK_INT >= 33) {
            geocoder.getFromLocation(latitude, longitude, 1, object : Geocoder.GeocodeListener {
                override fun onGeocode(addresses: List<Address>) {
                    val address = if (addresses.isNotEmpty()) {
                        addresses[0].subAdminArea
                    } else {
                        context.getString(R.string.unknown)
                    }
                    Log.d("UtilsLogging", "onGeocode: $address")
                    onResult(address ?: context.getString(R.string.unknown))
                }

                override fun onError(errorMessage: String?) {
                    onResult(context.getString(R.string.unknown))
                }
            })
        } else {
            val addresses = geocoder.getFromLocation(latitude, longitude, 1)
            val address = if (!addresses.isNullOrEmpty()) {
                addresses[0].subAdminArea
            } else {
                context.getString(R.string.unknown)
            }
            onResult(address ?: context.getString(R.string.unknown))
        }
    }
    fun searchWithAddress(
        query: String,
        maxResults:Int = 10,
        onResult:(List<Address>)-> Unit
    ){

        if (Build.VERSION.SDK_INT >= 33){
            geocoder.getFromLocationName(query,maxResults
            ) { addresses -> onResult(addresses) }
        }else{
            val addresses = geocoder.getFromLocationName(query, maxResults)
            onResult(addresses ?:emptyList())
        }
    }
}