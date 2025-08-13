package com.mr.anonym.discounts.ui.screens.locationScreen

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.mr.anonym.discounts.R

@Composable
fun LocationFAB(
    onClick:()-> Unit,
    componentColor: Color
) {
    FloatingActionButton(
        onClick = { onClick() },
        shape = RoundedCornerShape(10.dp),
        containerColor = componentColor,
        contentColor = componentColor
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_orientation),
            tint = Color.White,
            contentDescription = ""
        )
    }
}