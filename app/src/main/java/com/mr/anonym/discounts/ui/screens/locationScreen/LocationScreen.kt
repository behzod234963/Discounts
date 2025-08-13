package com.mr.anonym.discounts.ui.screens.locationScreen

import android.annotation.SuppressLint
import android.location.Address
import android.location.Geocoder
import android.util.Log
import androidx.appcompat.content.res.AppCompatResources
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.android.gms.location.LocationServices
import com.mr.anonym.data.local.instance.DataStoreInstance
import com.mr.anonym.data.local.instance.SharedPreferencesInstance
import com.mr.anonym.discounts.R
import com.mr.anonym.discounts.presentation.managers.CoordinateManager
import com.mr.anonym.discounts.presentation.managers.updateLocation
import com.mr.anonym.discounts.presentation.navigation.ScreensRouter
import com.utsman.osmandcompose.DefaultMapProperties
import com.utsman.osmandcompose.Marker
import com.utsman.osmandcompose.OpenStreetMap
import com.utsman.osmandcompose.ZoomButtonVisibility
import com.utsman.osmandcompose.rememberCameraState
import com.utsman.osmandcompose.rememberMarkerState
import com.utsman.osmandcompose.rememberOverlayManagerState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.osmdroid.util.GeoPoint
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LocationScreen(
    navController: NavController
) {

//    Context
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

//    Objects
    val sharedPreferences = SharedPreferencesInstance(context)
    val dataStore = DataStoreInstance(context)
    val geoCoder = Geocoder(context, Locale.getDefault())
    val coordinateManager = CoordinateManager(geoCoder)

//    Booleans
    val systemThemeState = sharedPreferences.systemThemeState()
    val darkThemeState = sharedPreferences.darkThemeState()
    val isCameraCentered = remember { mutableStateOf(false) }
    val centerCamera = remember { mutableStateOf(true) }
    val isAddressFound = remember { mutableStateOf( true ) }

//    Strings
    val convertedAddress = remember { mutableStateOf("") }
    val searchValue = remember { mutableStateOf( "" ) }

//    List & arrays
    val foundAddresses = remember { mutableStateOf( emptyList<Address>() ) }

//    Colors
    val componentColor = Color(67, 123, 205, 255)
    val errorContentColor = Color.Red
    val systemPrimaryColor = if (isSystemInDarkTheme()) Color.Black else Color.White
    val primaryColor = when {
        systemThemeState -> systemPrimaryColor
        darkThemeState -> Color.Black
        else -> Color.White
    }
    val systemSecondaryColor = if (isSystemInDarkTheme()) Color.White else Color.Black
    val secondaryColor = when {
        systemThemeState -> systemSecondaryColor
        darkThemeState -> Color.White
        else -> Color.Black
    }
    val systemTertiaryColor = if (isSystemInDarkTheme()) Color.DarkGray else Color.LightGray
    val tertiaryColor = when {
        systemThemeState -> systemTertiaryColor
        darkThemeState -> Color.DarkGray
        else -> Color.LightGray
    }
    val systemItemsColor = if (isSystemInDarkTheme()) Color(16, 15, 15, 255) else Color.White
    val itemsColor = when {
        systemThemeState -> systemItemsColor
        darkThemeState -> Color(16, 15, 15, 255)
        else -> Color.White
    }

//    State
    val userLocation = remember { mutableStateOf(GeoPoint(55.751244, 37.618423)) }
    val cameraState = rememberCameraState {
        geoPoint = userLocation.value
        zoom = 17.0
    }
    val userMarkerState = rememberMarkerState(geoPoint = userLocation.value)
    val mapProperties = remember { mutableStateOf(DefaultMapProperties) }
    val overlayManagerState = rememberOverlayManagerState()
    val fusedClient = remember { LocationServices.getFusedLocationProviderClient(context) }

//    Helpers
    LaunchedEffect(Unit) {
        delay(250L)
        if (!isCameraCentered.value) {
            updateLocation(context, fusedClient) { location ->
                val newPoint = GeoPoint(location.first, location.second)
                userLocation.value = newPoint
                userMarkerState.geoPoint = newPoint
                coordinateManager.convertGeoCode(
                    latitude = location.first,
                    longitude = location.second,
                    context = context,
                    onResult = {
                        Log.d("UtilsLogging", "onGeocodeUI: $it")
                        convertedAddress.value = "$it ?"
                    }
                )
                if (centerCamera.value) {
                    cameraState.geoPoint = newPoint
                }
            }
            isCameraCentered.value = true
        }
    }
    SideEffect {
        mapProperties.value = mapProperties.value
            .copy(isMultiTouchControls = true)
            .copy(isEnableRotationGesture = true)
            .copy(isTilesScaledToDpi = true)
            .copy(zoomButtonVisibility = ZoomButtonVisibility.SHOW_AND_FADEOUT)
    }

//    UI
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    LocationTopBar(
                        primaryColor = primaryColor,
                        secondaryColor = secondaryColor,
                        isAddressFound = isAddressFound.value,
                        address = convertedAddress.value,
                        value = searchValue.value,
                        onValueChange = {
                            searchValue.value = it
                        },
                        onTrailingIconClick = {
                            coordinateManager.searchWithAddress(
                                query = searchValue.value,
                                maxResults = 20,
                                onResult = {
                                    foundAddresses.value = it
                                }
                            )
                        }
                    )
                }
            )
        },
        bottomBar = {
            if (isAddressFound.value){
                BottomAppBar {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
//                    No
                        TextButton(
                            onClick = {
                                isAddressFound.value = false
                            }
                        ) {
                            Text(
                                text = stringResource(R.string.no),
                                fontSize = 16.sp,
                                color = errorContentColor,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
//                    Yes
                        TextButton(
                            onClick = {
                                coroutineScope.launch {
                                    dataStore.saveAddress(convertedAddress.value)
                                    dataStore.saveLatitude(userLocation.value.latitude)
                                    dataStore.saveLongitude(userLocation.value.longitude)
                                    sharedPreferences.showLocation(false)
                                    navController.navigate(ScreensRouter.InformationScreen.route)
                                }
                            }
                        ) {
                            Text(
                                text = stringResource(R.string.yes),
                                fontSize = 16.sp,
                                color = componentColor,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }
                }
            }
        },
        floatingActionButton = {
            LocationFAB(
                componentColor = componentColor,
                onClick = {
                    updateLocation(
                        context,
                        fusedClient
                    ) { location ->
                        val newPoint = GeoPoint(location.first, location.second)
                        userLocation.value = newPoint
                        userMarkerState.geoPoint = newPoint
                        coordinateManager.convertGeoCode(
                            latitude = location.first,
                            longitude = location.second,
                            context = context,
                            onResult = {
                                convertedAddress.value = "$it ?"
                            }
                        )
                        if (centerCamera.value) {
                            cameraState.geoPoint = newPoint
                        }
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ){
            OpenStreetMap(
                modifier = Modifier
                    .fillMaxSize(),
                cameraState = cameraState,
                properties = mapProperties.value,
                overlayManagerState = overlayManagerState,
            ) {
                Marker(
                    state = userMarkerState,
                    visible = true,
                    icon = AppCompatResources.getDrawable(context, R.drawable.ic_location_marker),
                    infoWindowContent = { infoWindowData ->
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(itemsColor)
                                .padding(10.dp)
                                .clip(RoundedCornerShape(15.dp)),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = stringResource(R.string.your_location) + convertedAddress.value,
                                fontSize = 16.sp,
                                color = secondaryColor,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }
                )
            }
            if ( !isAddressFound.value ){
                LazyColumn {
                    items(foundAddresses.value){ address->
                        LocationItem(
                            primaryColor = primaryColor,
                            secondaryColor = secondaryColor,
                            tertiaryColor = tertiaryColor,
                            title = address.subAdminArea,
                            onClick = {
                                cameraState.geoPoint = GeoPoint(address.latitude,address.longitude)
                                userMarkerState.geoPoint = GeoPoint(address.latitude,address.longitude)
                                userLocation.value = GeoPoint(address.latitude,address.longitude)
                                coordinateManager.convertGeoCode(address.latitude,address.longitude,context){
                                    convertedAddress.value = it
                                }
                                isAddressFound.value = true
                            },
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun PreviewLocationScreen() {
    val context = LocalContext.current
    LocationScreen(navController = NavController(context))
}