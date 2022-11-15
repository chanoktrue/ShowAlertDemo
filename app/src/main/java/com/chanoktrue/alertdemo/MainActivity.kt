package com.chanoktrue.alertdemo

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.chanoktrue.alertdemo.ui.theme.AlertDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AlertDemoTheme {
                MainView(this)
            }
        }
    }
}

@Composable
fun MainView(context: Context) {

    var isAlert by remember {
        mutableStateOf(false)
    }

    ShowAlert(
        isAlert = isAlert,
        onDismiss = {
            isAlert = false
        },
        onConfirm = {
            Toast.makeText(context, "Complete", Toast.LENGTH_LONG).show()
        },
        onDismissRequest = {
            isAlert = false
        }
    )

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = {
                isAlert = true
            }
        ) {
            Text(text = "Show Alert")
        }
    }

}

@Composable
fun ShowAlert(isAlert: Boolean, onDismissRequest: () -> Unit,onDismiss: () -> Unit, onConfirm: () -> Unit) {
    if (isAlert) {
        AlertDialog(
            onDismissRequest = { onDismissRequest() },
            title = { Text(text = "Title") },
            text = { Text(text = "Message....") },
            dismissButton = {
                Button(
                    onClick = { onDismiss() }
                ) {
                    Text(text = "Cancel")
                }
            },
            confirmButton = {
                Button(
                    onClick = { onConfirm() }
                ) {
                    Text(text = "Done")
                }
            }
        )
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    val context = LocalContext.current
    MainView(context)
}