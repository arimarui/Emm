package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.Database.UserDb
import com.example.myapplication.databinding.ActivityLogBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LogActivity : AppCompatActivity() {
    private lateinit var binding : ActivityLogBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var db = UserDb.getDb(this)
        val userDao = db.getDao()

        binding.apply{

            bGoToReg.setOnClickListener {
                var intentToReg = Intent(this@LogActivity, RegActivity::class.java)
                openActivity(intentToReg)
            }

            bLog.setOnClickListener {
                lifecycleScope
                    .launch(Dispatchers.IO){
                    val user = userDao
                        .getUserByUsername(edUsername.text.toString())
                    if(user != null){
                        val userWithPassword = userDao
                            .getUserByUsernameAndPassword(
                                edUsername.text.toString(), edPassword.text.toString())
                        if (userWithPassword != null) {
                            val intent = Intent(
                                this@LogActivity, MainActivity::class.java)
                            openActivity(intent)
                        }
                       else{
                            runOnUiThread{
                                makeToast("Никнейм либо пароль не верен!")
                            }
                        }
                    }
                    else{
                        runOnUiThread{
                            makeToast("Никнейм либо пароль не верен!")
                        }
                    }
                }
            }
        }
    }
}