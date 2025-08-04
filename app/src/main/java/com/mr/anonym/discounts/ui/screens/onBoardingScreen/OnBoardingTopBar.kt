package com.mr.anonym.discounts.ui.screens.onBoardingScreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mr.anonym.discounts.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OnBoardingTopBar(
    primaryColor: Color,
    componentColor: Color,
    onSkipClick:()-> Unit
) {

    TopAppBar(
        title = {},
        navigationIcon = {
            OutlinedButton(
                shape = RoundedCornerShape(10.dp),
                border = BorderStroke(1.dp,componentColor),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = primaryColor,
                    containerColor = primaryColor
                ),
                onClick = { onSkipClick() }
            ) {
                Text(
                    text = stringResource(R.string.skip),
                    color = componentColor,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = primaryColor,
        ),
    )
}

@Preview
@Composable
private fun OnBoardingTopBarPrev() {
    OnBoardingTopBar(
        primaryColor = Color.Black,
        componentColor = Color(67, 123, 205, 255)
    ) {}
}