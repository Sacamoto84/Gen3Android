import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.generator2.Global
import com.example.generator2.ui.wiget.InfinitySlider
import com.example.generator2.ui.wiget.UIspinner

@Composable
fun CardFM(str: String = "CH0") {

    val fmEN: State<Boolean?> = if (str == "CH0") {
        Global.ch1_FM_EN.observeAsState()
    } else {
        Global.ch2_FM_EN.observeAsState()
    }

    val fmFr: State<Float?> = if (str == "CH0") {
        Global.ch1_FM_Fr.observeAsState()
    } else {
        Global.ch2_FM_Fr.observeAsState()
    }

    val fmBase: State<Float?> = if (str == "CH0") {
        Global.ch1_FM_Base.observeAsState()
    } else {
        Global.ch2_FM_Base.observeAsState()
    }

    val fmDev: State<Float?> = if (str == "CH0") {
        Global.ch1_FM_Dev.observeAsState()
    } else {
        Global.ch2_FM_Dev.observeAsState()
    }


//    Card(
//
//        backgroundColor = Color(0xFF2A2D36),
//        modifier = Modifier
//            .wrapContentHeight()
//            .fillMaxWidth()
//            .padding(start = 16.dp, end = 16.dp, top = 16.dp),
//        elevation = 5.dp
//    )
//    {
    Column()
    {
        Box(
            modifier = Modifier
                .background(if (str == "CH0") colorGreen else colorOrange)
                .height(8.dp)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        )
        {
            //Text("FM")
        }

        Row(
            Modifier
                .padding(top = 8.dp)
                .height(48.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {


            Switch(
                modifier = Modifier.width(ms4SwitchWidth),
                checked = fmEN.value!!, onCheckedChange = {
                    if (str == "CH0") Global.ch1_FM_EN.value = it else Global.ch2_FM_EN.value = it
                })

            MainscreenTextBox(
                String.format("%.1f", fmFr.value),
                modifier = Modifier
                    .padding(start = 8.dp)
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .weight(1f)
            )

            InfinitySlider(
                value = fmFr.value,
                sensing = if (fmFr.value!! < 10.0F) sensetingSliderAmFm else sensetingSliderAmFm * 10f,
                range = rangeSliderAmFm,
                onValueChange = {
                    if (str == "CH0") Global.ch1_FM_Fr.value =
                        it else Global.ch2_FM_Fr.value = it
                },
                modifier = modifierInfinitySlider,
                vertical = true,
                invert = true,
                visibleText = false
            )


            UIspinner.Spinner(
                str,
                "FM",
                modifier = Modifier
                    .padding(top = 0.dp, start = 8.dp, end = 8.dp)
                    .wrapContentWidth()
                    //.fillMaxWidth()
                    .clip(shape = RoundedCornerShape(4.dp))
                    .background(Color.Black)
            )

        }

//        Slider(
//            valueRange = 0.1f..100f,
//            value = fmFr.value!!,
//            onValueChange = {
//                if (str == "CH0") Global.ch1_FM_Fr.value =
//                    it else Global.ch2_FM_Fr.value = it
//            },
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(start = 8.dp, end = 8.dp),
//            colors = SliderDefaults.colors(thumbColor = Color.LightGray)
//        )
/////////////////////////


        //???????? 
        Row(
            Modifier
                .padding(top = 8.dp, start = 0.dp, end = 8.dp)
                .height(48.dp),
            verticalAlignment = Alignment.CenterVertically
        )
        {

            Box(
                modifier = Modifier
                    .padding(start=4.dp)
                    .height(48.dp)
                    .width(ms4SwitchWidth)
                    //.border(0.dp, Color.White, RoundedCornerShape(8.dp))
                    .clip(RoundedCornerShape(8.dp))
                    .background(colorDarkBackground)
                    .clickable { }
                , contentAlignment = Alignment.Center
            ) {
                Text("??????????????????", fontSize = 12.sp, color = Color.LightGray )
            }






            MainscreenTextBoxPlus2Line(
                String.format("%d", fmBase.value!!.toInt()),
                String.format("%d", fmBase.value!!.toInt() + fmDev.value!!.toInt()),
                String.format("%d", fmBase.value!!.toInt() - fmDev.value!!.toInt()),
                Modifier.padding(start=4.dp)
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .weight(1f)
            )

            InfinitySlider(
                value = fmBase.value,
                sensing = sensetingSliderFmBase * 8,
                range = rangeSliderFmBase,
                onValueChange = {
                    if (str == "CH0") Global.ch1_FM_Base.value =
                        it else Global.ch2_FM_Base.value = it
                },
                modifier = modifierInfinitySlider,
                vertical = true,
                invert = true,
                visibleText = false
            )


            InfinitySlider(
                value = fmBase.value,
                sensing = sensetingSliderFmBase,
                range = rangeSliderFmBase,
                onValueChange = {
                    if (str == "CH0") Global.ch1_FM_Base.value =
                        it else Global.ch2_FM_Base.value = it
                },
                modifier = modifierInfinitySlider,
                vertical = true,
                invert = true,
                visibleText = false
            )


        }

////////
        Row(
            Modifier
                .padding(top = 8.dp, start = 0.dp, end = 8.dp)
                .height(48.dp),
            verticalAlignment = Alignment.CenterVertically
        )
        {
//            Text(
//                text = "  ",
//                color = Color.LightGray,
//                fontSize = 18.sp,
//                fontWeight = FontWeight.Bold,
//                modifier = Modifier
//                    .width(ms4SwitchWidth)
//
//                    //.weight(1f)
//            )


            Box(
                modifier = Modifier
                    .padding(start=4.dp)
                    .height(48.dp)
                    .width(ms4SwitchWidth)
                    //.border(1.dp, Color.White, RoundedCornerShape(8.dp))
                    .clip(RoundedCornerShape(8.dp))
                    .background(colorDarkBackground)
                    .clickable { }
                , contentAlignment = Alignment.Center
            ) {
                Text("??????????????????", fontSize = 12.sp, color = Color.LightGray )
            }

            MainscreenTextBox(
                String.format("?? %d", fmDev.value!!.toInt()),
                Modifier
                    .padding(start=4.dp)
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .weight(1f)
            )


            InfinitySlider(
                value = fmDev.value,
                sensing = sensetingSliderFmDev * 8,
                range = rangeSliderFmDev,
                onValueChange = {
                    if (str == "CH0") Global.ch1_FM_Dev.value =
                        it else Global.ch2_FM_Dev.value = it
                },
                modifier = modifierInfinitySlider,
                vertical = true,
                invert = true,
                visibleText = false
            )

            InfinitySlider(
                value = fmDev.value,
                sensing = sensetingSliderFmDev,
                range = rangeSliderFmDev,
                onValueChange = {
                    if (str == "CH0") Global.ch1_FM_Dev.value =
                        it else Global.ch2_FM_Dev.value = it
                },
                modifier = modifierInfinitySlider,
                vertical = true,
                invert = true,
                visibleText = false
            )


        }


        Spacer(modifier = Modifier.height(8.dp))
//        Slider(
//            valueRange = rangeSliderFmDev,
//            value = fmDev.value!!,
//            onValueChange = {
//                if (str == "CH0") Global.ch1_FM_Dev.value =
//                    it else Global.ch2_FM_Dev.value = it
//            },
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(start = 8.dp, end = 8.dp),
//            colors = SliderDefaults.colors(thumbColor = Color.LightGray)
//        )


        //}
    }


}