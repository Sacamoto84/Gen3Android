package com.example.generator2

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import flipagram.assetcopylib.AssetCopier
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import java.io.File
import java.io.IOException
import java.util.ArrayList

@SuppressLint("StaticFieldLeak")
object Global : ViewModel(){

    var contextActivity   : Context? = null
    var componentActivity : ComponentActivity? = null

    var patchDocument =  ""
    var patchCarrier  = "$patchDocument/Carrier/"
    var patchMod      = "$patchDocument/Mod/"

    var ch1_EN                 = MutableLiveData<Boolean>( true)         //: MutableState<Int> = mutableStateOf( 1)
    var ch1_Carrier_Filename   = MutableLiveData<String> ( "03_HWave2")
    var ch1_Carrier_Fr         = MutableLiveData<Float>    ( 2000.0f) //Частота несущей
    var ch1_AM_EN              = MutableLiveData<Boolean>( false)
    var ch1_AM_Filename        = MutableLiveData<String> ( "09_Ramp")
    var ch1_AM_Fr              = MutableLiveData<Float>  ( 8.7f)
    var ch1_FM_EN              = MutableLiveData<Boolean>( false)
    var ch1_FM_Filename        = MutableLiveData<String> ( "06_CHIRP")
    var ch1_FM_Base            = MutableLiveData<Float>    ( 2500f) //Частота базы
    var ch1_FM_Dev             = MutableLiveData<Float>    ( 1100f) //Частота базы
    var ch1_FM_Fr              = MutableLiveData<Float>  ( 5.1f)

    var ch2_EN                 = MutableLiveData<Boolean>( false)
    var ch2_Carrier_Filename   = MutableLiveData<String> ( "03_HWave2")
    var ch2_Carrier_Fr         = MutableLiveData<Int>    ( 2000) //Частота несущей
    var ch2_AM_EN              = MutableLiveData<Boolean>( false)
    var ch2_AM_Filename        = MutableLiveData<String> ( "09_Ramp")
    var ch2_AM_Fr              = MutableLiveData<Float>  ( 8.7f)
    var ch2_FM_EN              = MutableLiveData<Boolean>( false)
    var ch2_FM_Filename        = MutableLiveData<String> ( "06_CHIRP")
    var ch2_FM_Base            = MutableLiveData<Int>    ( 2500) //Частота базы
    var ch2_FM_Dev             = MutableLiveData<Int>    ( 1100)//Частота базы
    var ch2_FM_Fr              = MutableLiveData<Float>  ( 5.1f)

    var itemlistCarrier: ArrayList<itemList> = ArrayList() //Создать список
    var itemlistAM     : ArrayList<itemList> = ArrayList() //Создать список
    var itemlistFM     : ArrayList<itemList> = ArrayList() //Создать список

    val onoffconfig  : ConfigOnOff = ConfigOnOff()
    val onoffconfig1 : ConfigOnOff = ConfigOnOff()

    val log       =  mutableStateListOf<LogMessage>()

    val colorline =  mutableStateListOf<List <pairTextAndColor>>()


    fun init()
    {

        val file = contextActivity!!.getExternalFilesDir("") //Создать если нет папку generator2
        contextActivity!!.getExternalFilesDir("/Carrier")
        contextActivity!!.getExternalFilesDir("/Mod")
        patchDocument = file!!.toString()
        patchCarrier  = "$patchDocument/Carrier/"
        patchMod      = "$patchDocument/Mod/"

        Utils.patchDocument = patchDocument
        Utils.patchCarrier  = patchCarrier
        Utils.patchMod      = patchMod

        try {
            AssetCopier(contextActivity) //.withFileScanning()
                .copy("Carrier", File(patchCarrier))
        }
        catch (e: IOException) {
            Log.d("Init",e.printStackTrace().toString())
        }

        try {
            AssetCopier(contextActivity) //.withFileScanning()
                .copy("Mod", File(patchMod))
        }
        catch (e: IOException) {
            Log.d("Init",e.printStackTrace().toString())
        }




    }


    fun observe()
    {


        ch1_EN.observeForever { ch1_EN ->
            Log.d("mySwitch", "onClick")
            PlaybackEngine.CH_EN(1, ch1_EN!!)
        }


    }

}