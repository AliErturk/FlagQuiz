package com.example.flagquiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.flagquiz.databinding.ActivityMainBinding
import com.example.flagquiz.databinding.ActivityQuizBinding

class QuizActivity : AppCompatActivity() {

    private lateinit var design : ActivityQuizBinding

    private lateinit var questions : ArrayList<Flags>
    private lateinit var wrongSelections : ArrayList<Flags>
    private lateinit var correctSelection : Flags
    private lateinit var allSelections : HashSet<Flags>

    private lateinit var da :DatabaseAssistant

    private var trueAnswer : Int =0
    private var falseAnswer : Int =0
    private var questionCount : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        design= DataBindingUtil.setContentView(this,R.layout.activity_quiz)

        da = DatabaseAssistant(this)

        questions = Flagsdao().getRandom5Flags(da)

        uploadQuestion()

        design.buttonA.setOnClickListener {
            correctAnswerController(design.buttonA)
            questionCountController()
        }
        design.buttonB.setOnClickListener {
            correctAnswerController(design.buttonB)
            questionCountController()
        }
        design.buttonC.setOnClickListener {
            correctAnswerController(design.buttonC)
            questionCountController()
        }
        design.buttonD.setOnClickListener {
            correctAnswerController(design.buttonD)
            questionCountController()
        }


    }

    fun uploadQuestion(){

        design.textViewQuizCount.text ="${questionCount+1}. Question"

        correctSelection = questions.get(questionCount)

        design.imageViewFlag.setImageResource(
            resources.getIdentifier(correctSelection.flag_picture,"drawable",packageName)
        )
        wrongSelections = Flagsdao().getRandom3FalseFlags(da,correctSelection.flag_id)
        allSelections = HashSet()
        allSelections.add(correctSelection)
        allSelections.add(wrongSelections.get(0))
        allSelections.add(wrongSelections.get(1))
        allSelections.add(wrongSelections.get(2))

        design.buttonA.text= allSelections.elementAt(0).flag_name
        design.buttonB.text= allSelections.elementAt(1).flag_name
        design.buttonC.text= allSelections.elementAt(2).flag_name
        design.buttonD.text= allSelections.elementAt(3).flag_name



    }
    fun questionCountController(){
        questionCount++
        if (questionCount !=5){
            uploadQuestion()
        }else{
            val intent = Intent(this@QuizActivity,ResultActivity::class.java)
            intent.putExtra("true",trueAnswer)
            intent.putExtra("false",falseAnswer)
            startActivity(intent)
            finish()
        }
    }
    fun correctAnswerController(button :Button){
        val buttonText = button.text.toString()
        val correctAnswer = correctSelection.flag_name
        if (buttonText.lowercase()==correctAnswer.lowercase()){
            trueAnswer++
        }else{
            falseAnswer++
        }
        design.textViewTrue.text="True: $trueAnswer"
        design.textViewFalse.text="False: $falseAnswer"
    }
}