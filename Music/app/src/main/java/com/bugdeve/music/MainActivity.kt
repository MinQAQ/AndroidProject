package com.bugdeve.music

import android.os.Bundle

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat

import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bugdeve.music.composes.*
import com.bugdeve.music.ui.theme.MusicTheme
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.insets.statusBarsHeight
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window,false)

        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "Login", ){
                composable("Login"){
                    Login(navController)
                }
                composable("App"){
                    App(navController)
                }
                composable("LocalMusic"){
                    LocalMusic(navController)
                }
                composable("Collect"){
                    Collect()
                }
                composable("Download"){
                    Download()
                }
            }
        }
    }
}

@Composable
fun Login(navController: NavController){
    //状态栏透明

    ProvideWindowInsets {
        rememberSystemUiController().setSystemBarsColor(Color.Transparent,darkIcons = true)

    }

    //获取状态栏高度
    val statusBarHeightDp = with(LocalContext.current){
        var height = 0
        val resourcesId = resources.getIdentifier("status_bar_height", "dimen", "android")
        if(resourcesId>0) {
            height = resources.getDimensionPixelSize(resourcesId)
        }
        with(LocalDensity.current) {
            height.toDp()
        }
    }



    //屏幕宽高
    var height:Float
    var width:Float
    with(LocalDensity.current){
        height= LocalConfiguration.current.screenHeightDp.dp.toPx()
        width= LocalConfiguration.current.screenWidthDp.dp.toPx()

    }


    var username by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    Spacer(modifier = Modifier
        .statusBarsHeight()
        .fillMaxWidth())

    Box(modifier = Modifier.fillMaxSize()){
        Image(
            painter = painterResource(id = R.drawable.bg),
            contentDescription = null,
            contentScale = ContentScale.FillHeight,
            modifier = Modifier.fillMaxSize()
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.linearGradient(
                        listOf(Color(255, 255, 255, 175), Color.Transparent),
                        start = Offset(x = width, y = 0f),
                        end = Offset(x = 0f, y = height)
                    )
                )
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.linearGradient(
                        listOf(Color(224, 102, 255, 200), Color.Transparent),
                        start = Offset(x = 0f, y = height),
                        end = Offset(x = width, y = 0f)
                    )
                )
        )

    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(value = username, onValueChange = {username=it})
        TextField(value = password, onValueChange = {password=it})
        Button(onClick = {
            navController.navigate("App")
        }) {
            Text(text = "登陆")
        }
    }
}
@Composable
fun App(navController: NavController){
    Column(
        modifier = Modifier.fillMaxSize(), 
        //verticalArrangement = Arrangement.Center,

        ) {
        //顶部
        TopBar {
            App_TopBar()
        }
        //本地
        App_Center(navController as NavHostController)
        //列表
        //播放器

    }
    

}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MusicTheme {
        App(navController = rememberNavController())
    }
}