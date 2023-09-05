package com.rums.android_compose_example.demo_design

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rums.android_compose_example.R
import com.rums.android_compose_example.ui.theme.AndroidComposeExampleTheme
import java.util.Locale

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidComposeExampleTheme {
                Surface(
                    modifier = Modifier.fillMaxWidth(), color = MaterialTheme.colors.background
                ) {
                    MainApp()
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF0EAE2, widthDp = 360, heightDp = 640)
@Composable
fun DefaultPreview() {
    AndroidComposeExampleTheme {
        MainApp()
    }
}

@Composable
fun SearchBar(
    modifier: Modifier = Modifier
) {
    TextField(value = "", onValueChange = {}, leadingIcon = {
        Icon(Icons.Default.Search, contentDescription = null)
    }, placeholder = {
        Text(stringResource(id = R.string.app_name))
    }, colors = TextFieldDefaults.textFieldColors(
        backgroundColor = MaterialTheme.colors.surface
    ), modifier = modifier
        .heightIn(min = 56.dp)
        .fillMaxWidth()
    )
}

@Composable
fun MyHorizontalListElement(
    modifier: Modifier = Modifier, @DrawableRes drawable: Int, string: String
) {
    Column(
        modifier, horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painterResource(id = drawable),
            contentDescription = null,
            modifier = Modifier
                .size(88.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )
        Text(
            text = string,
            fontSize = 15.sp,
            style = MaterialTheme.typography.h3,
            modifier = Modifier.paddingFromBaseline(top = 24.dp, bottom = 8.dp)

        )
    }
}

@Composable
fun FavouriteCollectionCard(
    modifier: Modifier, @DrawableRes drawable: Int, string: String
) {
    Surface(
        modifier = modifier, shape = MaterialTheme.shapes.small
    ) {
        Row(
            modifier = Modifier.width(192.dp), verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painterResource(id = drawable),
                contentDescription = null,
                modifier = Modifier.size(56.dp),
                contentScale = ContentScale.Crop
            )
            Text(
                text = string,
                style = MaterialTheme.typography.h3,
                modifier = Modifier.padding(horizontal = 16.dp),
                fontSize = 15.sp
            )
        }
    }
}

@Composable
fun MyHorizontalListRow(
    modifier: Modifier = Modifier, names: List<String> = List(10) { "$it" }
) {
    LazyRow(
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(names) { item ->
            MyHorizontalListElement(
                drawable = R.drawable.demo_image, string = ("Heer $item")
            )
        }
    }
}

@Composable
fun FavouriteCollectionsGrid(
    modifier: Modifier = Modifier, names: List<String> = List(10) { "$it" }
) {
    LazyHorizontalGrid(
        rows = GridCells.Fixed(2),
        modifier = modifier.height(120.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)

    ) {
        items(names) { item ->
            FavouriteCollectionCard(
                modifier = modifier, drawable = R.drawable.demo_image, string = ("Heer $item")
            )
        }
    }
}

@Composable
fun HomeSection(
    @StringRes title: Int, modifier: Modifier = Modifier, content: @Composable () -> Unit
) {
    Column(modifier = modifier) {
        Text(
            stringResource(id = title).uppercase(Locale.getDefault()),
            style = MaterialTheme.typography.h2,
            fontSize = 20.sp,
            modifier = Modifier
                .paddingFromBaseline(top = 40.dp, bottom = 8.dp)
                .padding(horizontal = 16.dp)
        )
        content()

    }
}

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier
) {
    Column(modifier.verticalScroll(rememberScrollState())) {
        Spacer(modifier = Modifier.height(16.dp))
        SearchBar(Modifier.padding(horizontal = 8.dp))
        HomeSection(title = R.string.my_demo_text) {
            MyHorizontalListRow()
        }
        HomeSection(title = R.string.my_demo_text) {
            FavouriteCollectionsGrid()
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun SmoothBottomNavigation(modifier: Modifier = Modifier) {
    BottomNavigation(
        modifier = modifier, backgroundColor = MaterialTheme.colors.background
    ) {
        BottomNavigationItem(selected = true, onClick = { /*TODO*/ }, icon = {
            Icon(Icons.Default.Home, contentDescription = null)
        }, label = {
            Text(text = stringResource(id = R.string.my_demo_text))
        })
        BottomNavigationItem(selected = false, onClick = { /*TODO*/ }, icon = {
            Icon(Icons.Default.AccountCircle, contentDescription = null)
        }, label = {
            Text(text = stringResource(id = R.string.my_demo_text))
        })
    }
}

@Composable
fun MainApp() {
    Scaffold(bottomBar = { SmoothBottomNavigation() }) { padding ->
        HomeScreen(
            modifier = Modifier.padding(padding)
        )
    }
}