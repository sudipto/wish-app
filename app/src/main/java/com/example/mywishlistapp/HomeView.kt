package com.example.mywishlistapp

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mywishlistapp.data.Wish

@Composable
fun HomeView(
    viewModel: WishViewModel,
    onAddButtonClick: () -> Unit,
    onWishItemClick: (Long) -> Unit,
    onSwipeWishItem: (Wish) -> Unit
) {
    Scaffold(
        topBar = {
            AppBar(
                title = "Wishlist"
            ) {}
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    onAddButtonClick()
                },
                modifier = Modifier.padding(20.dp),
                contentColor = Color.White,
                containerColor = colorResource(R.color.accent_color)
            ) {
                Icon(
                    painter = painterResource(R.drawable.add_24),
                    contentDescription = null
                )
            }
        }
    ) {
        val wishList = viewModel.getAllWishes.collectAsState(initial = listOf())

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            items(
                items = wishList.value,
                key = { wish -> wish.id }
            ) { wish ->
                // Swipe to dismiss
                val swipeToDismissBoxState = rememberSwipeToDismissBoxState()

                when (swipeToDismissBoxState.currentValue) {
                    SwipeToDismissBoxValue.StartToEnd -> {
                        onSwipeWishItem(wish)
                    }

                    // Do nothing when swiped to any other direction
                    else -> {
                        false
                    }
                }

                SwipeToDismissBox(
                    state = swipeToDismissBoxState,
                    modifier = Modifier.fillMaxWidth(),
                    backgroundContent = {
                        Card(
                            modifier = Modifier.wrapContentSize()
                                .padding(start = 8.dp, top = 8.dp, end = 8.dp),
                            elevation = CardDefaults.cardElevation(10.dp)
                        ) {
                            Box(
                                modifier = Modifier.fillMaxSize()
                                    .padding(start = 8.dp),
                                contentAlignment = Alignment.CenterStart
                            ) {
                                Icon(
                                    painter = painterResource(R.drawable.delete_24),
                                    contentDescription = null,
                                    tint = Color.White
                                )
                            }
                        }
                    },
                    enableDismissFromEndToStart = false
                ) {
                    WishItem(wish) { wishId ->
                        onWishItemClick(wishId)
                    }
                }

            }
        }
    }
}

@Composable
fun WishItem(
    wish: Wish,
    onWishItemClick: (Long) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth()
            .padding(start = 8.dp, top = 8.dp, end = 8.dp)
            .clickable() {
                onWishItemClick(wish.id)
            },
        elevation = CardDefaults.cardElevation(10.dp),
        colors = CardDefaults.cardColors(Color.White)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = wish.title,
                fontWeight = FontWeight.ExtraBold
            )

            Text(
                text = wish.description
            )
        }
    }
}

@Preview
@Composable
fun PreviewHomeView() {
//    HomeView({})
}