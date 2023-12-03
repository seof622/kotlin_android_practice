package com.example.greetingcard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.greetingcard.ui.theme.GreetingCardTheme

// androidx 는 android의 버전상 상위버전

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // onCreate 함수는 앱의 진입점을 표시하며 다른 함수들을 호출하여 사용자 인터페이스를 빌드한다
        // Kotlin에서 main함수가 시작되는 코드인 것과 동일한 부분
        setContent {
            // setContent는 Composable 함수들을 활용하여 레이아웃을 정의하는데 사용된다.
            // setContent는 closure로 작용하기 때문에, method에 바로 외부 variable을 활용한 명령문이 실행된다.
            GreetingCardTheme {
                // A surface container using the 'background' color from the theme
                //Surface는 배경색상이나 테두리와 같은 모양을 변경할 수 있는 UI container
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    Greeting("Android")
                }
            }
        }
    }
}

// Composable 주석은 Jetpack Compose에서 이 함수가 UI를 생성하는데 사용된다고 Kotlin 컴파일러에게 알림.
// Jetpack compose란 Native UI를 빌드하기 위한 Android의 최신 권장 도구 키트
// https://developer.android.com/jetpack/compose?gclid=CjwKCAiAvJarBhA1EiwAGgZl0LmR91J7VJUv0WTGm8mRcx7KiiZMJiibPYMKCztTFdtrNSVAGLTKthoCHDkQAvD_BwE&gclsrc=aw.ds&hl=ko
// Modifier는 Composable 함수를 강화하거나 장식하는데 사용된다. 아래의 코드는 padding을 추가한 형태이다.
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    // 기존 text로 주어져 있던 것을 surface로 감싸고 배경 색을 Color 패키지의 Green으로 바꾸어줌.
    // Recall: Surface는 배경색상이나 테두리와 같은 모양을 변경할 수 있는 UI container
    // Color package는 androidx.compose.ui.graphics.Color임.
    Surface(color = Color.Green) {
        Text(
            text = "Hi, my  name is  $name!",
            modifier = Modifier.padding(24.dp)
        )
    }
}

/*
Composable 함수들 특징
- 함수 이름이 대문자로 표기된다.
- @Composable 주석을 추가한다.
- 반환할 수 있는 값이 없다.
 */

/*
미리보기 함수인 GreetingPreview는 전체 앱을 빌드하지 않고도 앱이 어떻게 표시되는지 알 수 있도록 해주는 기능.
@Preview라는 주석을 달아줘야 가능하다
 */
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GreetingCardTheme {
        Greeting("Taehyun")
    }
}