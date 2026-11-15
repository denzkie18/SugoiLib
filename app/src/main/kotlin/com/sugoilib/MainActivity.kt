package com.sugoilib

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.sugoilib.utils.supabase.auth.SupabaseAuth
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Android 15 and later draw the app under the system bars: this picks status bar icons
        // that stay readable, and the padding keeps the button clear of the navigation bar.
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val bars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(bars.left, 0, bars.right, 0) // the action bar takes the top
            insets
        }

        checkUserExists()

    }

    private fun checkUserExists() {
        lifecycleScope.launch {
            val isLoggin = SupabaseAuth().checkUserLogin()
            if (!isLoggin) {
                Toast.makeText(this@MainActivity, "Not login", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
