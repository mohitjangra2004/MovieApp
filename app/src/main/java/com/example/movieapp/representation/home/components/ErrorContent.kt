package com.example.movieapp.representation.home.components

import android.hardware.usb.UsbInterface
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.koin.core.logger.MESSAGE


@Composable

fun ErrorContent(
    message: String ,
    onRetry: () -> Unit
) {

    Column (
        modifier = Modifier.fillMaxSize().padding(16.dp) ,

        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
      Text(
          text = message ,
          style = MaterialTheme.typography.bodyLarge,
          textAlign = TextAlign.Center
      )

        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onRetry) {
            Text("Retry")
        }


    }
}

