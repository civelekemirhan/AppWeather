package com.example.appweather.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.appweather.ui.theme.bottomBarBackground
import com.example.appweather.ui.theme.bottomBarIconColor

@Composable
fun BottomBarComponent(
    homeOnClick: () -> Unit = {},
    searchOnClick: () -> Unit = {},
    starOnClick: () -> Unit = {}
) {

    BottomAppBar(
        modifier = Modifier
            .fillMaxWidth(),
        containerColor = MaterialTheme.colorScheme.bottomBarBackground,
        contentColor = MaterialTheme.colorScheme.bottomBarIconColor,
        actions = {
            IconButton(onClick = { homeOnClick() }) {
                Icon(
                    Icons.Filled.Home,
                    contentDescription = "Localized description"
                )
            }
            IconButton(onClick = { searchOnClick() }) {
                Icon(
                    Icons.Filled.Search,
                    contentDescription = "Localized description"
                )
            }
            IconButton(onClick = { starOnClick() }) {
                Icon(
                    Icons.Filled.Star,
                    contentDescription = "Localized description"
                )
            }
        },
        contentPadding = PaddingValues()
    )

}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun PreviewBottomBarComponent() {
    Scaffold(bottomBar = { BottomBarComponent() }) {
        Column(modifier = Modifier
            .padding(it)
            .fillMaxSize()) {

        }
    }

}