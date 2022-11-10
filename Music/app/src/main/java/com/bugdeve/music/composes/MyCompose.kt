package com.bugdeve.music.composes

import android.content.Context
import android.widget.ImageButton
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.insets.statusBarsHeight
import com.google.accompanist.systemuicontroller.rememberSystemUiController


//透明状态栏
@Composable
fun TopBar(content: @Composable () -> Unit){
    ProvideWindowInsets {
        rememberSystemUiController().setSystemBarsColor(Color.Transparent,darkIcons = true)
    }
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

    Spacer(modifier = Modifier
        .height(statusBarHeightDp)
        .fillMaxWidth())

    content()
}
@Composable
fun App_TopBar(){
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Icon( imageVector = Icons.Filled.Menu, contentDescription ="" , tint = Color.Blue)
        Icon( imageVector = Icons.Filled.Search, contentDescription ="" , tint = Color.Blue)
    }
}
@Composable
fun App_Center(navHostController: NavHostController){
    var cc= LocalContext.current
    Column() {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {

            Box(modifier = Modifier.clickable{
                navHostController.navigate("Collect")
            }) {
                Column() {
                    Icon( imageVector = Icons.Filled.Favorite, contentDescription ="" , tint = Color.Blue)
                    Text(text = "收藏")
                }

            }


            Box(modifier = Modifier.clickable{
                navHostController.navigate("LocalMusic")
            }) {
              Column() {
                  Icon( imageVector = Icons.Filled.Headphones, contentDescription ="" , tint = Color.Blue)
                  Text(text = "本地音乐")
              }

            }
            Box(modifier = Modifier.clickable{
                navHostController.navigate("Download")
            }) {
                Column() {
                    Icon( imageVector = Icons.Filled.Download, contentDescription ="" , tint = Color.Blue)
                    Text(text = "下载")
                }

            }


        }

    }
}

fun toast(context: Context,value:String){
    Toast.makeText(context,value,Toast.LENGTH_LONG).show()
}
