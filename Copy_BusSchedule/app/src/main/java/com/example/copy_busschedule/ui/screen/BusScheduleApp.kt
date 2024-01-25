package com.example.copy_busschedule.ui.screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavArgument
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.copy_busschedule.R
import com.example.copy_busschedule.data.BusSchedule
import com.example.copy_busschedule.ui.theme.Copy_BusScheduleTheme
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

enum class ScheduleScreen {
    FullSchedule,
    RouteSchedule
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BusScheduleApp(
    modifier: Modifier = Modifier,
    viewModel: BusScheduleViewModel = viewModel(factory = BusScheduleViewModel.Factory)
) {
    val navController = rememberNavController()
    val fullScheduleTitle = stringResource(R.string.bus_schedule)
    var topAppBarTitle by remember { mutableStateOf(fullScheduleTitle) }
    val fullBusSchedule by viewModel.getAllBusSchedules().collectAsState(emptyList())
    val onBackHandler = {
        topAppBarTitle = fullScheduleTitle
        navController.navigateUp()
    }

    Scaffold (
        topBar = {
            BusScheduleTopAppBar(
                title = topAppBarTitle,
                emptyBackStackEntry = navController.previousBackStackEntry == null,
                onBackButton = { onBackHandler() }
            )
        }
    ) {innerPadding ->
        NavHost(
            navController = navController,
            startDestination = ScheduleScreen.FullSchedule.name,
        ) {
            composable(ScheduleScreen.FullSchedule.name) {
                FullScheduleScreen(
                    innerPadding = innerPadding,
                    busSchedule = fullBusSchedule,
                    onClickSchedule = { stopName ->
                        navController.navigate(ScheduleScreen.RouteSchedule.name + "/$stopName")
                        topAppBarTitle = stopName
                    }
                )
            }
            val busRouteArgument = "busRoute"
            composable(
                route = ScheduleScreen.RouteSchedule.name + "/{$busRouteArgument}",
                arguments = listOf(navArgument(busRouteArgument) { type = NavType.StringType })
            ) { navBackStackEntry ->
                val stopName = navBackStackEntry.arguments?.getString(busRouteArgument)
                    ?: error("busRouteArgument cannot be null")
                val routeSchedule by viewModel.getBusSchedule(stopName).collectAsState(emptyList())
                RouteScheduleScreen(
                    stopName = stopName,
                    innerPadding = innerPadding,
                    busSchedule = routeSchedule,
                    onBackHandler = { onBackHandler() }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BusScheduleTopAppBar(
    title: String,
    emptyBackStackEntry: Boolean,
    onBackButton: () -> Unit,
    modifier: Modifier = Modifier
) {
    if (emptyBackStackEntry) {
        TopAppBar(title = { Text(title) })
    } else {
        TopAppBar(
            title = { Text(title) },
            navigationIcon = {
                IconButton(onClick = onBackButton) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back)
                    )
                }
            }
        )
    }
}

@Composable
fun FullScheduleScreen(
    modifier: Modifier = Modifier,
    innerPadding: PaddingValues,
    busSchedule: List<BusSchedule>,
    onClickSchedule: ((String) -> Unit)
) {
    BusScheduleScreen(
        innerPadding = innerPadding,
        busSchedule = busSchedule,
        onClickSchedule = onClickSchedule
    )
}

@Composable
fun RouteScheduleScreen(
    modifier: Modifier = Modifier,
    stopName: String,
    innerPadding: PaddingValues,
    busSchedule: List<BusSchedule>,
    onBackHandler: () -> Unit
) {
    BackHandler { onBackHandler() }
    BusScheduleScreen(
        stopName = stopName,
        innerPadding = innerPadding,
        busSchedule = busSchedule,
    )
}

@Composable
fun BusScheduleScreen(
    modifier: Modifier = Modifier,
    innerPadding: PaddingValues,
    busSchedule: List<BusSchedule>,
    stopName: String? = null,
    onClickSchedule: ((String) -> Unit)? = null
) {
    val stopNameText = if (stopName == null) {
        stringResource(R.string.stop_name)
    } else {
        "$stopName " + stringResource(R.string.stop)
    }

    Column (
        Modifier
            .padding(horizontal = 16.dp)
            .padding(top = innerPadding.calculateTopPadding())
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        ) {
            Text (text = stopNameText)
            Text (text = stringResource(R.string.arrival_time))
        }
        Divider()
        BusScheduleDetail(
            busSchedule = busSchedule,
            onClickSchedule = onClickSchedule
        )
    }
}

@Composable
fun BusScheduleDetail(
    modifier: Modifier = Modifier,
    busSchedule: List<BusSchedule>,
    onClickSchedule: ((String) -> Unit) ?= null
) {
    LazyColumn {
        items(
            items = busSchedule,
            key = { busSchedule -> busSchedule.id }
        ) {schedule ->
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(enabled = onClickSchedule != null) {
                        onClickSchedule?.invoke(schedule.stopName)
                    }
                    .padding(vertical = 8.dp)
            ) {
                if (onClickSchedule != null) {
                    Text(
                        text = schedule.stopName,
                        style = MaterialTheme.typography.titleLarge
                    )
                    Text(
                        text = SimpleDateFormat("h:mm a", Locale.getDefault())
                            .format(Date(schedule.arrivalTime.toLong() * 1000)),
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.titleLarge
                    )
                } else {
                    Text(
                        text = "--",
                        style = MaterialTheme.typography.titleLarge,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = SimpleDateFormat("h:mm a", Locale.getDefault())
                            .format(Date(schedule.arrivalTime.toLong() * 1000)),
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.titleLarge,
                        textAlign = TextAlign.End,
                        modifier = Modifier.weight(2f)
                    )
                }
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun BusScheduleScreenPreview() {
//    Copy_BusScheduleTheme {
//        Surface {
//            BusScheduleScreen(emptyList())
//        }
//    }
//}
