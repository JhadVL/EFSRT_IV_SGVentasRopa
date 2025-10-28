package edu.pe.cibertec.sgventasropa

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import edu.pe.cibertec.sgventasropa.ui.theme.SGVentasRopaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SGVentasRopaTheme {
                SGVentasRopaApp()
            }
        }
    }
}
