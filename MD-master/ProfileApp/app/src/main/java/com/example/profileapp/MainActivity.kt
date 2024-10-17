package com.example.profileapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.draw.clip
import com.example.profileapp.ui.theme.ProfileAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProfileAppTheme {
                ProfileScreen()
            }
        }
    }
}

@Composable
fun ProfileScreen() {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val profileSize = screenWidth / 2

    var selectedBlock by remember { mutableStateOf<Block?>(null) }

    // Основной контейнер с фоном
    Box(modifier = Modifier.fillMaxSize()) {
        // Фоновое изображение
        Image(
            painter = painterResource(id = R.drawable.theme), // Замените на ваш ресурс
            contentDescription = "Background Image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop // Растягиваем изображение, чтобы заполнить весь экран
        )

        // Контент
        Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
            // Новый Box для фото профиля
            Box(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(16.dp)
                    .border(2.dp, MaterialTheme.colorScheme.primary, RoundedCornerShape(16.dp)) // Рамка
                    .padding(16.dp) // Внутренние отступы
            ) {
                // Фото профиля
                Image(
                    painter = painterResource(id = R.drawable.profile),
                    contentDescription = "Profile Image",
                    modifier = Modifier
                        .size(profileSize) // Фиксированный размер
                        .clip(RoundedCornerShape(16.dp)) // Округлые углы
                        .align(Alignment.Center) // Центрируем фото
                )
            }

            // Новый Box для персональной информации
            Box(
                modifier = Modifier
                    .align(Alignment.Start) // Сместим влево
                    .padding(top = 16.dp) // Отступ от фото
            ) {
                Column(
                    horizontalAlignment = Alignment.Start // Выравниваем текст влево
                ) {
                    Text("Name: Anastasia Bovkun", fontSize = 24.sp, fontWeight = FontWeight.Bold)
                    Text("Year: 20 years old", fontSize = 16.sp)
                    Text("Role: Product Manager", fontSize = 16.sp)
                }
            }

            // Кнопки для отображения блоков с добавлением смайлов
            Button(
                onClick = { selectedBlock = if (selectedBlock == Block.PHOTOS) null else Block.PHOTOS },
                modifier = Modifier.fillMaxWidth().padding(8.dp)
            ) {
                Text("📸 Photos")
            }
            Button(
                onClick = { selectedBlock = if (selectedBlock == Block.ACHIEVEMENTS) null else Block.ACHIEVEMENTS },
                modifier = Modifier.fillMaxWidth().padding(8.dp)
            ) {
                Text("🏆 Achievements")
            }
            Button(
                onClick = { selectedBlock = if (selectedBlock == Block.PROJECTS) null else Block.PROJECTS },
                modifier = Modifier.fillMaxWidth().padding(8.dp)
            ) {
                Text("📂 Projects")
            }

            // Отображение выбранного блока
            when (selectedBlock) {
                Block.PHOTOS -> PhotoBlock()
                Block.ACHIEVEMENTS -> AchievementsBlock()
                Block.PROJECTS -> ProjectsBlock()
                null -> {} // Ничего не отображаем
            }
        }
    }
}


enum class Block {
    PHOTOS,
    ACHIEVEMENTS,
    PROJECTS
}

@Composable
fun PhotoBlock() {
    val photos = listOf(
        R.drawable.profile,
        R.drawable.profile,
        R.drawable.profile,
        R.drawable.profile,
        R.drawable.profile,
        R.drawable.profile
    ) // Добавьте свои фотографии

    var expandedPhoto by remember { mutableStateOf<Int?>(null) } // Хранит индекс увеличенного фото

    Column(modifier = Modifier.padding(16.dp)) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(3), // 3 столбца
            contentPadding = PaddingValues(4.dp)
        ) {
            items(photos) { photo ->
                val index = photos.indexOf(photo)
                Image(
                    painter = painterResource(id = photo),
                    contentDescription = "Photo",
                    modifier = Modifier
                        .padding(4.dp)
                        .size(100.dp) // Исходный размер
                        .clickable {
                            // Если нажатое фото уже увеличено, сбрасываем, иначе устанавливаем
                            expandedPhoto = if (expandedPhoto == index) null else index
                        }
                )
            }
        }
    }

    // Отображение увеличенного фото на весь экран
    expandedPhoto?.let { index ->
        Box(
            modifier = Modifier.fillMaxSize().background(Color.Black.copy(alpha = 0.8f))
        ) {
            Image(
                painter = painterResource(id = photos[index]),
                contentDescription = "Expanded Photo",
                modifier = Modifier
                    .fillMaxSize()
                    .clickable { expandedPhoto = null } // Закрываем при клике
                    .padding(32.dp), // Отступы
                contentScale = ContentScale.Fit // Сохраняем пропорции
            )
        }
    }
}



@Composable
fun AchievementsBlock() {
    Box(
        modifier = Modifier
            .padding(16.dp)
            .border(2.dp, MaterialTheme.colorScheme.primary, RoundedCornerShape(8.dp)) // Рамка
            .clip(RoundedCornerShape(8.dp)) // Округлые углы
    ) {
        Image(
            painter = painterResource(id = R.drawable.theme),
            contentDescription = "Achievements Background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop // Заполнение фона
        )
        Column(modifier = Modifier.padding(16.dp)) {
            Text("1. Completed a course on product management.", fontSize = 16.sp)
            Text("2. Launched a successful product feature.", fontSize = 16.sp)
        }
    }
}

@Composable
fun ProjectsBlock() {
    Box(
        modifier = Modifier
            .padding(16.dp)
            .border(2.dp, MaterialTheme.colorScheme.primary, RoundedCornerShape(8.dp)) // Рамка
            .clip(RoundedCornerShape(8.dp)) // Округлые углы
    ) {
        Image(
            painter = painterResource(id = R.drawable.theme),
            contentDescription = "Projects Background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop // Заполнение фона
        )
        Column(modifier = Modifier.padding(16.dp)) {
            // Здесь ваш код для отображения проектов
            val projects = listOf(
                Project("Mobile App Development", R.drawable.profile, "Developed a mobile application that enhances user experience.")
            )
            LazyVerticalGrid(
                columns = GridCells.Fixed(2), // 2 столбца
                contentPadding = PaddingValues(4.dp)
            ) {
                items(projects) { project ->
                    ProjectItem(project)
                }
            }
        }
    }
}


@Composable
fun ProjectItem(project: Project) {
    var expanded by remember { mutableStateOf(false) }

    Column(modifier = Modifier.padding(4.dp)) {
        Image(
            painter = painterResource(id = project.imageRes),
            contentDescription = project.name,
            modifier = Modifier
                .size(150.dp)
                .clickable { expanded = !expanded }
        )
        if (expanded) {
            Text(project.description, modifier = Modifier.padding(top = 4.dp))
        }
    }
}

data class Project(val name: String, val imageRes: Int, val description: String)
