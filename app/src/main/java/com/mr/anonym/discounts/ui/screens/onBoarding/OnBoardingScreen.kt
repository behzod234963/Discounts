package com.mr.anonym.discounts.ui.screens.onBoarding

import android.annotation.SuppressLint
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
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
fun OnBoardingScreen(
    navController: NavController
) {

//  Contexts
    val context = LocalContext.current

//    Objects
    val sharedPreferences = SharedPreferencesInstance(context)

//    Booleans
    val systemThemeState = sharedPreferences.systemThemeState()
    val darkThemeState = sharedPreferences.darkThemeState()

//    Integers
    val page = remember { mutableIntStateOf( 0 ) }

//    Colors
    val componentColor = Color(67, 123, 205, 255)
    val systemPrimaryColor = if (isSystemInDarkTheme()) Color.Black else Color.White
    val primaryColor = when {
        systemThemeState -> systemPrimaryColor
        darkThemeState -> Color.Black
        else -> Color.White
    }
    val systemSecondaryColor = if ( isSystemInDarkTheme() ) Color.White else Color.Black
    val secondaryColor = when {
        systemThemeState -> systemSecondaryColor
        darkThemeState -> Color.White
        else -> Color.Black
    }

//    List & arrays
    val images = listOf<Int>(
        R.drawable.ic_interface,
        R.drawable.ic_discount
    )
    val descriptions = listOf<String>(
        stringResource(R.string.enjoyful_interface),
        stringResource(R.string.discounts_at_every_turn)
    )

//    UI
    Scaffold(
        containerColor = primaryColor,
        contentColor = primaryColor,
        topBar = {
            OnBoardingTopBar(
                primaryColor = primaryColor,
                componentColor = componentColor,
                onSkipClick = { TODO() }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            OnBoardingPager(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.8f),
                componentColor = componentColor,
                secondaryColor = secondaryColor,
                images = images, descriptions = descriptions,
                onPagerState = { page.intValue = it }
            )
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
                    text = stringResource(R.string.continue_),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White
                )
            }
        }
    }
}

@Preview
@Composable
private fun PreviewOnBoardingScreen() {
    val context = LocalContext.current
    OnBoardingScreen(
        navController = NavController(context)
    )
}