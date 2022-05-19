package com.example.generator2

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.net.DatagramPacket
import java.net.DatagramSocket


//Получаем строку
fun stringcalculate(text: String): List<pairTextAndColor> {
    var str = text
    val listPair = mutableListOf<pairTextAndColor>()
    //Ищем есть ли знак ESC в строке
    val indexESC = str.indexOf('\u001b')
    //Ничего не нашли
    if (indexESC == -1) {
        listPair.add(
            pairTextAndColor(
                text = str,
                colorText = currentTextColor,
                colorBg = currentBgColor
            )
        )
        return listPair
    } else {
        //Начало строки
        if (indexESC == 0) {
            do {
                // подстрока до первого указанного разделителя
                val strESC = str.substringBefore('m') // все что до m
                //Из последовательности обновляем текущие цвета //"38;05;232;48;05;226"
                calculateColorInEscString(strESC.substring(2)) //Без первых двух символов
                str = str.removePrefix(strESC + "m") //Удаляем префикс ESC
                val subsring = str.substringBefore('\u001b')
                listPair.add(
                    pairTextAndColor(
                        text = subsring,
                        colorText = currentTextColor,
                        colorBg = currentBgColor,
                        bold = currentTextBold,
                        italic = currentTextItalic,
                        underline = currentTextUnderline,
                        flash = currentTextFlash

                    )
                )
                str = str.removePrefix(subsring) //Удаляем из исходной строки эту подстроку
            } while (str.indexOf('\u001b') != -1)
        }
    }
    return listPair
}

//Из из последовательности обновляем текущие цвета
fun calculateColorInEscString(str: String) {

    //"38;05;232;48;05;226"
    println("--->calculateColorInString->str:$str")

    val rederxTextColor = """38;05;([^;]+)""".toRegex()
    val rederxBgColor = """48;05;([^;]+)""".toRegex()

    if (str == "0") {
        currentTextColor = defaultTextColor
        currentBgColor = defaultBgColor
        return
    }

    var str1 = str

    //Цвет текста
    var matchResult = rederxTextColor.find(str)
    if (matchResult != null) {
        val color = colorIn256(matchResult.groupValues[1].toInt()) //Получили цвет по коду
        currentTextColor = color
        println("--->calculateColorInString->codeColor:${color}")

        str1 = str.replace(matchResult.value, "")

    }

    //Цвет Фона
    matchResult = rederxBgColor.find(str1)
    if (matchResult != null) {
        val color = colorIn256(matchResult.groupValues[1].toInt()) //Получили цвет по коду
        currentBgColor = color
        println("--->calculateColorInString->currentBgColor:${color}")

        str1 = str1.replace(matchResult.value, "")
    }

    currentTextBold = 0
    currentTextItalic = 0
    currentTextUnderline = 0
    currentTextFlash = 0

    if (str1.indexOf("01") != -1) {
        //Bold
        currentTextBold = 1
    }

    if (str1.indexOf("03") != -1) {
        //Italic
        currentTextItalic = 1
    }

    if (str1.indexOf("04") != -1) {
        //Uderline
        currentTextUnderline = 1
    }

    if (str1.indexOf("07") != -1) {
        //Reverse
        val temp = currentBgColor
        currentBgColor = currentTextColor
        currentTextColor = temp
    }

    if (str1.indexOf("08") != -1) {
        //Flash
        currentTextFlash = 1
    }

    println(str1)

}

class udp_DataArrival : Runnable {

    public override fun run() {
        println("${Thread.currentThread()} Runnable Thread Started.")
        val buffer = ByteArray(2048 * 16)
        var socket: DatagramSocket? = null
        socket = DatagramSocket(8888)
        socket.broadcast = true
        val packet = DatagramPacket(buffer, buffer.size)
        while (true) {
            socket.receive(packet)
            var buffer1: ByteArray = packet.data.copyOfRange(0, packet.length)
            val string = String(buffer1)
            println("UDP:" + string)

            val delim = "\r\n"
            var list = string.split("\r\n")

            var mask = "\\[([^m]+)m".toRegex()
            var matchResult = mask.findAll(string).toList()

            for (str in list) {
                if (str == "") {
                    continue
                } else {
                    val linepair = stringcalculate(str) //Создаем список пар для одной строки
                    Global.colorline.add(linepair)
                    //Global.log.add(LogMessage(str))
                }
            }
        }
        socket?.close()
    }

}

