package com.org.photoplay.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.org.photoplay.domain.model.Movie

@Composable
fun MovieItem(movie: Movie, onClickListener: (Movie) -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.clickable { onClickListener(movie) }) {
        CoilImage(
            url = "https://image.tmdb.org/t/p/w500${movie.poster_path}",
            modifier = Modifier
                .height(89.dp)
                .width(140.dp),
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(movie.title)
    }
}

@Composable
fun CoilImage(modifier: Modifier = Modifier, url: String) {
    AsyncImage(
        modifier = modifier,
        model = ImageRequest.Builder(LocalContext.current).data(url).crossfade(true).build(),
        contentDescription = null,
        contentScale = ContentScale.Crop
    )
}

@Composable
fun CastItem(modifier: Modifier = Modifier, url: String, name: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        CoilImage(
            modifier = modifier
                .height(121.dp)
                .width(96.dp),
            url = "https://image.tmdb.org/t/p/w500${url}"
        )
        Text(name, modifier = Modifier.padding(top = 6.dp), fontSize = 12.sp)
    }
}

@Preview(showBackground = true)
@Composable
private fun MovieItemPrev() {

}