package com.bugdeve.music.composes

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

@Composable
fun LocalMusic(navHostController: NavHostController){
    Text(text = "本地音乐")
}