@Composable
fun mainsreen2() {

    //Создаем список цветов из Json цветов
    colorJsonToList()

    //Запуск получения пакетов
    val threadWithRunnable = Thread(udp_DataArrival())
    threadWithRunnable.start()


    // A surface container using the 'background' color from the theme
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFF000000) //MaterialTheme.colors.background

    )
    {


        Column(modifier = Modifier.fillMaxHeight(), verticalArrangement = Arrangement.Top) {


            /*
            //Первая строка
            Row(
                Modifier
                    .height(72.dp)
                    .fillMaxWidth()
                    .border(width = 1.dp, color = Color.Yellow),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Box(
                    modifier = Modifier
                        .size(64.dp, 64.dp)
                        .background(Color.Blue)
                )
                {
                }
                UIspinner.Spinner(
                    "CH0",
                    "CR",
                    modifier = Modifier.padding(top = 0.dp, start = 16.dp, end = 0.dp)
                )
            }




            //Вторая строка
            Column(
                Modifier
                    .height(72.dp)
                    .fillMaxWidth()
                    .border(width = 1.dp, color = Color.Yellow),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val carrierFr by Global.ch1_Carrier_Fr.observeAsState()
                Text(text = "Несущая : ${carrierFr!!.format(2)} Hz", color = Color.White)
                Slider(
                    value = carrierFr!!,
                    onValueChange = { Global.ch1_Carrier_Fr.value = it },
                    Modifier.fillMaxWidth()
                )
            }


            //Третья строка
            Row(
                Modifier
                    .height(72.dp)
                    .fillMaxWidth()
                    .border(width = 1.dp, color = Color.Yellow),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Box(
                    modifier = Modifier
                        .size(64.dp, 64.dp)
                        .background(Color.Blue)
                )
                {
                }
                UIspinner.Spinner(
                    "CH0",
                    "CR",
                    modifier = Modifier.padding(top = 0.dp, start = 16.dp, end = 0.dp)
                )
            }




            //Четвертая строка
            Column(
                Modifier
                    .height(72.dp)
                    .fillMaxWidth()
                    .border(width = 1.dp, color = Color.Yellow),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val carrierFr by Global.ch1_Carrier_Fr.observeAsState()
                Text(text = "Несущая : ${carrierFr!!.format(2)} Hz", color = Color.White)
                Slider(
                    value = carrierFr!!,
                    onValueChange = { Global.ch1_Carrier_Fr.value = it },
                    Modifier.fillMaxWidth()
                )
            }



            //Пятая строка
            Row(
                Modifier
                    .height(72.dp)
                    .fillMaxWidth()
                    .border(width = 1.dp, color = Color.Yellow),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Box(
                    modifier = Modifier
                        .size(64.dp, 64.dp)
                        .background(Color.Blue)
                )
                {
                }
                UIspinner.Spinner(
                    "CH0",
                    "CR",
                    modifier = Modifier.padding(top = 0.dp, start = 16.dp, end = 0.dp)
                )
            }

            //Шестая строка
            Column(
                Modifier
                    .height(72.dp*5)
                    .fillMaxWidth()
                    .border(width = 1.dp, color = Color.Yellow),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val carrierFr by Global.ch1_Carrier_Fr.observeAsState()

                Text(text = "Несущая : ${carrierFr!!.format(2)} Hz", color = Color.White)
                Slider(
                    value = carrierFr!!,
                    onValueChange = { Global.ch1_Carrier_Fr.value = it },
                    Modifier.fillMaxWidth()
                )
                Slider(
                    value = carrierFr!!,
                    onValueChange = { Global.ch1_Carrier_Fr.value = it },
                    Modifier.fillMaxWidth()
                )

                Text(text = "Несущая : ${carrierFr!!.format(2)} Hz", color = Color.White)
                Slider(
                    value = carrierFr!!,
                    onValueChange = { Global.ch1_Carrier_Fr.value = it },
                    Modifier.fillMaxWidth()
                )
                Slider(
                    value = carrierFr!!,
                    onValueChange = { Global.ch1_Carrier_Fr.value = it },
                    Modifier.fillMaxWidth()
                )

                Text(text = "Несущая : ${carrierFr!!.format(2)} Hz", color = Color.White)
                Slider(
                    value = carrierFr!!,
                    onValueChange = { Global.ch1_Carrier_Fr.value = it },
                    Modifier.fillMaxWidth()
                )

            }

*/




            Button(onClick = {
                Global.log.add(LogMessage("----"))
            }) {

            }


            lazy(Global.colorline)

        }
    }
}

data class LogMessage(val text: String, val color: Color = Color.Black)


@Composable
fun lazy(messages: SnapshotStateList<List<pairTextAndColor>>) {

    var update by remember {
        mutableStateOf(false)
    }

    var isTimerRunning by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(key1 = true, key2 = isTimerRunning) {
        while (true) {
            print("-------->\n")
            delay(700L)
            update = !update
        }
    }


    var counter = remember {
        mutableStateOf(0)
    }

    val coroutineScope = rememberCoroutineScope()

    println("---lazy---")
    val lazyListState: LazyListState = rememberLazyListState()

    Button(
        onClick = {
            coroutineScope.launch {
                while (true) {
                    lazyListState.animateScrollToItem(index = messages.size - 1)
                }
            }
        }
    ) {}

    LazyColumn(modifier = Modifier.fillMaxWidth(), state = lazyListState) {
        itemsIndexed(messages)
        { index, l ->
            Row()
            {
                var s = l.size
                if (s > 0) {

                    val str: String = when (index) {
                        in 0..9 -> String.format("   %d>", index)
                        in 10..99 -> String.format("  %d>", index)
                        in 100..999 -> String.format(" %d>", index)
                        else -> String.format("%d>", index)
                    }

                    Text(
                        text = "$str",
                        color = Color.Gray,
                        fontFamily = FontFamily.Monospace
                    )

                }

                for (i in 0 until s) {
                    if (l[i].flash == 1) {
                        if (update) {
                            Text(
                                text = l[i].text,
                                color = l[i].colorText,
                                modifier = Modifier.background(l[i].colorBg),
                                textDecoration = if (l[i].underline == 1) TextDecoration.Underline else null,
                                fontWeight = if (l[i].bold == 1) FontWeight.Bold else null,
                                fontStyle = if (l[i].italic == 1) FontStyle.Italic else null,
                                fontFamily = FontFamily.Monospace
                            )
                        }
                    } else {
                        Text(
                            text = l[i].text,
                            color = l[i].colorText,
                            modifier = Modifier.background(l[i].colorBg),
                            textDecoration = if (l[i].underline == 1) TextDecoration.Underline else null,
                            fontWeight = if (l[i].bold == 1) FontWeight.Bold else null,
                            fontStyle = if (l[i].italic == 1) FontStyle.Italic else null,
                            fontFamily = FontFamily.Monospace
                        )
                    }
                }
            }
        }
    }
}





