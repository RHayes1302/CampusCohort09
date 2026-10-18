package com.example.campuscohort09

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.campuscohort09.ui.theme.CampusCohort09Theme

// Holds the string resource IDs for one campus resource
data class CampusResource(
    @param:StringRes val nameRes: Int,
    @param:StringRes val categoryRes: Int,
    @param:StringRes val hoursRes: Int,
    @param:StringRes val lastUpdateRes: Int
)

val campusResources = listOf(
    CampusResource(
        R.string.resource_intl_office_name,
        R.string.resource_intl_office_category,
        R.string.resource_intl_office_hours,
        R.string.resource_intl_office_updated
    ),
    CampusResource(
        R.string.resource_library_name,
        R.string.resource_library_category,
        R.string.resource_library_hours,
        R.string.resource_library_updated
    ),
    CampusResource(
        R.string.resource_veterans_name,
        R.string.resource_veterans_category,
        R.string.resource_veterans_hours,
        R.string.resource_veterans_updated
    )
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CampusCohort09Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CampusConnectScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun CampusConnectScreen(modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Text(
                text = stringResource(R.string.app_title),
                style = MaterialTheme.typography.headlineMedium
            )
            Text(
                text = stringResource(R.string.app_subtitles),
                style = MaterialTheme.typography.bodyLarge
            )
        }

        items(campusResources) { resource ->
            ResourceCard(resource)
        }

        // New section
        item {
            InfoSection(
                titleRes = R.string.section_orientation_title,
                descriptionRes = R.string.section_orientation_description
            )
        }
    }
}

@Composable
fun ResourceCard(resource: CampusResource, modifier: Modifier = Modifier) {
    Card(modifier = modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = stringResource(resource.nameRes),
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = stringResource(resource.categoryRes),
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                text = stringResource(
                    R.string.hours_label,
                    stringResource(resource.hoursRes)
                ),
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = stringResource(
                    R.string.last_update_label,
                    stringResource(resource.lastUpdateRes)
                ),
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

@Composable
fun InfoSection(
    @StringRes titleRes: Int,
    @StringRes descriptionRes: Int,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = stringResource(titleRes),
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = stringResource(descriptionRes),
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CampusConnectPreview() {
    CampusCohort09Theme {
        CampusConnectScreen()
    }
}