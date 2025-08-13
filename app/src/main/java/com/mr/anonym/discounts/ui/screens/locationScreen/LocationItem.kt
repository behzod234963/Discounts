package com.mr.anonym.discounts.ui.screens.locationScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LocationItem(
    primaryColor: Color,
    secondaryColor: Color,
    tertiaryColor: Color,
    title: String,
    onClick:()-> Unit
) {
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .background(primaryColor)
            .clickable { onClick() },
        verticalArrangement = Arrangement.SpaceBetween
    ){
        HorizontalDivider(thickness = 1.dp, color = tertiaryColor)
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp),
            text = title,
            color = secondaryColor,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold
        )
        HorizontalDivider(thickness = 1.dp, color = tertiaryColor)
    }
}

@Preview
@Composable
private fun PreviewItem() {
    LocationItem(
        primaryColor = Color.White,
        secondaryColor = Color.Black,
        tertiaryColor = Color.LightGray,
        title = "Beruni"
    ) { }
}