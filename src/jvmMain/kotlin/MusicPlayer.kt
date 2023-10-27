import androidx.compose.foundation.layout.Row
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import javax.sound.sampled.Clip

@Composable
fun MusicPlayer(audioPlayer: Clip) {
    Row {
       Button(onClick = {audioPlayer.stop()})  {
           Text("Stop")
       }
       Button(onClick = {audioPlayer.start()})  {
           Text("Play")
       }
    }
}