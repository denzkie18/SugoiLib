package com.sugoilib.utils.supabase.auth

import com.sugoilib.data.SupabaseProvider
import io.github.jan.supabase.auth.auth

class SupabaseAuth {

    private val auth = SupabaseProvider.client.auth

    // Checking user is logged in
    suspend fun checkUserLogin(): Boolean {
        auth.awaitInitialization()
        return auth.currentSessionOrNull() != null
    }
}
