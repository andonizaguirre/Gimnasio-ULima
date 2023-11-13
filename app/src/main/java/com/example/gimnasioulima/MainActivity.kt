package com.example.gimnasioulima

import android.os.Bundle
import android.net.Uri
import android.preference.PreferenceManager
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.example.gimnasioulima.components.BottomNavigationBar
import com.example.gimnasioulima.components.TopNavigationBar
import com.example.gimnasioulima.configs.BottomBarScreen
import com.example.gimnasioulima.configs.TopBarScreen
import com.example.gimnasioulima.factories.LoginScreenViewModelFactory
import com.example.gimnasioulima.screenmodels.CreateUserScreenViewModel
import com.example.gimnasioulima.screenmodels.ExerciseDetailScreenViewModel
import com.example.gimnasioulima.screenmodels.LoginScreenViewModel
import com.example.gimnasioulima.screenmodels.HomeScreenViewModel
import com.example.gimnasioulima.screenmodels.PasswordChangeScreenViewModel
import com.example.gimnasioulima.screenmodels.RoutineScreenViewModel
import com.example.gimnasioulima.screens.CreateUserScreen
import com.example.gimnasioulima.screens.ExerciseDetailScreen
import com.example.gimnasioulima.screens.HomeScreen
import com.example.gimnasioulima.screens.LoginScreen
import com.example.gimnasioulima.screens.PasswordChangeScreen
import com.example.gimnasioulima.screens.ProfileScreen
import com.example.gimnasioulima.screens.RoutineScreen
import com.example.gimnasioulima.ui.theme.GimnasioULimaTheme
import com.example.gimnasioulima.screens.SplashScreen
import com.example.gimnasioulima.storages.UserStorage

