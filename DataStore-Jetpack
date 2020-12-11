package com.example.jetpackdemo

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.preferencesKey
import androidx.datastore.preferences.createDataStore
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map

class MainActivity : AppCompatActivity(){
    private val scope = CoroutineScope(Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        scope.launch {
            createData()
        }

        var text = findViewById<TextView>(R.id.text)
        var button = findViewById<Button>(R.id.button).apply {
            setOnClickListener {
                scope.launch {
                    var readDatas = DataStoreUtil.readFromDataStore(context, "one")
                    readDatas.collect {
                        text.text = it
                    }
                }
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        scope.cancel()
    }

    private suspend fun createData() {
        DataStoreUtil.writeToDataStore(this, "one", "123")
        DataStoreUtil.writeToDataStore(this, "two", "456")
        DataStoreUtil.writeToDataStore(this, "three", "789")
    }

}

object DataStoreUtil{
    private var dataStore: DataStore<Preferences>? = null
    private const val dataStoreName: String = "User"

    //write to DataStore
    suspend fun writeToDataStore(context: Context, key: String, msg: String){
        if(dataStore == null){
            dataStore = context.createDataStore(dataStoreName)
        }

        val data = preferencesKey<String>(key)
        dataStore!!.edit {
            it[data] = msg
        }
    }

    //read from DataStore
    fun readFromDataStore(context: Context, key: String): Flow<String>{
        if(dataStore == null){
            dataStore = context.createDataStore(dataStoreName)
        }
        val data = preferencesKey<String>(key)
        return dataStore!!.data.map {
            it[data] ?: ""
        }
    }
}
