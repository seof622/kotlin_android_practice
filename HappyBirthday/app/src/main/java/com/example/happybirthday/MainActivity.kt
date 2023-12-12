package com.example.happybirthday

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.happybirthday.ui.theme.HappyBirthdayTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HappyBirthdayTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GreetingImage(
                        // stringResource는 하드코딩 문자열을 리소스 파일로 추출하여 재사용이나 코드 유연성에 대한 효과를 증진시켜줌.
                        message = stringResource(R.string.happy_birthday_sam),
                        from = stringResource(R.string.signature_text)
                    )
                }
            }
        }
    }
}


/*
SP: 확장가능한픽셀의 약자로 글꼴 크기의 측정단위이다.
DP: 밀도 독립형 픽셀의 약자로 레이아웃에서 사용한다. 160dpi의 1픽셀과 동일하며, Android에서는 이 값을 밀도마다
적합한 픽셀 수로 변환한다.
위 수치에 관련된 설명은: https://developer.android.com/training/multiscreen/screendensities?hl=ko

두 크기 단위는 기본적으로 크기가 동일하지만, SP는 사용자가 휴대전화 설정에서 선택한 선호하는 텍스트 크기에 따라 조절된다.
따라서 텍스트는 항상 SP로 지정하고 레이아웃들은 DP로 지정한다.
 */
// Modifier가 상위 composable 함수의 modifier 속성으로 전달되는 것이 좋다. 이유가 뭘까?
@Composable
fun GreetingText(message: String, from: String, modifier: Modifier = Modifier) {
    Column (
        verticalArrangement = Arrangement.Center,
        modifier = modifier
    ){
        Text(
            text = message,
            fontSize = 100.sp,
            lineHeight = 116.sp,
            textAlign = TextAlign.Center
        )
        Text(
            text = from,
            fontSize = 36.sp,
            // 여기서 매개변수 modifier를 사용하는 것과 일반 클래스인 Modifier를 사용하는 것의 차이가 뭘까
            // modifier를 사용하면, Column에 있는 modifier를 기준으로 수정이 되는 것.
            // modifier.align을 진행하면, column modifier에 대해서 정렬이 되므로 세로 정렬
            // 새롭게 Modifier를 정의해서 진행하면 Text를 싸는 Box에 대해서 align이 진행되어서 원하는대로 가로 정렬이 됨.
            modifier = Modifier
                .padding(16.dp)
                .align(alignment = Alignment.End)
        )
    }
}

/*
                    - 이미지를 위한 Composable function
                    - 이미지를 추가할 떄, nodpi value로 설정하여 import하는데 nodpi는 모든 밀도에 대한 리소스를 의미하고 현재 화면에
                    관계없이 시스템에서 리소스 크기를 조정하지 않는다. (https://developer.android.com/training/multiscreen/screendensities?hl=ko#TaskUseDP)
                    */
            @Composable
            fun GreetingImage(message: String, from: String, modifier: Modifier = Modifier) {
                val image = painterResource(R.drawable.androidparty)
                val str1 = stringResource(R.string.my_name_is_th)
                Box {
                    Image(
                        painter = image,
                        contentDescription = null, // 앱의 화면을 읽어주는 TalkBack 기능을 건너뛰게 해줌.
                        contentScale = ContentScale.Crop, // 이미지의 너비와 높이가 화면의 크기와 같거나 더 크도록 설정됨.
            alpha = 2.0F // 배경 이미지의 불투명도 설정
        )
        GreetingText(
            message = message,
            from = from,
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        )
    }
}

// Composable 함수는 항상 파스칼 표기법으로
// 파스칼 표기법은 문자의 모든 단어의 첫 알파벳만 대문자이고 나머지는 소문자
// ex) DoneButton
@Preview(showBackground = true)
@Composable
fun BirthdayCardPreview() {
    HappyBirthdayTheme {
        GreetingImage(message = stringResource(R.string.happy_birthday_sam), from = stringResource(R.string.signature_text))
    }
}