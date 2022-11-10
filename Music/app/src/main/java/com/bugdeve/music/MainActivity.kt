package com.bugdeve.music

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bugdeve.music.ui.theme.MusicTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "Login", ){
                composable("Login"){
                    Login(navController)
                }
                composable("App"){
                    App(navController)
                }
            }
        }
    }
}

@Composable
fun Login(navController: NavController){
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
                .background(Brush.linearGradient(
                    listOf(Color(255,255,255,175), Color.Transparent),
                    start = Offset(x = width,y=0f),
                    end = Offset(x=0f,y=height)
                    ))
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Brush.linearGradient(
                    listOf(Color(224,102,255,200), Color.Transparent),
                    start = Offset(x = 0f,y=height),
                    end = Offset(x=width,y=0f)
                ))
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
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
        ) {
        Text(text = "App")
    }
    

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MusicTheme {
        Login(navController = rememberNavController())
    }
}