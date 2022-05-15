package com.example.generator2.ui.wiget

import android.graphics.Bitmap
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.input.pointer.consumeAllChanges
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.*
import kotlin.math.PI
import kotlin.math.acos
import kotlin.math.roundToInt
import kotlin.math.sqrt

@Composable
fun InfinitiSlider(
    modifier: Modifier,

    image: ImageBitmap? = null,  //Фоновая картинка, неподвижная

    sensing: Float = 1.0f, //Чувствительность

    rangeAngle: Float = 1000f,
    value: Float? = null, //Вывод icrementalAngle от 0 до rangeAngle при rangeAngle != 0, и от +- Float при rangeAngle = 0
    onValueChange: (Float) -> Unit
) {

    val onValueChangeState = rememberUpdatedState(onValueChange)
    val ValueRemember = rememberUpdatedState(value)

    var icrementalAngle by remember { mutableStateOf(0f) }

    if (value != null) {
        icrementalAngle = ValueRemember.value!!
    }

    var lastAngle = 0f //При старте устанавливаем стартовый угол

    var offX by remember { mutableStateOf(0f) }    //Смещени, координаты текущего начала Box

    Box(
        modifier = Modifier
            .then(modifier)
            .fillMaxSize()
            .pointerInput(Unit) {
                detectDragGestures(
                    onDragStart =
                    {
                    },
                    onDrag =
                    { change, dragAmount ->

                        change.consumeAllChanges()
                        //offX += dragAmount.x

                        icrementalAngle += dragAmount.x * sensing

                        if (icrementalAngle > rangeAngle) icrementalAngle = rangeAngle
                        if (icrementalAngle < 0f) icrementalAngle = 0f

                        onValueChangeState.value.invoke(icrementalAngle)

                    },
                    onDragEnd = {
                        offX = 0f
                    },
                    onDragCancel = {
                        offX = 0f
                    }
                )
            },
        contentAlignment = Alignment.Center
    )
    {

        if (image != null) {
            Image(image, contentDescription = null, modifier = Modifier.fillMaxSize(), contentScale = ContentScale.FillBounds)
        }

        Text(text = "${icrementalAngle.format(2)}", color = Color.White)


    }


}

fun Float.format(digits: Int) = "%.${digits}f".format(this)