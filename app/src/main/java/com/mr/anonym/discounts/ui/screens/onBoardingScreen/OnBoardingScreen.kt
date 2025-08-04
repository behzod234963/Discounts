package com.mr.anonym.discounts.ui.screens.onBoardingScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import com.mr.anonym.discounts.presentation.navigation.ScreensRouter
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun OnBoardingScreen(
    navController: NavController
) {

//  Contexts & scopes
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

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

//    State
    val pagerState = rememberPagerState { 2 }

//    List & arrays
    val images = listOf(
        R.drawable.ic_interface,
        R.drawable.ic_discount
    )
    val descriptions = listOf(
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
                onSkipClick = { navController.navigate(ScreensRouter.InformationScreen.route) }
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
                    .fillMaxHeight(0.6f),
                secondaryColor = secondaryColor,
                pagerState = pagerState,
                images = images, descriptions = descriptions
            ) { page.intValue = it }
            Spacer(Modifier.height(10.dp))
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .height(30.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ){
                Icon(
                    modifier = Modifier
                        .size( if ( page.intValue == 0) 15.dp else 15.dp  ),
                    painter = painterResource( if ( page.intValue == 0 ) R.drawable.ic_selected_page else R.drawable.ic_unselected_page ),
                    tint = if ( page.intValue == 0 ) componentColor else secondaryColor,
                    contentDescription = ""
                )
                Spacer(Modifier.width(20.dp))
                Icon(
                    modifier = Modifier
                        .size(if ( page.intValue == 1) 15.dp else 15.dp),
                    painter = painterResource( if ( page.intValue == 1 ) R.drawable.ic_selected_page else R.drawable.ic_unselected_page ),
                    tint = if ( page.intValue == 1 ) componentColor else secondaryColor,
                    contentDescription = ""
                )
            }
            Spacer(Modifier.height(40.dp))
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = componentColor,
                    contentColor = componentColor
                ),
                shape = RoundedCornerShape(10.dp),
                onClick = {
                    if ( page.intValue == 0 ){
                        coroutineScope.launch {
                            pagerState.scrollToPage(1)
                        }
                    }else{
                        sharedPreferences.showLocation(true)
                        navController.navigate(ScreensRouter.InformationScreen.route)
                    }
                }
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