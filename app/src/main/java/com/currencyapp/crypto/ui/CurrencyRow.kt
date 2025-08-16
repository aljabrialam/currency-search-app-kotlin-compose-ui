package com.currencyapp.crypto.ui

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.currencyapp.crypto.domain.model.CurrencyInfo

@Composable
fun CurrencyRow(item: CurrencyInfo) {
    Row(
        Modifier
            .fillMaxWidth()
            .clickable(
                indication = LocalIndication.current,
                interactionSource = remember { MutableInteractionSource() }
            ) { /* handle click */ }
            .padding(horizontal = 12.dp, vertical = 10.dp),
        verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
    ) {
        Box(
            Modifier.size(36.dp).clip(CircleShape).background(Color(0xFF333333)),
            contentAlignment = androidx.compose.ui.Alignment.Center
        ) {
            Text(
                item.name.firstOrNull()?.uppercase() ?: "",
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(Modifier.width(12.dp))

        Text(
            text = item.name,
            modifier = Modifier.weight(1f),
            fontWeight = FontWeight.SemiBold
        )

        Row(verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
            Text(item.symbol, color = Color.Gray, fontWeight = FontWeight.Bold)
            Spacer(Modifier.width(6.dp))
            Text("›", color = Color.Gray, fontWeight = FontWeight.Bold)
        }
    }
}
