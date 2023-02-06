package edu.uksw.fti.pam.pamactivityintent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.*
import edu.uksw.fti.pam.pamactivityintent.ui.theme.PAMActivityIntentTheme
import kotlinx.coroutines.launch


class HomeActivity : AppCompatActivity() {
    @ExperimentalMaterialApi
    @ExperimentalPagerApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PAMActivityIntentTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainScreen(ViewModel = DataViewModel())
                }
            }
        }
    }
}


@ExperimentalMaterialApi
@ExperimentalPagerApi
@Composable
fun MainScreen(
    ViewModel:DataViewModel
){
    val state = ViewModel.state

    val tabs = listOf(
        TabItem.Home,
        TabItem.Status,
        TabItem.Call,
    )

    val pagerState = rememberPagerState(pageCount = tabs.size)

    Scaffold(
        topBar = {
            Column {
                if (state.isSearchBarVisible){
                    SearchBar (
                        onCloseIconClicked = {
                            ViewModel.onAction(UserAction.CloseIconClicked)
                        }
                    )
                }else {
                    TopBar (
                        onSearchIconClicked = {
                            ViewModel.onAction(UserAction.SearchIconClicked)
                        }
                    )
                }

                Divider(
                    color = Color(0XFF7F67BE),
                    thickness = 5.dp,
                    modifier = Modifier.padding(vertical = 4.dp)
                )
                Tabs(tabs = tabs, pagerState = pagerState);
                TabsContent(tabs = tabs, pagerState = pagerState)
            }
        },
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = { FloatingActionButton(onClick = {}){
            Icon(
                painter = painterResource(id = R.drawable.ic_drive_file),
                contentDescription = "Pen Icon",
                modifier = Modifier.size(25.dp)
            )
        } },
    ) {}

}

@Composable
//Hanya LetsTalk
fun TopBar(
    onSearchIconClicked: () -> Unit
){

    Row (
        modifier = Modifier
            .background(Color(0XFF7F67BE))
            .fillMaxWidth()
            .padding(top = 7.dp, start = 7.dp),
        verticalAlignment = Alignment.CenterVertically,


        ){
        Text(text = "LetsTalk", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.White)
        Spacer(modifier = Modifier.weight(1f))
        IconButton(onClick =  onSearchIconClicked ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_search_24),
                contentDescription = "Search Icon",
                modifier = Modifier.size(25.dp),
                tint = Color.White
            )
        }
        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                painter = painterResource(id = R.drawable.ic_camera_alt_24),
                contentDescription = "Camera Icon",
                modifier = Modifier.size(25.dp),
                tint = Color.White
            )
        }
    }
}

@Composable
fun SearchBar(
    onCloseIconClicked: () -> Unit
){
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = "",
        onValueChange ={},
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Search ,
                contentDescription = "Search Icon",
                tint = Color.Black
            )
        },
        trailingIcon = {
            IconButton(onClick =  onCloseIconClicked) {
                Icon(
                    imageVector = Icons.Filled.Close ,
                    contentDescription = "Close Icon",
                    tint = Color.Black
                )
            }
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            unfocusedBorderColor = Color.Black,
            focusedBorderColor = Color.White
        ),
        placeholder = {
            Text(
                text = "Search....",
                color = Color.White
            )
        }
    )

}

//CONTENT NYA =>

@ExperimentalMaterialApi
@ExperimentalPagerApi
@Composable
fun Tabs(tabs: List<TabItem>, pagerState: PagerState){

    val scope = rememberCoroutineScope()
    TabRow(
        selectedTabIndex = pagerState.currentPage,
        backgroundColor = Color(0XFF7F67BE),
        contentColor = Color.White,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier.pagerTabIndicatorOffset(pagerState, tabPositions)
            )
        }
    ) {
        tabs.forEachIndexed{ index, tab ->

            LeadingIconTab(
                icon = { Icon(painter = painterResource(id = tab.icon), contentDescription = "") },
                text = { Text(text = tab.title) },
                selected = pagerState.currentPage == index,
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                },
            )
        }
    }
}


@ExperimentalPagerApi
@Composable
fun TabsContent(tabs: List<TabItem>, pagerState: PagerState){
    HorizontalPager(state = pagerState) { page ->
        tabs[page].screen()
    }
}

