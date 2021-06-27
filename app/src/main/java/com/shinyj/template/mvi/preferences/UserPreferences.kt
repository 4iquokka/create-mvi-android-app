package com.shinyj.template.mvi.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.createDataStore
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

class UserPreferences
@Inject
constructor(
    context: Context
) {
    private val dataStore: DataStore<Preferences> = context.createDataStore(USER_PREFERENCES_NAME)

    private val scope = CoroutineScope(Main)

    //TODO: Declare more variables here for the preferences
    private val _isNightMode: MutableLiveData<Boolean> = MutableLiveData()

    val isNightMode: LiveData<Boolean>
        get() = _isNightMode

    init {
        observeDataStore()
    }

    private fun observeDataStore() {
        dataStore.data.onEach { preferences ->
            //TODO: Add more preferences here to observe the changes
            preferences[KEY_PREF_NIGHT_MODE]?.let{ isNightMode ->
                _isNightMode.value = isNightMode
            }
        }.launchIn(scope)
    }

    //TODO: Add more setter methods here for the variables
    fun switchMode(){
        scope.launch {
            dataStore.edit { preferences ->
                val currentMode = preferences[KEY_PREF_NIGHT_MODE] ?: false
                preferences[KEY_PREF_NIGHT_MODE] = !currentMode
            }
        }
    }

    companion object {
        private const val USER_PREFERENCES_NAME = "user_preferences"
        private val KEY_PREF_NIGHT_MODE = booleanPreferencesKey("key_pref_night_mode")
    }
}