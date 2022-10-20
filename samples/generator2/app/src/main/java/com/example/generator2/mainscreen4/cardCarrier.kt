import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Slider
import androidx.compose.material.SliderDefaults
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.generator2.Global
import com.example.generator2.ui.wiget.UIspinner
import kotlin.properties.Delegates


@Composable
fun CardCarrier(str: String = "CH0") {

    Card(

        backgroundColor = Color(0xFF2A2D36), modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 16.dp),
        elevation = 5.dp
    )
    {
        Column()
        {
            Box(
                modifier = Modifier
                    .background(if (str == "CH0") Color(0xFF4CB050) else Color(0xFFC2AA10))
                    .height(30.dp)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            )
            {
                Text(str)
            }

            val carrierFr: State<Float?> = if (str == "CH0") {
                Global.ch1_Carrier_Fr.observeAsState()
            } else {
                Global.ch2_Carrier_Fr.observeAsState()
            }

            Row(
                Modifier.padding(top = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                val chEN: State<Boolean?> = if (str == "CH0") {
                    Global.ch1_EN.observeAsState()
                } else {
                    Global.ch2_EN.observeAsState()
                }

                Switch(
                    checked = chEN.value!!,
                    onCheckedChange = {
                        if (str == "CH0") Global.ch1_EN.value = it else Global.ch2_EN.value = it
                    })

                MainscreenTextBox(
                    str = String.format("%.1f Hz", carrierFr.value),
                    Modifier
                        .height(48.dp)
                        .fillMaxWidth()
                        .weight(1f)
                )

                UIspinner.Spinner(
                    CH = str,
                    Mod = "CR",
                    modifier = Modifier
                        .padding(top = 0.dp, start = 8.dp, end = 8.dp)
                        .wrapContentWidth()
                        .clip(shape = RoundedCornerShape(4.dp))
                )

            }

            Slider(
                valueRange = 300f..5000f,
                value = carrierFr.value!!,
                onValueChange = {
                    if (str == "CH0") Global.ch1_Carrier_Fr.value =
                        it else Global.ch2_Carrier_Fr.value = it
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, end = 8.dp),
                colors = SliderDefaults.colors(thumbColor = Color.LightGray)
            )


        }
    }
}