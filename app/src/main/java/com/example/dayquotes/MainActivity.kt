package com.example.dayquotes

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


const val BASE_URL = "https://zenquotes.io/"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /**
         * setContentView(R.layout.test_layout)
        // Set the layout manager for your RecyclerView here
        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val dataset = arrayOf("January", "February", "March")
        val customAdapter = CustomAdapter(dataset)

        recyclerView.adapter = customAdapter

        */

        setContentView(R.layout.activity_main)

        //createDb()

        val button = findViewById<Button>(R.id.button_id)
        // Set a click listener for the button
        button.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                // Perform another get request when button is clicked
                getMyData()
            }
        })

        // Perform get request
        getMyData()
    }

    /**
    private fun createDb() {
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "database-name"
        ).build()

    }

     */

    private fun getMyData() {
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory
            .create())
            .baseUrl(BASE_URL)
            .build()
            .create(ApiInterface::class.java)

        val retrofitData = retrofitBuilder.getData()

        retrofitData.enqueue(object : Callback<List<MyDataItem>?> {
            override fun onResponse(
                call: Call<List<MyDataItem>?>, response: Response<List<MyDataItem>?>) {

                // !! is null safety operator
                val responseBody = response.body()!!

                val myStringBuilder = StringBuilder()
                for (myData in responseBody){
                    myStringBuilder.append(myData.q)
                    myStringBuilder.append("\n")
                }

                val myStringBuilderTwo = StringBuilder()
                for (myData in responseBody){
                    myStringBuilderTwo.append(myData.a)
                    myStringBuilderTwo.append("\n")
                }

                val txtId:TextView = findViewById(R.id.info_text)

                txtId.text = myStringBuilder

                val txtIdTwo:TextView = findViewById(R.id.second_text)

                txtIdTwo.text = myStringBuilderTwo

            }

            override fun onFailure(call: Call<List<MyDataItem>?>, t: Throwable) {
                Log.d("MainActivity", "onFailure: "+t.message)
            }
        })
    }
}