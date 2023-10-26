import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.onPointerEvent
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.seiko.imageloader.rememberImagePainter

data class SongData(val name: String, val singer: String, val duration: Int, val picture: String, val album: String)

val songs = arrayOf(
    SongData(
        "Sky Full Of Song",
        "Florence, The Machine",
        226,
        picture = "https://i.scdn.co/image/ab67616d00004851f9bd7a6c772ac496015be3f8",
        album = "One Of The Boys"
    ),
    SongData(
        "I Said Hi",
        "Amy Shark",
        168,
        picture = "https://i.scdn.co/image/ab67616d00004851f049fc9322f0c93646cdcc77",
        album = "One Of The Boys"
    ),
    SongData(
        "Poker Face",
        "Lady Gaga",
        237,
        picture = "https://i.scdn.co/image/ab67616d0000485118aa5d7e6d484b832cd5d03f",
        album = "One Of The Boys"
    ),
    SongData(
        "Temperature",
        "Sean Paul",
        218,
        picture = "https://i.scdn.co/image/ab67616d0000485169ba684e533706bafe248ef3",
        album = "The Trinity"
    ),
    SongData(
        "Say It Right",
        "Nelly Furtado",
        223,
        picture = "https://i.scdn.co/image/ab67616d00004851a6f439c8957170652f9410e2",
        album = "Loose"
    )
)

@Composable
fun SongsList(songs: Array<SongData>) {
    Column(modifier = Modifier.padding(vertical = 10.dp).height(200.dp).verticalScroll(rememberScrollState())) {
        songs.mapIndexed { n, it -> SongItem(n, it.name, it.singer, it.duration, it.picture, it.album) }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SongItem(index: Int, name: String, singer: String, duration: Int, picture: String, album: String) {
    val painter = rememberImagePainter(picture)
    var active by remember { mutableStateOf(false) }
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .clip(shape = RoundedCornerShape(5.dp))
            .background(color = if (active) Color(22, 22, 22) else Color.Transparent)
            .padding(7.dp)
            .onPointerEvent(PointerEventType.Enter) { active = true }
            .onPointerEvent(PointerEventType.Exit) { active = false }

    ) {
        Text(
            text = "#$index",
            color = Color.Gray,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.End,
            modifier = Modifier.padding(end = 15.dp)
        )
        Image(
            painter = painter, contentDescription = "image", modifier = Modifier
                .width(43.dp).height(43.dp)
//                .padding(5.dp)
                .clip(shape = RoundedCornerShape(4.dp))
        )
        Column(modifier = Modifier.padding(start = 8.dp).weight(1f)) {
            Text(text = name, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 14.sp)
            Text(text = singer, color = Color.Gray, fontWeight = FontWeight.SemiBold, fontSize = 13.sp)
        }
        Text(
            text = album,
            color = Color.Gray,
            fontWeight = FontWeight.SemiBold,
            fontSize = 13.sp,
            modifier = Modifier.weight(1f)
        )
        Spacer(modifier = Modifier.weight(1.0f, fill = true))
        Text(
            text = formatDuration(duration),
            color = Color.Gray,
            fontWeight = FontWeight.SemiBold,
            fontSize = 13.sp
        )
    }
}