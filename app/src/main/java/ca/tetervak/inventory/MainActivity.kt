package ca.tetervak.inventory

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import ca.tetervak.inventory.ui.AppRootScreen
import ca.tetervak.inventory.ui.theme.InventoryAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            InventoryAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    AppRootScreen()
                }
            }
        }
    }
}