import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MainscreenTextBox(str: String, modifier : Modifier = Modifier)
{
    Box(
        Modifier
            //.height(48.dp)
            //.fillMaxWidth()
            .then(modifier)
            .clip(shape = RoundedCornerShape(4.dp))
            .background(Color(0xFF13161B)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = str,
            color = Color.LightGray,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
    }
}