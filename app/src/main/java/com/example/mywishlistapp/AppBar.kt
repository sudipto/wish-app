package com.example.mywishlistapp

import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
    title: String,
    onBackNavClick: () -> Unit = {}
) {
    TopAppBar(
        title = {
            Text(
                text = title,
                color = colorResource(id = R.color.white),
                modifier = Modifier.padding(start = 4.dp).heightIn(max = 24.dp)
            )
        },
        modifier = Modifier.shadow(
            elevation = 3.dp
        ),
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = colorResource(R.color.app_bar_color)
        ),
        navigationIcon = {
            if(title != "Wishlist") {
                IconButton(
                    onClick = { onBackNavClick() }
                ) {
                    Icon(
                        painter = painterResource(R.drawable.arrow_back_24),
                        tint = Color.White,
                        contentDescription = null
                    )
                }
            }
        }
    )
}