package com.example.flagquiz

import android.content.Intent
import android.database.DatabaseUtils
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.i
import android.view.LayoutInflater
import androidx.databinding.DataBindingUtil
import com.example.flagquiz.databinding.ActivityMainBinding
import com.info.sqlitekullanimihazirveritabani.DatabaseCopyHelper

class MainActivity : AppCompatActivity() {
    private lateinit var design : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        design = DataBindingUtil.setContentView(this,R.layout.activity_main)
        copyDatabase()
        design.buttonStart.setOnClickListener {
            startActivity(Intent(this@MainActivity,QuizActivity::class.java))

        }

    }

    fun copyDatabase(){
        val dpHelper = DatabaseCopyHelper(this)
        try {
            dpHelper.createDataBase()
        }catch (e: Exception){
            throw Error("Unable to create database")
        }

        try {
            dpHelper.createDataBase()
        }catch (e: Exception){
            throw Error("Unable to open database")
        }
    }
}