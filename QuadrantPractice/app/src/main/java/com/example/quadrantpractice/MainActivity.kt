package com.example.quadrantpractice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
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
import com.example.quadrantpractice.ui.theme.QuadrantPracticeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuadrantPracticeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    QuadrantUI()
                }
            }
        }
    }
}

@Composable
fun QuadrantUI(modifier: Modifier = Modifier) {
    Column {
        Row(
            modifier = Modifier.weight(0.5f)
        ) {
            ComposableFunctionInfo(
                title = stringResource(R.string.text_composable_title),
                description = stringResource(R.string.text_composable_desc),
                colorCode = 0xFFEADDFF,
                modifier = Modifier.weight(0.5f)
            )
            ComposableFunctionInfo(
                title = stringResource(R.string.image_composable_title),
                description = stringResource(R.string.image_composable_desc),
                colorCode = 0xFFD0BCFF,
                modifier = Modifier.weight(0.5f)
            )
        }
        Row(
            modifier = Modifier.weight(0.5f)
        ) {
            ComposableFunctionInfo(
                title = stringResource(R.string.row_composable_title),
                description = stringResource(R.string.row_composable_desc),
                colorCode = 0xFFB69DF8,
                modifier = Modifier.weight(0.5f)
            )
            ComposableFunctionInfo(
                title = stringResource(R.string.column_composable_title),
                description = stringResource(R.string.column_composable_desc),
                colorCode = 0xFFF6EDFF,
                modifier = Modifier.weight(0.5f)
            )
        }
    }
}

@Composable
fun ComposableFunctionInfo(title: String, description: String,
                           colorCode: Long, modifier : Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxHeight()
            .background(color = Color(colorCode))
            .padding(16.dp)
    ) {
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Text(
            text = description,
            textAlign = TextAlign.Justify
        )
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    QuadrantPracticeTheme {
        QuadrantUI()
    }
}