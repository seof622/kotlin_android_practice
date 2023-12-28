package com.example.superheros

import android.os.Bundle
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.superheros.model.Hero
import com.example.superheros.model.HeroesRepository.heroes
import com.example.superheros.ui.theme.Shapes
import com.example.superheros.ui.theme.SuperherosTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SuperherosTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    HeroApp()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HeroApp(modifier: Modifier = Modifier) {
    Scaffold(
        modifier = modifier,
        topBar = {
            HeroTopAppBar()
        }
    ) { it ->
        LazyColumn(contentPadding = it) {
            items(heroes) {
                HeroItem(hero = it)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HeroTopAppBar(modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        title = { 
            Text(
                text = stringResource(id = R.string.app_name),
                style = MaterialTheme.typography.displayLarge
            )
        }
    )
}

@Composable
fun HeroItem(
    hero: Hero,
    modifier: Modifier = Modifier
) {
    Card (
        modifier = Modifier
            .clip(Shapes.large)
            .padding(
                start = 16.dp,
                end = 16.dp,
                top = 4.dp,
                bottom = 4.dp
            )
    ){
        Row(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Column (Modifier.weight(4f)){
                Text(
                    text = stringResource(id = hero.nameRes),
                    style = MaterialTheme.typography.displayMedium
                )
                Text(
                    text = stringResource(id = hero.descriptionRes),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
            Spacer(Modifier)
            Image(
                painter = painterResource(id = hero.imageRes),
                contentDescription = null,
                modifier = Modifier
//                    .width(dimensionResource(id = R.dimen.imageSize))
//                    .height(dimensionResource(id = R.dimen.imageSize))
                    .weight(1f)
                    .clip(Shapes.small)
            )
        }
    }
}

@Preview
@Composable
fun HeroAppPreview(modifier: Modifier = Modifier) {
    SuperherosTheme{
        HeroApp(modifier = modifier)
    }
}