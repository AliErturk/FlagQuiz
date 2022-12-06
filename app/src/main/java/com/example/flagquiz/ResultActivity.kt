package com.example.flagquiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.constraintlayout.motion.widget.DesignTool
import androidx.databinding.DataBindingUtil
import com.example.flagquiz.databinding.ActivityMainBinding
import com.example.flagquiz.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    private lateinit var design  : ActivityResultBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        design = DataBindingUtil.setContentView(this,R.layout.activity_result)


        val trueAnswer = intent.getIntExtra("true",0)
        val falseAnswer = intent.getIntExtra("false",0)

        design.textViewTrueFalse.text ="Result:\n\tTrue: $trueAnswer\n\tFalse: $falseAnswer"
        design.textViewScore.text = "Score: ${trueAnswer*20} Point"

        design.buttonTryAgain.setOnClickListener {
            val intent = Intent(this@ResultActivity,QuizActivity:: class.java)
            finish()
            startActivity(intent)
        }


    }
}