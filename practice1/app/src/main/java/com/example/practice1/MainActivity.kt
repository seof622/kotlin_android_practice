package com.example.practice1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.practice1.ui.theme.Practice1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Practice1Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GreetingImage("Android")
                }
            }
        }
    }
}

@Composable
fun GreetingImage(name: String, modifier: Modifier = Modifier) {
    val image = painterResource(id = R.drawable.bg_compose_background)
    Column (
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
    ){
        Image(
            painter = image,
            contentDescription = null,
            contentScale = ContentScale.FillWidth
        )
        Box {
            Text(
                text = stringResource(R.string.jetpack_compose_tutorial),
                fontSize = 24.sp,
                modifier = Modifier
                    .padding(16.dp)
            )
        }
        GreetingText(
            text = stringResource(R.string.Intro_compose_1),
            padding = PaddingValues(start = 16.dp, end = 16.dp)
        )
        GreetingText(
            text = stringResource(R.string.intro_compose_2),
            padding = PaddingValues(16.dp)
        )
    }
}

@Composable
fun GreetingText(text: String, padding: PaddingValues, modifier: Modifier = Modifier) {
    Box {
        Text(
            text = text,
            textAlign = TextAlign.Justify,
            modifier = Modifier
                .padding(padding),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Practice1Theme {
        GreetingImage("Android")
    }
}