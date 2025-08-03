package com.mr.anonym.discounts.ui.screens.onBoarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Icon
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
import com.mr.anonym.discounts.R

@Composable
fun OnBoardingPager(
    modifier: Modifier = Modifier,
    secondaryColor: Color,
    componentColor: Color,
    images: List<Int>,
    descriptions: List<String>,
    onPagerState:(Int)-> Unit
) {

    val pagerState = rememberPagerState { 2 }

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
                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(30.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Icon(
                        modifier = Modifier
                            .size(20.dp),
                        painter = painterResource( if ( page == 0 ) R.drawable.ic_selected_page else R.drawable.ic_unselected_page ),
                        tint = if ( page == 0 ) componentColor else secondaryColor,
                        contentDescription = ""
                    )
                    Spacer(Modifier.width(20.dp))
                    Icon(
                        modifier = Modifier
                            .size(20.dp),
                        painter = painterResource( if ( page == 1 ) R.drawable.ic_selected_page else R.drawable.ic_unselected_page ),
                        tint = if ( page == 1 ) componentColor else secondaryColor,
                        contentDescription = ""
                    )
                }
                Spacer(Modifier.height(70.dp))
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