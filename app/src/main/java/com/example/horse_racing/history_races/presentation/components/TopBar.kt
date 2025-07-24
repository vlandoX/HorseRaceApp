package com.example.horse_racing.history_races.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.notesapptesting.R
import com.example.horse_racing.core.domain.model.RaceItem

@Composable
fun TopBar(raceListState: List<RaceItem>){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .padding(end = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "История скачек",
            textAlign = TextAlign.End,
            fontWeight = FontWeight.SemiBold,
            fontSize = 24.sp,
        )

        Text(
            text = stringResource(R.string.races, raceListState.size),
            textAlign = TextAlign.End,
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp,
        )
    }
}