package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.retrofit.FactApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val retrofit = Retrofit.Builder().baseUrl("https://catfact.ninja").addConverterFactory(GsonConverterFactory.create()).build()
        val factApi = retrofit.create(FactApi::class.java)

        binding.apply{

            bLogOut.setOnClickListener {

                val intentLogOut = Intent(
                    this@MainActivity,
                    LogActivity::class.java)
                openActivity(intentLogOut)
            }

            bGetFact.setOnClickListener {
                CoroutineScope(Dispatchers.IO).launch {
                    val fact = factApi.getFact()
                    runOnUiThread {
                        tvFact.text = fact.fact
                    }
                }
            }

        }
    }
}