@Composable
fun HomeScreen(){

    Column(
        modifier = Modifier.fillMaxSize(),

    ) {
        Row (
            modifier = Modifier.clickable { }.fillMaxWidth().background(Color(0XFFF6EDFF))

        ){
            Image(
                painter = painterResource(id = R.drawable.img_image1), 
                contentDescription = null,
                modifier = Modifier.size(45.dp).padding(top = 10.dp,start = 10.dp),
                alignment = Alignment.TopStart
            )
            Column {
                Text(
                    text = "Michael J. Ramli",
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(top = 5.dp, start = 20.dp)
                )
                Text(
                    text = "Hello Good Morning",
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    fontSize = 10.sp,
                    modifier = Modifier.padding(start = 20.dp)
                )
            }

        }
        Row (
            modifier = Modifier.clickable { }.fillMaxWidth().padding(top = 5.dp, bottom = 5.dp).background(Color(0XFFF6EDFF))

                ){
            Image(
                painter = painterResource(id = R.drawable.ic_iamge2),
                contentDescription = null,
                modifier = Modifier.size(45.dp).padding(top = 10.dp,start = 10.dp),
                alignment = Alignment.TopStart
            )
            Column {
                Text(
                    text = "Jonathan",
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(top = 5.dp, start = 20.dp)
                )
                Text(
                    text = "Are You Home?",
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    fontSize = 10.sp,
                    modifier = Modifier.padding(start = 20.dp)
                )
            }

        }
        Row (
            modifier = Modifier.clickable { }.fillMaxWidth().padding(bottom = 3.dp).background(Color(0XFFF6EDFF))

        ){
            Image(
                painter = painterResource(id = R.drawable.img_image3),
                contentDescription = null,
                modifier = Modifier.size(45.dp).padding(top = 10.dp,start = 10.dp),
                alignment = Alignment.TopStart
            )
            Column {
                Text(
                    text = "Alexa",
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(top = 5.dp, start = 20.dp)
                )
                Text(
                    text = "Lets Work",
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    fontSize = 10.sp,
                    modifier = Modifier.padding(start = 20.dp)
                )
            }

        }
        Row (
            modifier = Modifier.clickable { }.fillMaxWidth().padding(bottom = 3.dp).background(Color(0XFFF6EDFF))

        ){
            Image(
                painter = painterResource(id = R.drawable.img_image4),
                contentDescription = null,
                modifier = Modifier.size(45.dp).padding(top = 10.dp,start = 10.dp),
                alignment = Alignment.TopStart
            )
            Column {
                Text(
                    text = "Sarah Wijayanti",
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(top = 5.dp, start = 20.dp)
                )
                Text(
                    text = "Have a Nice Day",
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    fontSize = 10.sp,
                    modifier = Modifier.padding(start = 20.dp)
                )
            }

        }
        Row (
            modifier = Modifier.clickable { }.fillMaxWidth().padding(bottom = 3.dp).background(Color(0XFFF6EDFF))

        ){
            Image(
                painter = painterResource(id = R.drawable.img_image5),
                contentDescription = null,
                modifier = Modifier.size(45.dp).padding(top = 10.dp,start = 10.dp),
                alignment = Alignment.TopStart
            )
            Column {
                Text(
                    text = "Donatelo K.Natan",
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(top = 5.dp, start = 20.dp)
                )
                Text(
                    text = "Lets Have a Holiday",
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    fontSize = 10.sp,
                    modifier = Modifier.padding(start = 20.dp)
                )
            }

        }
        Row (
            modifier = Modifier.clickable { }.fillMaxWidth().padding(bottom = 3.dp).background(Color(0XFFF6EDFF))

        ){
            Image(
                painter = painterResource(id = R.drawable.img_iamge6),
                contentDescription = null,
                modifier = Modifier.size(45.dp).padding(top = 10.dp,start = 10.dp),
                alignment = Alignment.TopStart
            )
            Column {
                Text(
                    text = "Kimberly",
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(top = 5.dp, start = 20.dp)
                )
                Text(
                    text = "Lets make a beautiful night",
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    fontSize = 10.sp,
                    modifier = Modifier.padding(start = 20.dp)
                )
            }

        }
        Row (
            modifier = Modifier.clickable { }.fillMaxWidth().padding(bottom = 3.dp).background(Color(0XFFF6EDFF))

        ){
            Image(
                painter = painterResource(id = R.drawable.img_image7),
                contentDescription = null,
                modifier = Modifier.size(45.dp).padding(top = 10.dp,start = 10.dp),
                alignment = Alignment.TopStart
            )
            Column {
                Text(
                    text = "Home Alone",
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(top = 5.dp, start = 20.dp)
                )
                Text(
                    text = "I'm scared",
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    fontSize = 10.sp,
                    modifier = Modifier.padding(start = 20.dp)
                )
            }

        }
        Row (
            modifier = Modifier.clickable { }.fillMaxWidth().padding(bottom = 3.dp).background(Color(0XFFF6EDFF))

        ){
            Image(
                painter = painterResource(id = R.drawable.img_iamge8),
                contentDescription = null,
                modifier = Modifier.size(45.dp).padding(top = 10.dp,start = 10.dp),
                alignment = Alignment.TopStart
            )
            Column {
                Text(
                    text = "Layla Limbergh",
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(top = 5.dp, start = 20.dp)
                )
                Text(
                    text = "Last night was really fun",
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    fontSize = 10.sp,
                    modifier = Modifier.padding(start = 20.dp)
                )
            }

        }
        Row (
            modifier = Modifier.clickable { }.fillMaxWidth().padding(bottom = 3.dp).background(Color(0XFFF6EDFF))

        ){
            Image(
                painter = painterResource(id = R.drawable.img_image1),
                contentDescription = null,
                modifier = Modifier.size(45.dp).padding(top = 10.dp,start = 10.dp),
                alignment = Alignment.TopStart
            )
            Column {
                Text(
                    text = "Koh LI Cuan",
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(top = 5.dp, start = 20.dp)
                )
                Text(
                    text = "Lest make money",
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    fontSize = 10.sp,
                    modifier = Modifier.padding(start = 20.dp)
                )
            }

        }
        Row (
            modifier = Modifier.clickable { }.fillMaxWidth().padding(bottom = 3.dp).background(Color(0XFFF6EDFF))

        ){
            Image(
                painter = painterResource(id = R.drawable.img_image3),
                contentDescription = null,
                modifier = Modifier.size(45.dp).padding(top = 10.dp,start = 10.dp),
                alignment = Alignment.TopStart
            )
            Column {
                Text(
                    text = "Sunset",
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(top = 5.dp, start = 20.dp)
                )
                Text(
                    text = "What a beautiful day",
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    fontSize = 10.sp,
                    modifier = Modifier.padding(start = 20.dp)
                )
            }

        }

        //Last
        Row (
            modifier = Modifier.clickable { }.fillMaxWidth().padding(bottom = 3.dp).background(Color(0XFFF6EDFF))

        ){
            Image(
                painter = painterResource(id = R.drawable.img_image4),
                contentDescription = null,
                modifier = Modifier.size(45.dp).padding(top = 10.dp,start = 10.dp),
                alignment = Alignment.TopStart
            )
            Column {
                Text(
                    text = "Layla Layla",
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(top = 5.dp, start = 20.dp)
                )
                Text(
                    text = "Last night was realy fun",
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    fontSize = 10.sp,
                    modifier = Modifier.padding(start = 20.dp)
                )
            }

        }
        Row (
            modifier = Modifier.clickable { }.fillMaxWidth().padding(bottom = 3.dp).background(Color(0XFFF6EDFF))

        ){
            Image(
                painter = painterResource(id = R.drawable.img_image5),
                contentDescription = null,
                modifier = Modifier.size(45.dp).padding(top = 10.dp,start = 10.dp),
                alignment = Alignment.TopStart
            )
            Column {
                Text(
                    text = "Suprihadi",
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(top = 5.dp, start = 20.dp)
                )
                Text(
                    text = "Dont forget your task",
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    fontSize = 10.sp,
                    modifier = Modifier.padding(start = 20.dp)
                )
            }

        }
        Row (
            modifier = Modifier.clickable { }.fillMaxWidth().padding(bottom = 3.dp).background(Color(0XFFF6EDFF))

        ){
            Image(
                painter = painterResource(id = R.drawable.img_iamge6),
                contentDescription = null,
                modifier = Modifier.size(45.dp).padding(top = 10.dp,start = 10.dp),
                alignment = Alignment.TopStart
            )
            Column {
                Text(
                    text = "Berliana",
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(top = 5.dp, start = 20.dp)
                )
                Text(
                    text = "Want to wacth a movie?",
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    fontSize = 10.sp,
                    modifier = Modifier.padding(start = 20.dp)
                )
            }

        }
        Row (
            modifier = Modifier.clickable { }.fillMaxWidth().padding(bottom = 3.dp).background(Color(0XFFF6EDFF))

        ){
            Image(
                painter = painterResource(id = R.drawable.img_image4),
                contentDescription = null,
                modifier = Modifier.size(45.dp).padding(top = 10.dp,start = 10.dp),
                alignment = Alignment.TopStart
            )
            Column {
                Text(
                    text = "Amanda",
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(top = 5.dp, start = 20.dp)
                )
                Text(
                    text = "Last night was realy fun",
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    fontSize = 10.sp,
                    modifier = Modifier.padding(start = 20.dp)
                )
            }

        }


    }
}

@Composable
fun StatusScreen(){

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Status",
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            fontSize = 30.sp
        )
    }

}

@Composable
fun CallScreen(){

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Panggilan",
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            fontSize = 30.sp
        )
    }

}


@Preview
@ExperimentalMaterialApi
@ExperimentalPagerApi
@Composable
fun Preview() {
    MainScreen(ViewModel = DataViewModel())
}