package com.example.myapplication

import android.os.Bundle
import android.webkit.WebView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.myapplication.ui.theme.MyApplicationTheme
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.runtime.*
import androidx.compose.ui.viewinterop.AndroidView

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
//                    Greeting("Android")
                    WebViewContent()
                }
            }
        }
    }
}

@Composable
fun WebViewContent() {
    var webView by remember { mutableStateOf<WebView?>(null) }

    Column {
        // WebView
        AndroidView(
            factory = {
                WebView(it).apply {
                    webView = this
                    settings.javaScriptEnabled = true
                    webViewClient = WebViewClient()
                    loadData("<html><body><h1>Initial Content</h1><p id='content'>This is the original content.</p></body></html>", "text/html", "UTF-8")
                }
            },
            update = {
                // Update WebView settings or other properties if necessary
            }
        )

        // Button to update the content of the WebView
        Button(onClick = {
            val newHtml = "<html><body><h1>Updated Content</h1><p id='content'>This content was updated by clicking the button.</p></body></html>"
            webView?.loadData(newHtml, "text/html", "UTF-8")
        }) {
            Text("Update Content")
        }

        // Button to update innerHTML specifically
        Button(onClick = {
            // Update innerHTML using JavaScript
            webView?.evaluateJavascript("document.getElementById('content').innerHTML = 'This is new content from Kotlin!';", null)
        }) {
            Text("Update InnerHTML")
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
        Greeting("Android")
    }
}