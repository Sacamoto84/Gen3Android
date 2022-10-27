package com.example.generator2.mainscreen4

import androidx.compose.runtime.Composable
import com.example.generator2.Global

@Composable
private fun Key0()
{
    TemplateButtonBottomBar( str = "0", onClick = {Global.scriptVisible.value = !Global.scriptVisible.value})
}
@Composable
private fun Key1()
{
    TemplateButtonBottomBar( str = "1", onClick = {Global.scriptVisible.value = !Global.scriptVisible.value})
}

@Composable
private fun Key2()
{
    TemplateButtonBottomBar( str = "2", onClick = {Global.scriptVisible.value = !Global.scriptVisible.value})
}

@Composable
private fun Key3()
{
    TemplateButtonBottomBar( str = "3", onClick = {Global.scriptVisible.value = !Global.scriptVisible.value})
}

@Composable
private fun Key4()
{
    TemplateButtonBottomBar( str = "4", onClick = {Global.scriptVisible.value = !Global.scriptVisible.value})
}

@Composable
private fun Key5()
{
    TemplateButtonBottomBar( str = "5", onClick = {Global.scriptVisible.value = !Global.scriptVisible.value})
}



@Composable
fun BottomBarGenerator()
{
    TemplateBottomBar6Key(
        key0 = {Key0()},
        //key1 = {Key1()},
        //key2 = {Key2()},
        key3 = {Key3()},
        //key4 = {},
        key5 = {Key5()},



    )
}