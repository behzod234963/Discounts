package com.mr.anonym.discounts.ui.screens.locationScreen

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import androidx.navigation.NavController
import com.google.android.gms.location.LocationServices
import com.mr.anonym.discounts.presentation.managers.PermissionManager
import com.utsman.osmandcompose.OpenStreetMap
import com.utsman.osmandcompose.rememberCameraState
import com.utsman.osmandcompose.rememberOverlayManagerState
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay

@Composable
fun LocationScreen(
    navController: NavController
) {

//    Context
    val context = LocalContext.current
    val activity = LocalActivity.current

//    Objects
    val permissionManager = PermissionManager(context,activity)

//    State
    val cameraState = rememberCameraState {
        geoPoint = GeoPoint( 55.751244,37.618423 )
        zoom = 12.0
    }
    val fusedClient = LocationServices.getFusedLocationProviderClient(context)
    if (ActivityCompat.checkSelfPermission(
        context,
            Manifest.permission.ACCESS_FINE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED &&
        ActivityCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    ){
        fusedClient.lastLocation.addOnSuccessListener { location ->
            if ( location != null ){
                val geoPoint = GeoPoint(location.latitude,location.longitude)
                cameraState.geoPoint = geoPoint
            }
        }
    }else{
        permissionManager.requestLocationPermission()
    }
    val overlayManagerState = rememberOverlayManagerState()
////    val mapView = rememberOverlayManagerState()
//    val locationOverlay = MyLocationNewOverlay(GpsMyLocationProvider(context),overlayManagerState)
//    locationOverlay.enableMyLocation()
//    overlayManagerState.overlays.add(locationOverlay)

//    UI
    OpenStreetMap (
        modifier = Modifier
            .fillMaxSize(),
        cameraState = cameraState,
        overlayManagerState = overlayManagerState
    )
}

@Preview
@Composable
private fun PreviewLocationScreen() {
    val context = LocalContext.current
    LocationScreen(navController = NavController(context))
}