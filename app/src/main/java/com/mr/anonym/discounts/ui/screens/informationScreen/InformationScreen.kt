package com.mr.anonym.discounts.ui.screens.informationScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.mr.anonym.data.local.instance.SharedPreferencesInstance
import com.mr.anonym.discounts.R

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun InformationScreen(
    navController: NavController
) {

//    Contexts & Scopes
    val context = LocalContext.current

//    Objects
    val sharedPreferences = SharedPreferencesInstance(context)

//    Booleans
    val systemThemeState = sharedPreferences.systemThemeState()
    val darkThemeState = sharedPreferences.darkThemeState()
    val locationVisibilityState = sharedPreferences.locationVisibilityState()

//    Colors
    val componentColor = Color(67, 123, 205, 255)
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
    val systemItemsColor = if (isSystemInDarkTheme()) Color(16, 15, 15, 255) else Color.White
    val itemsColor = when {
        systemThemeState -> systemItemsColor
        darkThemeState -> Color(16, 15, 15, 255)
        else -> Color.White
    }

//    UI
    Scaffold(
        containerColor = primaryColor,
        contentColor = primaryColor
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(10.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.7f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    modifier = Modifier
                        .size(100.dp),
                    painter = painterResource( if ( locationVisibilityState ) R.drawable.ic_location else R.drawable.ic_notification ),
                    contentDescription = ""
                )
            }
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = componentColor,
                    contentColor = componentColor
                ),
                shape = RoundedCornerShape(10.dp),
                onClick = {  }
            ) {
                Text(
                    text = stringResource( if (locationVisibilityState) R.string.show_where_i_am else R.string.grant_permission ),
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}

@Preview
@Composable
private fun PreviewInformationScreen() {
    val context = LocalContext.current
    InformationScreen(
        navController = NavController(context)
    )
}