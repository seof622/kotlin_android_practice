package com.example.businesscard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountBox
import androidx.compose.material.icons.rounded.Call
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Face
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.businesscard.ui.theme.BusinessCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BusinessCardTheme {
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
    Column {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(top = 60.dp)
                .weight(0.6f)
        ) {
            Icon(
                imageVector = Icons.Rounded.Face,
                contentDescription = null,
                modifier = Modifier
                    .width(150.dp)
                    .height(150.dp)
            )
            Text(
                text = stringResource(R.string.top_full_name),
                fontSize = 48.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = stringResource(R.string.top_title),
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )
        }
        // 아래 개인정보가 담겨진 Column container
        // Container 자체를 중앙으로 정렬하기 위해 사용
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .weight(0.4f)
        ) {
            // 상위 Column container 안에서 start 정렬을 하기 위해서 사용된 Column container
            Column (
                horizontalAlignment = Alignment.Start
            ){
                textIcon(
                    Icons.Rounded.Call,
                    stringResource(R.string.call_number)
                )
                textIcon(
                    Icons.Rounded.AccountBox,
                    stringResource(R.string.hash_tag)
                )
                textIcon(
                    Icons.Rounded.Email,
                    stringResource(R.string.email)
                )
            }
        }
    }
}

@Composable
fun textIcon(icon: ImageVector, text: String, modifier: Modifier = Modifier) {
    Row (
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
        modifier = modifier
            .padding(bottom = 12.dp)
    ){
        Icon(
            icon,
            contentDescription = null
        )
        // Spacer는 빈 칸을 구성하는 UI
        Spacer(Modifier.width(20.dp))
        Text(
            text = text,
            fontSize = 24.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BusinessCardTheme {
        Greeting()
    }
}