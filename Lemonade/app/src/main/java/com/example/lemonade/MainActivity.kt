package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                // A surface container using the 'background' color from the theme
                MakingLemonade(
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MakingLemonadePreview() {
    MakingLemonade(
        modifier = Modifier.fillMaxSize()
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MakingLemonade(modifier: Modifier = Modifier) {
    Scaffold(
        // scaffold는 material design layout으로 기본적인 layout을 구현할 수 있다
        // topBar는 상단 바를 의미
        // innerPadding은 무조건 적용이 되어야 하고, 상단 및 하단바에 offset이 적용되어야 함
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.topBar_text),
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    actionIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { innerPadding ->
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .padding(innerPadding)
        ) {
            var makingLemonadeStep by remember { mutableStateOf(1) }
            var squeezeCount by remember { mutableStateOf(1) }
            var randomSqueezeCount = (2..4).random()

            var buttonImage = when (makingLemonadeStep) {
                1 -> painterResource(id = R.drawable.lemon_tree)
                2 -> painterResource(id = R.drawable.lemon_squeeze)
                3 -> painterResource(id = R.drawable.lemon_drink)
                else -> painterResource(id = R.drawable.lemon_restart)
            }

            var descriptionText = when (makingLemonadeStep) {
                1 -> stringResource(id = R.string.lemon_tree_procedure)
                2 -> stringResource(id = R.string.lemon_procedure)
                3 -> stringResource(id = R.string.lemonade_procedure)
                else -> stringResource(id = R.string.empty_glass_procedure)
            }

            Button(
                onClick = {
                    if (makingLemonadeStep == 2) {
                        if (squeezeCount == randomSqueezeCount) {
                            makingLemonadeStep++
                            randomSqueezeCount = (2..4).random()
                            squeezeCount = 0
                        }
                        squeezeCount++
                    } else {
                        makingLemonadeStep++
                    }
                    if (makingLemonadeStep > 4) makingLemonadeStep = 1
                },
                shape = RoundedCornerShape(dimensionResource(id = R.dimen.button_corner_radius)),
                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.tertiaryContainer)
            ) {
                Image(
                    painter = buttonImage,
                    contentDescription = null,
                    modifier = Modifier
                        .padding(dimensionResource(id = R.dimen.image_padding))
                )
            }
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.button_text_space)))
            Text(
                text = descriptionText,
                fontSize = 18.sp
            )
        }
    }
}

