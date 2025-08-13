package com.mr.anonym.data.local.instance

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.doublePreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("dataStore")

class DataStoreInstance(private val context: Context) {

    suspend fun saveAddress(address: String){
        val key = stringPreferencesKey("saveAddress")
        context.dataStore.edit {
            it[key] = address
        }
    }
    fun getAddress(): Flow<String>{
        val key = stringPreferencesKey("saveAddress")
        return context.dataStore.data.map {
            it[key]?:""
        }
    }

    suspend fun saveLatitude(latitude: Double){
        val key = doublePreferencesKey("saveLatitude")
        context.dataStore.edit {
            it[key] = latitude
        }
    }
    fun getLatitude():Flow<Double>{
        val key = doublePreferencesKey("saveLatitude")
        return context.dataStore.data.map {
            it[key]?:0.0
        }
    }

    suspend fun saveLongitude(longitude: Double){
        val key = doublePreferencesKey("saveLongitude")
        context.dataStore.edit {
            it[key] = longitude
        }
    }
    fun getLongitude(): Flow<Double>{
        val key = doublePreferencesKey("saveLongitude")
        return context.dataStore.data.map {
            it[key]?:0.0
        }
    }
}