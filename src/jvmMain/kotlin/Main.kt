import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.window.WindowDraggableArea
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SkipNext
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.material.icons.outlined.SkipNext
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.window.*
import com.seiko.imageloader.rememberImagePainter
import java.math.RoundingMode
import java.text.DecimalFormat

@Composable
@Preview
fun HomeView(closeWindow: () -> Unit) {
    MaterialTheme {
        Column(
            modifier = Modifier
//                .background(color = Color(30, 65, 122), shape = RoundedCornerShape(15.dp))
                .background(color = Color(12, 12, 12), shape = RoundedCornerShape(15.dp))
                .padding(15.dp)
        ) {
            Row(modifier = Modifier.padding(bottom = 10.dp).align(Alignment.End)) {
                WindowActions(closeWindow)
            }
            Text(
                "Music Player", color = Color.White, fontWeight = FontWeight.Bold, modifier = Modifier
                    .padding(bottom = 5.dp)
            )
            Text(
                "This will be the music player application",
                color = Color.White,
                modifier = Modifier.padding(bottom = 10.dp)
            )
            Icon(Icons.Rounded.ShoppingCart, contentDescription = null, tint = Color.White)
            Icon(Icons.Default.SkipNext, contentDescription = null, tint = Color.White)
//            val painter = rememberImagePainter("https://i.scdn.co/image/ab67656300005f1ff8141e891abf749375772343")
            val painter =
                rememberImagePainter("https://miro.medium.com/v2/resize:fit:1100/format:webp/1*4BTBxtkO0yp7o1sRIaSIAQ.jpeg")
            Image(
                painter = painter,
                contentDescription = "image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(width = 120.dp, height = 120.dp)
//                    .padding(10.dp)
                    .shadow(
                        elevation = 15.dp,
                        spotColor = Color.Black,
                        ambientColor = Color.Black,
                        shape = RoundedCornerShape(10.dp),
                        clip = true
                    )
//                    .clip(shape = RoundedCornerShape(10.dp))
            )
            SongsList(songs)
        }
    }
}

@Composable
fun WindowActions(closeWindow: () -> Unit) {
    Button(
        onClick = {},
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Green),
        modifier = Modifier
            .clip(shape = CircleShape)
            .padding(3.dp)
            .size(10.dp)
    ) {}
    Button(
        onClick = {},
        colors = ButtonDefaults.buttonColors(backgroundColor = Color(255, 165, 0)),
        modifier = Modifier
            .clip(shape = CircleShape)
            .padding(3.dp)
            .size(10.dp)
    ) {}
    Button(
        onClick = { closeWindow() },
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red),
        modifier = Modifier
            .clip(shape = CircleShape)
            .padding(3.dp)
            .size(10.dp)
    ) {}
}

fun formatDuration(duration: Int): String {
    val minutes = duration / 60
    val seconds = duration - minutes * 60
    return "$minutes:$seconds"
}

fun main() = application {
    var isOpen by remember { mutableStateOf(true) }
//    val state = rememberWindowState(placement = WindowPlacement.Maximized)
    if (isOpen) {
        Window(
            state = rememberWindowState(
                position = WindowPosition(Alignment.Center)
            ),
            undecorated = true,
            transparent = true,
            onCloseRequest = ::exitApplication
        ) {
            WindowDraggableArea {
                HomeView { isOpen = false }
            }
        }
    }
}