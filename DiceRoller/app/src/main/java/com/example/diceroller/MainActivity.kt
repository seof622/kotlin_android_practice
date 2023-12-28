package com.example.diceroller

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.diceroller.ui.theme.DiceRollerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DiceRollerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DiceRollerApp()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DiceRollerApp() {
    DiceWithButtonAndImage(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    )
}

/*
Modifier
- Composable 함수에 매개변수로 Modifier 기본값
- 함수 호출 시 Modifier 값을 전달하는 이유는 composable이 재구성 할 수 있기 때문
- 재구성이란 @Composable 메서드의 코드 블록이 다시 실행되는 것

wrapContentSize
- 사용 가능 공간을 하위 요소에 맞추도록 하고,
- 사용 가능 공간이 하위 요소 보다 클 경우 매개변수로 alignment를 받아 정렬 가능

remember
- composable은 기본적으로는 stateless이다. -> 값을 보유하고 있지 않은, 시스템에서 언제든 재구성 할 수 있는 컴포넌트
- composable 함수는 remember composable 을 사용하여 메모리에 객체를 저장함
- 변수를 remember composable로 만들어 값에 따른 앱의 재구성을 시킴...?
- remember composable을 사용하려면 함수를 전달해야함
- mutableStateOf 함수가 observable을 반환
- 정확한 쓰임은 추후에 설명되며, 대략적으로는 result 변수 값이 변경되면 재구성이 트리거되고 UI가 새로고침 된다는 것.
 */
@Composable
fun DiceWithButtonAndImage(modifier: Modifier = Modifier) {
    var result by remember { mutableStateOf(1) }
    val imageResource = when (result) {
        1 -> R.drawable.dice_1
        2 -> R.drawable.dice_2
        3 -> R.drawable.dice_3
        4 -> R.drawable.dice_4
        5 -> R.drawable.dice_5
        else -> R.drawable.dice_6
    }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(imageResource),
            contentDescription = result.toString()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { result = (1..6).random() }) {
            Text(stringResource(id = R.string.roll))
        }
    }
}