package com.example.artspace

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toComposeRect
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.window.layout.WindowMetricsCalculator
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                // A surface container using the 'background' color from the theme
                Surface {
                    ArtSpaceApp()
                }
            }
        }
    }
}

@Composable
fun ArtSpaceApp(modifier: Modifier = Modifier) {
    var artProductCount by remember { mutableStateOf(2) }

    var painterResourceID = when (artProductCount) {
        1 -> R.drawable.final_dish
        2 -> R.drawable.gogh
        else -> R.drawable.monariza
    }

    var artTitle = when (artProductCount) {
        1 -> stringResource(R.string.first_art_title)
        2 -> stringResource(R.string.second_art_title)
        else -> stringResource(R.string.third_art_title)
    }

    var artist = when (artProductCount) {
        1 -> stringResource(R.string.first_artist)
        2 -> stringResource(R.string.second_artist)
        else -> stringResource(R.string.third_artist)
    }

    var yearOfArt = when (artProductCount) {
        1 -> stringResource(R.string.first_art_year)
        2 -> stringResource(R.string.second_art_year)
        else -> stringResource(R.string.third_art_year)
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .weight(0.7f)
        ) {
            Surface(
                shadowElevation = 30.dp,
                modifier = Modifier
                    .padding(50.dp)
            ) {
                Image(
                    painter = painterResource(id = painterResourceID),
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier
                        .width(300.dp)
                        .padding(30.dp)
                )
            }
        }
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .weight(0.2f)
                .width(300.dp)
        ) {
            TextArtProduct(
                title = artTitle,
                artist = artist,
                year = yearOfArt
            )
        }
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .weight(0.1f)
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        ) {
            ChangeArtSpaceButton(
                onClick = {
                    artProductCount++
                    if (artProductCount > 3) {
                        artProductCount = 1
                    }
                },
                buttonText = "Previous"
            )
            ChangeArtSpaceButton(
                onClick = {
                    artProductCount--
                    if (artProductCount < 1) {
                        artProductCount = 3
                    }
                },
                buttonText = "Next"
            )
        }
    }
}

@Composable
fun TextArtProduct(
    title: String,
    artist: String,
    year: String,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(color = Color.LightGray)
            .padding(16.dp)
    ) {
        Text(
            text = title,
            fontSize = 24.sp
        )
        Text(
            buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                ) {
                    append(artist)
                }
                append("($year)")
            }
        )
    }
}

@Composable
fun ChangeArtSpaceButton(
    onClick: () -> Unit,
    buttonText: String,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(60.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Blue,
            contentColor = Color.Blue
        ),
        modifier = Modifier.width(128.dp)
    ) {
        Text(
            text = buttonText,
            color = Color.White
        )
    }
}

/*
enum class WindowSize { Compact, Medium, Expanded }

@Composable
fun Activity.rememberWindowSize(): Size {
    val configuration = LocalConfiguration.current

    val windowMetrics = remember(configuration) {
        WindowMetricsCalculator.getOrCreate()
            .computeCurrentWindowMetrics(this)
    }
    return windowMetrics.bounds.toComposeRect().size
}

@Composable
fun Activity.rememberWindowSizeClass(): WindowSize {
    val windowSize = rememberWindowSize()

    val windowDpSize = with(LocalDensity.current) {
        windowSize.toDpSize()
    }

    return getWindowSizeClass(windowDpSize)
}

fun getWindowSizeClass(windowDpSize: DpSize): WindowSize = when {
    windowDpSize.width < 0.dp -> throw IllegalArgumentException("Dp value cannot be negative")
    windowDpSize.width < 600.dp -> WindowSize.Compact
    windowDpSize.width < 840.dp -> WindowSize.Medium
    else -> WindowSize.Expanded
}

위 코드를 활용하여 크기에 따른 화면 레이아웃을 설정할 수 있음
이 프로젝트에서는 적용하지 않았음
 */


@Preview(showBackground = true)
@Composable
fun previewArtSpace(modifier: Modifier = Modifier) {
    Surface {
        ArtSpaceApp()
    }
}
