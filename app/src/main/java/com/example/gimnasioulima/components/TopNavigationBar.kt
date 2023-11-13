package com.example.gimnasioulima.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.gimnasioulima.configs.TopBarScreen
import com.example.gimnasioulima.ui.theme.Orange40

@Composable
fun TopNavigationBar(navController: NavController, screens: List<TopBarScreen>) {
    var isMenuExpanded by remember { mutableStateOf(false) }
    val contentColor = if (isSystemInDarkTheme()) Color.White else Color.Black
    TopAppBar(
        title = { Text(text = "Gimnasio Ulima", color = contentColor) },
        actions = {
            IconButton(
                onClick = {
                    isMenuExpanded = true
                }
            ) {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "Menu",
                    tint = contentColor
                )
            }
            DropdownMenu(
                expanded = isMenuExpanded,
                onDismissRequest = { isMenuExpanded = false },
                modifier = Modifier.padding(end = 16.dp)
            ) {
                screens.forEachIndexed { index, item ->
                    DropdownMenuItem(
                        onClick = {
                            isMenuExpanded = false
                            if(item.onclick == null) {
                                navController.navigate(item.route)
                            } else {
                                item.onclick?.let { it() }
                            }
                        }
                    ) {
                        Text(text = item.title)
                    }
                }
            }
        },
        backgroundColor = Orange40
    )
}
