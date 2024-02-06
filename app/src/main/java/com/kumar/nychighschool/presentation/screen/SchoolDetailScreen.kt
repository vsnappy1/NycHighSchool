package com.kumar.nychighschool.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material.icons.rounded.Phone
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.kumar.nychighschool.R
import com.kumar.nychighschool.data.HighSchool

@Composable
fun SchoolDetailScreen(highSchool: HighSchool) {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.background)) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = highSchool.schoolName ?: "",
                style = MaterialTheme.typography.headlineMedium
            )

            Text(
                text = highSchool.overviewParagraph ?: "",
                style = MaterialTheme.typography.bodyMedium
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