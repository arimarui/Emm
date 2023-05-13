package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.Database.User
import com.example.myapplication.Database.UserDb
import com.example.myapplication.databinding.ActivityRegBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegActivity : AppCompatActivity() {
    private lateinit var binding : ActivityRegBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var db = UserDb.getDb(this)
        val userDao = db.getDao()

        binding.apply{

            bGoToLog.setOnClickListener{
               var intentLog = Intent(this@RegActivity, LogActivity::class.java)
               openActivity(intentLog)
            }

            bReg.setOnClickListener {

                lifecycleScope
                    .launch(Dispatchers.IO){

                    val user = userDao
                        .getUserByUsername(
                            edUsername.text.toString())
                    if(user != null){
                        runOnUiThread{
                            error("Error")
                            makeToast("This username already exists")
                        }
                    }
                    else{
                        if(
                            edPassword.text.toString() == edConfirmPassword.text.toString()){
                            var userReg = User(
                                edUsername.text.toString(), edPassword.text.toString())
                            db.getDao().insertUser(userReg)
                            val intent = Intent(
                                this@RegActivity, LogActivity::class.java)
                            openActivity(intent)
                        }
                        else{
                            runOnUiThread{
                                makeToast("Пароли не совпадают!")
                            }
                        }

                    }
                }


            }
        }
    }
}