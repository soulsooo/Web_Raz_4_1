package com.example.firstapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable // Импортируем clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.firstapp.ui.theme.FirstAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FirstAppTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    // Состояние, чтобы отслеживать, было ли нажатие на изображение
    val isImageVisible = remember { mutableStateOf(false) }

    // Основное размещение с изображением и фоном
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        // Проверяем состояние и отображаем соответствующее содержимое
        if (isImageVisible.value) {
            // Устанавливаем изображение в качестве фона
            Image(
                painter = painterResource(id = R.drawable.hello), // Замените на имя вашего фонового изображения
                contentDescription = null,
                modifier = Modifier.fillMaxSize()
            )
            // Текст "Здравствуй, мир!"
            Text(text = "Здравствуй, мир!", color = Color.Black, modifier = Modifier.align(Alignment.TopCenter))
        } else {
            // Изображение вместо кнопки
            StartImage(onClick = { isImageVisible.value = true })
        }
    }
}

@Composable
fun StartImage(onClick: () -> Unit) {
    Image(
        painter = painterResource(id = R.drawable.start), // Замените на имя вашего изображения для кнопки
        contentDescription = null,
        modifier = Modifier
            .fillMaxSize() // Занимает всё пространство
            .padding(0.dp) // Убираем отступы
            .clickable { onClick() } // Обработка нажатия
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewMainScreen() {
    FirstAppTheme {
        MainScreen()
    }
}
