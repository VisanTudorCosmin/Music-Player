import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import javax.sound.sampled.Clip

@Composable
fun MusicPlayer(playClip: (Boolean) -> Unit, isPlaying: Boolean) {
    Column {
        Slider(
            value = 1.25f, onValueChange = { },
            valueRange = 0f..3.25f,
            colors = SliderDefaults.colors(
                thumbColor = Color.LightGray,
                activeTrackColor = Color.LightGray,
                inactiveTrackColor = Color.DarkGray
            ),
            modifier = Modifier.fillMaxWidth()
        )
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(
                Icons.Rounded.Shuffle,
                contentDescription = "",
                tint = Color.LightGray,
                modifier = Modifier.padding(horizontal = 10.dp).size(20.dp)
            )
            Icon(
                Icons.Rounded.SkipPrevious,
                contentDescription = "",
                tint = Color.LightGray,
                modifier = Modifier.padding(horizontal = 10.dp).size(30.dp)
            )

            Button(onClick = {
                if(isPlaying) {
                    playClip(false)
                } else {
                    playClip(true)
                }
            }, contentPadding = PaddingValues(0.dp), elevation = null, colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent)) {
                Icon(
                    if(isPlaying) Icons.Rounded.PauseCircleFilled else Icons.Rounded.PlayCircleFilled ,
                    contentDescription = "",
                    tint = Color.LightGray,
                    modifier = Modifier.padding(horizontal = 10.dp).size(50.dp)
                )
            }
            Icon(
                Icons.Rounded.SkipNext,
                contentDescription = "",
                tint = Color.LightGray,
                modifier = Modifier.padding(horizontal = 10.dp).size(30.dp)
            )
            Icon(
                Icons.Rounded.Repeat,
                contentDescription = "",
                tint = Color.LightGray,
                modifier = Modifier.padding(horizontal = 10.dp).size(20.dp)
            )
        }
    }
}