package com.example.myapplication

import android.content.Intent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar

fun AppCompatActivity.makeToast(message : String){
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}
fun AppCompatActivity.openActivity(intent : Intent){
    startActivity(intent)
}