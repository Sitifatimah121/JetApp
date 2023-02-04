package com.compose.jetapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.compose.components.CategoryItem
import com.compose.components.MenuItem
import com.compose.components.SearchBar
import com.compose.jetapp.model.BottomBarItem
import com.compose.jetapp.model.Menu
import com.compose.jetapp.model.dummyCategory
import com.compose.jetapp.ui.theme.JetAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetAppTheme {
                JetCoffeeApp()
            }
        }
    }
}

@Composable
fun JetCoffeeApp(modifier: Modifier = Modifier) {
    Scaffold (
        bottomBar = { BottomBar()}
    ){
        Column(
            modifier = modifier
                .verticalScroll(rememberScrollState())
        ) {
            Banner()

        }
    }
//    Column {
//        Banner()
//        SectionText(stringResource(R.string.section_category))
//        CategoryRow()
//    }
}

@Composable
fun Banner(
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier){
        Image(
            painter = painterResource(R.drawable.banner),
            contentDescription = "Banner Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier.height(160.dp)
        )
        SearchBar()
    }
}

@Composable
fun MenuRow(
    listMenu: List<Menu>,
    modifier: Modifier = Modifier
){
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        modifier = modifier
    ){
        items(listMenu, key = {it.title}) { menu ->
            MenuItem(menu)
        }
    }
}

@Composable
fun CategoryRow(
    modifier: Modifier = Modifier
){
    LazyRow(
        horizontalArrangement  = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        modifier = modifier.padding(16.dp)
    ){
        items(dummyCategory, key = {it.textCategory}) { category ->
            CategoryItem(category)
        }
    }
}

@Composable
fun BottomBar(
    modifier: Modifier = Modifier
){
    BottomNavigation(
        modifier = modifier
    ) {
        val navigationItems = listOf(
            BottomBarItem(
                title = stringResource(R.string.menu_home),
                icon = Icons.Default.Home
            ),
            BottomBarItem(
                title = stringResource(R.string.menu_favorite),
                icon = Icons.Default.Favorite
            ),
            BottomBarItem(
                title = stringResource(R.string.menu_profile),
                icon = Icons.Default.AccountCircle
            )
        )

        navigationItems.map {
            BottomNavigationItem(
                icon = {
                    Icon(
                        imageVector = it.icon,
                        contentDescription = it.title
                    )
                },
                label = {
                    Text(it.title)
                },
                selected = it.title == navigationItems[0].title,
                onClick = {}
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun CategoryRowPreview(){
    JetAppTheme {
        CategoryRow()
    }
}

@Composable
@Preview(showBackground = true)
fun DefaultPreview() {
    JetAppTheme {
        JetCoffeeApp()
    }
}