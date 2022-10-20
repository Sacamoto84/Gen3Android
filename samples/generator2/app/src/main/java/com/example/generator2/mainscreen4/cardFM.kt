import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Slider
import androidx.compose.material.SliderDefaults
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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

@Composable
fun CardFM() {

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
                    .background(Color(0xFF4CB050))
                    .height(30.dp)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            )
            {
                Text("FM")
            }

            val carrierFr by Global.ch1_Carrier_Fr.observeAsState()

            Row(
                Modifier.padding(top = 8.dp).height(48.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Switch(checked = true, onCheckedChange = {})

                val str = String.format("%.1f Hz", carrierFr)
                //MainscreenTextBox(str, modifier = Modifier.fillMaxHeight().fillMaxWidth().weight(1f))

                Text(
                    text = "",
                    color = Color.LightGray,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.fillMaxWidth().weight(1f)
                )

                UIspinner.Spinner(
                    "CH0",
                    "FM",
                    modifier = Modifier
                        .padding(top = 0.dp, start = 8.dp, end = 8.dp)
                        .wrapContentWidth()
                        //.fillMaxWidth()
                        .clip(shape = RoundedCornerShape(4.dp)).background(Color.Black)
                )

            }

            val str = String.format("%.1f Hz", carrierFr)

///////////////////////////
            Row(
                Modifier.padding(top = 8.dp, start = 8.dp, end= 8.dp).height(48.dp),
                verticalAlignment = Alignment.CenterVertically
            )
            {
                Text(
                    text = "Частота модуляции",
                    color = Color.LightGray,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.fillMaxWidth().weight(1f)
                )

                MainscreenTextBox(str, Modifier.height(48.dp).width(160.dp))


            }
            Slider(
                value = carrierFr!!,
                onValueChange = { Global.ch1_Carrier_Fr.value = it },
                Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, end = 8.dp),
                colors = SliderDefaults.colors(thumbColor = Color.LightGray)
            )
/////////////////////////


            Row(
                Modifier.padding(top = 8.dp, start = 8.dp, end= 8.dp).height(48.dp),
                verticalAlignment = Alignment.CenterVertically
            )
            {
                Text(
                    text = "База",
                    color = Color.LightGray,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.fillMaxWidth().weight(1f)
                )

                MainscreenTextBox(str, Modifier.height(48.dp).width(160.dp))


            }
            Slider(
                value = carrierFr!!,
                onValueChange = { Global.ch1_Carrier_Fr.value = it },
                Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, end = 8.dp),
                colors = SliderDefaults.colors(thumbColor = Color.LightGray)
            )
////////
            Row(
                Modifier.padding(top = 8.dp, start = 8.dp, end= 8.dp).height(48.dp),
                verticalAlignment = Alignment.CenterVertically
            )
            {
                Text(
                    text = "Девиация",
                    color = Color.LightGray,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.fillMaxWidth().weight(1f)
                )

                MainscreenTextBox(str, Modifier.height(48.dp).width(160.dp))


            }
            Slider(
                value = carrierFr!!,
                onValueChange = { Global.ch1_Carrier_Fr.value = it },
                Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, end = 8.dp),
                colors = SliderDefaults.colors(thumbColor = Color.LightGray)
            )








        }
    }






}