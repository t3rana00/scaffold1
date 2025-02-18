package com.example.scaffoldappbar  // Ensure this matches your package

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.*
import com.example.scaffoldappbar.ui.theme.ScaffoldTheme

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ScaffoldTheme {
                ScaffoldApp()
            }
        }
    }
}

// ✅ Main Navigation Setup
@Composable
fun ScaffoldApp() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable(route = "home") { MainScreen(navController) }
        composable(route = "info") { InfoScreen(navController) }
        composable(route = "settings") { SettingsScreen(navController) }
    }
}

// ✅ Main TopAppBar (Home Screen)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopAppBar(title: String, navController: NavController) {
    var expanded by remember { mutableStateOf(false) }

    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        title = { Text(text = title) },
        navigationIcon = {
            IconButton(onClick = { /* do something */ }) {
                Icon(imageVector = Icons.Filled.Menu, contentDescription = "Open menu")
            }
        },
        actions = {
            IconButton(onClick = { expanded = !expanded }) {
                Icon(imageVector = Icons.Filled.MoreVert, contentDescription = "Open submenu")
            }
            DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                DropdownMenuItem(text = { Text("Info") }, onClick = { navController.navigate("info") })
                DropdownMenuItem(text = { Text("Settings") }, onClick = { navController.navigate("settings") })
            }
        }
    )
}

// ✅ Back Button TopAppBar (Info & Settings Screens)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenTopBar(title: String, navController: NavController) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        title = { Text(text = title) },
        navigationIcon = {
            IconButton(onClick = { navController.navigateUp() }) {
                Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
            }
        },
    )
}

// ✅ Home Screen
@Composable
fun MainScreen(navController: NavController) {
    Scaffold(
        topBar = { MainTopAppBar("My App", navController) }
    ) { innerPadding ->
        Text(text = "Home Screen", modifier = Modifier.padding(innerPadding))
    }
}

// ✅ Info Screen
@Composable
fun InfoScreen(navController: NavController) {
    Scaffold(
        topBar = { ScreenTopBar("Info", navController) }
    ) { innerPadding ->
        Text(text = "Info Screen", modifier = Modifier.padding(innerPadding))
    }
}

// ✅ Settings Screen
@Composable
fun SettingsScreen(navController: NavController) {
    Scaffold(
        topBar = { ScreenTopBar("Settings", navController) }
    ) { innerPadding ->
        Text(text = "Settings Screen", modifier = Modifier.padding(innerPadding))
    }
}

// ✅ Preview
@Preview(showBackground = true)
@Composable
fun MyAppPreview() {
    ScaffoldTheme {
        ScaffoldApp()
    }
}