class MainActivity : ComponentActivity() {
    private lateinit var preferencesManager: PreferenceManager
    private val loginScreenViewModel: LoginScreenViewModel by viewModels {
        LoginScreenViewModelFactory(applicationContext)
    }
    private val passwordChangeScreenViewModel by viewModels<PasswordChangeScreenViewModel>()
    private val createUserScreenViewModel by viewModels<CreateUserScreenViewModel>()
    private val homeScreenViewModel by viewModels<HomeScreenViewModel>()
    private val routineScreenViewModel by viewModels<RoutineScreenViewModel>()
    private val exerciseDetailScreenViewModel by viewModels<ExerciseDetailScreenViewModel>()
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GimnasioULimaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    val blackList: List<String> = listOf("splash","login", "password_change", "create_user", "profile")
                    var aboutDialog by remember { mutableStateOf(false) }
                    var shareDialog by remember { mutableStateOf(false) }
                    val currentRoute = navBackStackEntry?.destination?.route
                    Scaffold(
                        topBar = {
                            if(!blackList.contains(currentRoute)) {
                                val screens: List<TopBarScreen> = listOf(
                                    TopBarScreen(
                                        route = "home",
                                        title = "Home",
                                    ),
                                    TopBarScreen(
                                        route = "profile",
                                        title = "Editar Perfil",
                                    ),
                                    TopBarScreen(
                                        route = "",
                                        title = "Acerca de",
                                        onclick = {
                                            aboutDialog = true
                                        }
                                    ),
                                    TopBarScreen(
                                        route = "",
                                        title = "Cerrar Sesión",
                                        onclick = {
                                            finishAffinity()
                                        }
                                    ),
                                )
                                TopNavigationBar(navController, screens)
                            }
                        },
                        bottomBar = {
                            if(!blackList.contains(currentRoute)) {
                                val screens: List<BottomBarScreen> = listOf(
                                    BottomBarScreen(
                                        route = "home",
                                        title = "Mi rutina",
                                        icon = Icons.Default.Home
                                    ),
                                    BottomBarScreen(
                                        route = "exercises",
                                        title = "Ejercicios",
                                        icon = Icons.Default.List
                                    ),
                                    BottomBarScreen(
                                        route = "",
                                        title = "Compartir",
                                        onclick = {
                                            shareDialog = true
                                        },
                                        icon = Icons.Default.Share
                                    ),
                                )
                                BottomNavigationBar(navController = navController, screens)
                            }
                        }
                    ) { innerPadding ->
                        Column(modifier = Modifier.padding(innerPadding)) {
                            if (aboutDialog) {
                                AlertDialog(
                                    onDismissRequest = {
                                        aboutDialog = false
                                    },
                                    title = {
                                        Text(text = "Integrantes de Grupo")
                                    },
                                    text = {
                                        Column(){
                                            Text("20182686 - Angelo Guillen")
                                            Text("20142881 - Andoni Izaguirre")
                                            Text("20192985 - Gabriel Linares")
                                            Text("20202407 - Hector Meneses")
                                            Text("20203668 - Franco Sotelo")
                                        }
                                    },
                                    confirmButton = {
                                        TextButton(
                                            onClick = {
                                                // Lógica para manejar el botón de confirmación
                                                aboutDialog = false
                                            }
                                        ) {
                                            Text("Aceptar", color = Color.Black)
                                        }
                                    }
                                )
                            }
//                            if (shareDialog) {
//                                AlertDialog(
//                                    onDismissRequest = {
//                                        shareDialog = false
//                                    },
//                                    title = {
//                                        Text(text = "\u200E \u200E \u200E\u200E \u200E \u200E \u200E \u200E \u200E \u200E \u200E \u200E \u200E \u200E  Gracias por compartir",)
//                                    },
//                                    text = {
//                                        val imageUrl1 =
//                                            "https://upload.wikimedia.org/wikipedia/commons/thumb/6/6b/WhatsApp.svg/800px-WhatsApp.svg.png"
//                                        val imageUrl2 =
//                                            "https://logowik.com/content/uploads/images/633_facebook_icon.jpg"
//                                        val uri = parse(imageUrl1)
//                                        val uri2 = parse(imageUrl2)
//                                        val painter1= rememberImagePainter(
//                                            data = uri.scheme + "://" + uri.host + uri.path + (if (uri.query != null) uri.query else ""),
//                                            builder = {
//                                                transformations(CircleCropTransformation())
//                                            }
//                                        )
//                                        val painter2 = rememberImagePainter(
//                                            data = uri2.scheme + "://" + uri2.host + uri2.path + (if (uri2.query != null) uri2.query else ""),
//                                            builder = {
//                                                transformations(CircleCropTransformation())
//                                            }
//                                        )
//                                        Column {
//                                            Row{
//                                                Text("\u200E \u200E \u200E  WhatsApp",
//                                                    modifier=Modifier.padding(20.dp))
//                                                Text("\u200E \u200E \u200E  Facebook",
//                                                    modifier=Modifier.padding(20.dp))
//                                            }
//                                            Row{
//                                                Image(
//                                                    painter = painter1,
//                                                    contentDescription = null, // Set a proper content description if required
//                                                    modifier = Modifier.size(
//                                                        130.dp,
//                                                        130.dp)
//                                                        .clickable {
//                                                            sendGitHubLinkToWhatsApp()
//                                                        }
//                                                )
//                                                Image(
//                                                    painter = painter2,
//                                                    contentDescription = null, // Set a proper content description if required
//                                                    modifier = Modifier.size(
//                                                        120.dp,
//                                                        120.dp)
//                                                        .clickable {
//                                                            sendGitHubLinkToFacebook()
//                                                        }
//                                                )
//                                            }
//                                        }
//                                    },
//                                    confirmButton = {
//                                        TextButton(
//                                            onClick = {
//                                                // Lógica para manejar el botón de confirmación
//                                                shareDialog = false
//                                            }
//                                        ) {
//                                            Text("Regresar")
//                                        }
//                                    }
//                                )
//                            }
                            NavHost(navController, startDestination = "splash") {
                                composable("splash") {
                                    SplashScreen {
                                        navController.navigate("login")
                                    }
                                }
                                composable("password_change") {
                                    PasswordChangeScreen(navController, passwordChangeScreenViewModel)
                                }
                                composable(route = "create_user") {
                                    CreateUserScreen(navController, createUserScreenViewModel)
                                }
                                composable(route = "home") {
                                    HomeScreen(navController, homeScreenViewModel)
                                }
                                composable("profile") {
                                    ProfileScreen(navController)
                                }
                                composable(route = "routine?user_id={user_id}&member_id={member_id}", arguments = listOf(
                                    navArgument("user_id") {
                                        type = NavType.IntType
                                        defaultValue = 0
                                    },
                                    navArgument("member_id") {
                                        type = NavType.IntType
                                        defaultValue = 0
                                    }
                                ), content = { entry ->
                                    val memberId = entry.arguments?.getInt("member_id")!!
                                    val userId = entry.arguments?.getInt("user_id")!!
                                    routineScreenViewModel.memberId = memberId
                                    routineScreenViewModel.userId = userId
                                    routineScreenViewModel.fetchBodyPartsExercises()
                                    routineScreenViewModel.fetchBodyParts()
                                    routineScreenViewModel.fetchExercises()
                                    RoutineScreen(routineScreenViewModel, navController)
                                })
                                composable(route = "exercise/edit?exercise_id={exercise_id}", arguments = listOf(
                                    navArgument("exercise_id") {
                                        type = NavType.IntType
                                        defaultValue = 0
                                    }
                                ), content = { entry ->
                                    val exerciseId = entry.arguments?.getInt("exercise_id")!!
                                    exerciseDetailScreenViewModel.exerciseId = exerciseId
                                    ExerciseDetailScreen(navController, exerciseDetailScreenViewModel)
                                })
                                composable(route = "login") {
                                    Log.d("ROUTER", "login")
                                    val dataStore = UserStorage(applicationContext)
                                    if (dataStore.getUserId.collectAsState(initial = 9999).value != 0) {
                                        val userId =
                                            dataStore.getUserId.collectAsState(initial = 0).value
                                        val memberId = userId
                                        Log.d(
                                            "ROUTER",
                                            dataStore.getUserId.collectAsState(initial = 0).value.toString()
                                        )
                                        if (userId != null && memberId != null) {
                                            routineScreenViewModel.memberId = userId
                                            routineScreenViewModel.userId = userId
                                            routineScreenViewModel.fetchBodyPartsExercises()
                                            routineScreenViewModel.fetchBodyParts()
                                            routineScreenViewModel.fetchExercises()
                                            RoutineScreen(routineScreenViewModel, navController)
                                        }
                                    } else {
                                        LoginScreen(navController, loginScreenViewModel)
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GimnasioULimaTheme {
        Greeting("Android")
    }
}