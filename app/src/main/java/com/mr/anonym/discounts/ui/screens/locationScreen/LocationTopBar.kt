package com.mr.anonym.discounts.ui.screens.locationScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mr.anonym.discounts.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocationTopBar(
    primaryColor: Color,
    secondaryColor: Color,
    isAddressFound: Boolean,
    address: String,
    value: String,
    onValueChange: (String) -> Unit,
    onTrailingIconClick: () -> Unit
) {

    TopAppBar(
        title = {
            if (isAddressFound) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
//                    .background(itemsColor)
                        .padding(5.dp)
                        .clip(RoundedCornerShape(15.dp)),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = stringResource(R.string.your_location) + address,
                        fontSize = 16.sp,
                        color = secondaryColor,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            } else {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp)
                ) {
                    OutlinedTextField(
                        value = value,
                        onValueChange = { onValueChange(it) },
                        modifier = Modifier.fillMaxWidth(),
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Search,
                                tint = secondaryColor,
                                contentDescription = ""
                            )
                        },
                        trailingIcon = {
                            IconButton(
                                onClick = { onTrailingIconClick() }
                            ) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                                    tint = secondaryColor,
                                    contentDescription = ""
                                )
                            }
                        },
                        singleLine = true,
                        textStyle = TextStyle(
                            color = secondaryColor,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold
                        ),
                        shape = RoundedCornerShape(10.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedTextColor = secondaryColor,
                            unfocusedTextColor = secondaryColor,
                            focusedContainerColor = primaryColor,
                            unfocusedContainerColor = primaryColor,
                            focusedBorderColor = primaryColor,
                            unfocusedBorderColor = primaryColor,
                        )
                    )
                }
            }
        }
    )
}