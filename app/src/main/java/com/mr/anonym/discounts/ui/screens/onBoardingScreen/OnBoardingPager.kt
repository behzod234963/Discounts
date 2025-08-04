package com.mr.anonym.discounts.ui.screens.onBoardingScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun OnBoardingPager(
    modifier: Modifier = Modifier,
    secondaryColor: Color,
    pagerState: PagerState,
    images: List<Int>,
    descriptions: List<String>,
    onPagerState:(Int)-> Unit
) {

    HorizontalPager(

        modifier = modifier,
        state = pagerState
    ) { page ->
        onPagerState(pagerState.currentPage)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f),
            contentAlignment = Alignment.Center
        ){
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    modifier = Modifier
                        .size(if ( page == 0 ) 200.dp else 150.dp),
                    painter = painterResource(images[page]),
                    contentDescription = ""
                )
                Spacer(Modifier.height(50.dp))
                Text(
                    text = descriptions[page],
                    color = secondaryColor,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}