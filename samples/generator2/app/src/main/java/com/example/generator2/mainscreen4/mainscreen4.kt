import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.Surface
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState


@OptIn(ExperimentalMaterialApi::class, ExperimentalPagerApi::class)
@Composable
fun mainsreen4() {

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = colorDarkBackground

    )
    {

        val tabItems = listOf<String>("CH0", "CH1", "Setting")
        val tabIndex = remember { mutableStateOf(0) }

        Column(Modifier.verticalScroll(rememberScrollState())) {

            TabRow(
                selectedTabIndex = tabIndex.value,
                backgroundColor = Color(0xFF37A83C),
                indicator = {}

            ) {
                tabItems.forEachIndexed { index, item ->
                    Tab(
                        modifier = Modifier.background(if (tabIndex.value == index) colorLightBackground else Color.Transparent),
                        selected = tabIndex.value == index,
                        onClick = { tabIndex.value = index },
                        selectedContentColor = Color.White,
                        unselectedContentColor = Color.White,
                        text = {
                            Text(text = item, fontSize = 18.sp)
                        }
                    )
                }
            }


            CardCarrier("CH0")
            //CardAM("CH0")
            //CardFM("CH0")
            CardCarrier("CH1")
            //CardAM("CH1")
            //CardFM("CH1")
        }




    }

}

