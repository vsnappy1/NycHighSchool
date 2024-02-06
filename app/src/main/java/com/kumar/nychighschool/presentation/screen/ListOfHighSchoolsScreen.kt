package com.kumar.nychighschool.presentation.screen

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material.icons.rounded.Phone
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kumar.nychighschool.R
import com.kumar.nychighschool.data.HighSchool
import com.kumar.nychighschool.network.NetworkRequestStatus
import com.kumar.nychighschool.presentation.viewmodel.ListOfHighSchoolsViewModel

data class ListOfHighSchoolsUiState(
    val networkRequestStatus: NetworkRequestStatus = NetworkRequestStatus.Loading,
)

@Composable
fun ListOfHighSchoolsScreen(
    viewmodel: ListOfHighSchoolsViewModel = viewModel()
) {

    val uiState by viewmodel.uiState.observeAsState(ListOfHighSchoolsUiState())
    var isDetailScreenVisible by remember { mutableStateOf(false) }
    var currentSelectedSchool by remember { mutableStateOf(HighSchool()) }
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        when (uiState.networkRequestStatus) {
            is NetworkRequestStatus.Successed<*> -> {
                val response =
                    uiState.networkRequestStatus as NetworkRequestStatus.Successed<List<HighSchool>>
                LazyColumn(
                    modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(response.data) {
                        HighSchoolItem(highSchool = it,
                            onClick = {
                                isDetailScreenVisible = true
                                currentSelectedSchool = it
                            })
                    }
                }

                AnimatedVisibility(
                    visible = isDetailScreenVisible,
                    enter = slideInVertically().plus(fadeIn())
                ) {
                    SchoolDetailScreen(highSchool = currentSelectedSchool)
                }
            }

            is NetworkRequestStatus.Failed<*> -> {
                val response =
                    uiState.networkRequestStatus as NetworkRequestStatus.Failed<List<HighSchool>>
                Text(text = response.message)
            }

            is NetworkRequestStatus.Loading -> {
                CircularProgressIndicator()
            }
        }
    }

    BackHandler(isDetailScreenVisible) {
        isDetailScreenVisible = false
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HighSchoolItem(
    highSchool: HighSchool,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth()
            .testTag("School Item"),
        onClick = onClick

    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            Text(
                text = highSchool.schoolName ?: "",
                style = MaterialTheme.typography.headlineMedium
            )
            IconWithLabel(
                imageVector = Icons.Rounded.Phone,
                description = "Phone Number",
                title = highSchool.phoneNumber
            )
            IconWithLabel(
                imageVector = ImageVector.vectorResource(R.drawable.round_web_24),
                description = "Website",
                title = highSchool.website
            )
            IconWithLabel(
                imageVector = Icons.Rounded.LocationOn,
                description = "Location",
                title = highSchool.location
            )
        }
    }
}

@Composable
fun IconWithLabel(imageVector: ImageVector, description: String, title: String?) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(imageVector, description)
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = title ?: "", style = MaterialTheme.typography.labelMedium)
    }
}

@Preview
@Composable
fun PreviewHighSchoolItem() {
    HighSchoolItem(
        highSchool = HighSchool(
            schoolName = "Clinton School Writers & Artists, M.S. 260",
            location = "10 East 15th Street, Manhattan NY 10003 (40.736526, -73.992727)",
            phoneNumber = "212-524-4360",
            website = "www.theclintonschool.net"
        ),
        onClick = {}
    )
}