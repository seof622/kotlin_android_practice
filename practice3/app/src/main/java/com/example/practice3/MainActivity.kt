package com.example.practice3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.practice3.ui.theme.Practice3Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Practice3Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting()
                }
            }
        }
    }
}

@Composable
fun Greeting(modifier: Modifier = Modifier) {
    // Row()안의 Modifier.weight는 세로에 대한 비율
    // ComposeQuadrant안의 Modifier.weight는 가로에 대한 비율
    Column (Modifier.fillMaxWidth()) {
        Row(Modifier.weight(0.5f)) {
            ComposeQuadrant(
                title = stringResource(R.string.text_composable_title),
                component = stringResource(R.string.text_composable_component),
                color = Color(0xFFEADDFF),
                modifier = Modifier.weight(0.5f)
            )
            ComposeQuadrant(
                title = stringResource(R.string.image_composable_title),
                component = stringResource(R.string.image_composable_component),
                color = Color(0xFFD0BCFF),
                modifier = Modifier.weight(0.5f)
            )
        }
        Row(Modifier.weight(0.5f)) {
            // Modifier.weight는 Row의 하위 요소들의 비율을 결정함
            ComposeQuadrant(
                title = stringResource(R.string.row_composable_title),
                component = stringResource(R.string.row_composable_component),
                color = Color(0xFFB69DF8),
                modifier = Modifier.weight(0.5f)
            )
            ComposeQuadrant(
                title = stringResource(R.string.column_composable_title),
                component = stringResource(R.string.column_composable_component),
                color = Color(0xFFF6EDFF),
                modifier = Modifier.weight(0.5f)
            )
        }
    }
}


@Composable
fun ComposeQuadrant(title: String, component: String, color: Color, modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize() // 세로 화면을 꽉 채우도록 함
            .background(color = color) // Column box의 배경을 설정
            .padding(16.dp)
    ) {
        Text(
            text = title,
            modifier = Modifier
                .padding(bottom = 16.dp),
            fontWeight = FontWeight.Bold
        )
        Text(
            text = component,
            textAlign = TextAlign.Justify
            // 텍스트 줄 바꿈을 Box 크기에 맞춰서 정렬되도록 함
            // 즉, 단어의 시작 부분과 끝 부분이 일치하도록 정렬시킴
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Practice3Theme {
        Greeting()
    }
}