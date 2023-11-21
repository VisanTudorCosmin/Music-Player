import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.window.WindowDraggableArea
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import java.net.URL
import javax.sound.sampled.AudioSystem
import javax.sound.sampled.Clip

@Composable
@Preview
fun HomeView(closeWindow: () -> Unit) {
    MaterialTheme {
        var currentSong: SongData? by remember { mutableStateOf(null) }
        var player: Clip by remember { mutableStateOf(AudioSystem.getClip()) }
        var isRunning by remember { mutableStateOf(false) }

        Column(
            modifier = Modifier
//                .background(color = Color(30, 65, 122), shape = RoundedCornerShape(15.dp))
                .background(color = Color(12, 12, 12), shape = RoundedCornerShape(15.dp))
                .padding(15.dp)
        ) {
            Row(modifier = Modifier.padding(bottom = 10.dp).align(Alignment.End)) {
                WindowActions(closeWindow)
            }
            SongsList(songs, currentSong) { song ->
                val inputStream = AudioSystem.getAudioInputStream(URL("https://chimein.b-cdn.net/audio/Reflections-on-the-Water-CLI010501.wav"))
                player.stop()
                player.close()
                player.open(inputStream)
                player.start()
                currentSong = song
                isRunning = true
            }
            MusicPlayer({t -> if(t) {
                player.start()
                isRunning = true
            } else {
                player.stop()
                isRunning = false
            }}, isRunning)
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
//    val player = AudioSystem.getClip()
//    var inputStream = AudioSystem.getAudioInputStream(URL("https://chimein.b-cdn.net/audio/Mozart_Trio_from_Wind_Serenade_K388.wav"))
//    var inputStream2 = AudioSystem.getAudioInputStream(URL("https://chimein.b-cdn.net/audio/Bontempi-B3-A3-Chord.wav"))
////        https://chimein.b-cdn.net/audio/Bontempi-B3-A3-Chord.wav
//    player.open(inputStream)
//    player.start()

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