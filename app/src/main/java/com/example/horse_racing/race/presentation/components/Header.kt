package com.example.horse_racing.race.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.horse_racing.ui.theme.BrownLight
import com.example.horse_racing.ui.theme.StableBeige2

@Composable
fun Header() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(
                RoundedCornerShape(
                    bottomStart = 32.dp,
                    bottomEnd = 32.dp
                )
            )
            .background(StableBeige2),
        contentAlignment = Alignment.Center

    ) {
        Text(
            text = "Horse Racing",
            modifier = Modifier.padding(16.dp),
            color = BrownLight,
            fontWeight = FontWeight.ExtraBold,
            fontStyle = FontStyle.Italic,
            textDecoration = TextDecoration.Underline,
            fontSize = 32.sp
        )
    }
}