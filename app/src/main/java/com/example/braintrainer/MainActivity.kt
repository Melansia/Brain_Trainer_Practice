package com.example.braintrainer

import android.os.Bundle
import android.os.CountDownTimer
import android.preference.PreferenceManager
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private lateinit var tvScore: TextView
    private lateinit var tvTimer: TextView

    private lateinit var tvQuestion: TextView

    private lateinit var tvOpinion0: TextView
    private lateinit var tvOpinion1: TextView
    private lateinit var tvOpinion2: TextView
    private lateinit var tvOpinion3: TextView
    private lateinit var options: ArrayList<TextView>

    private var gameOver = false

    private lateinit var question: String
    private var rightAnswer = 0
    private var rightAnswerPosition = 0
    private var isPositive = true
    private var min = 5
    private var max = 30
    private var countOfQuestion = 0
    private var countOfRightAnswers = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvScore = findViewById(R.id.tvScore)
        tvTimer = findViewById(R.id.tvTimer)

        tvQuestion = findViewById(R.id.tvQuestion)

        tvOpinion0 = findViewById(R.id.tvOpinion0)
        tvOpinion1 = findViewById(R.id.tvOpinion1)
        tvOpinion2 = findViewById(R.id.tvOpinion2)
        tvOpinion3 = findViewById(R.id.tvOpinion3)
        options = ArrayList<TextView>(4)
        options.add(tvOpinion0)
        options.add(tvOpinion1)
        options.add(tvOpinion2)
        options.add(tvOpinion3)
        playNext()


        val timer: CountDownTimer = object : CountDownTimer(6000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val seconds = millisUntilFinished / 1000
                tvTimer.text = seconds.toString()
                if (millisUntilFinished < 10000) {
                    tvTimer.setTextColor(resources.getColor(android.R.color.holo_red_light))
                } else {
                    tvTimer.setTextColor(resources.getColor(android.R.color.holo_green_light))
                }
            }

            // Try to implement the ArgbEvaluator
//            val color = ArgbEvaluator().evaluate(
//                millisUntilFinished / 1000,
//                ContextCompat.getColor(applicationContext, android.R.color.holo_red_light),
//                ContextCompat.getColor(applicationContext, android.R.color.holo_green_light)
//            ) as Int

            override fun onFinish() {
                Toast.makeText(this@MainActivity, "Timer Finished", Toast.LENGTH_SHORT).show()
                gameOver = true
                val preferences = PreferenceManager.getDefaultSharedPreferences(
                    applicationContext
                )
                val max = preferences.getInt("max", 0)
                if (countOfRightAnswers >= max) {
                    preferences.edit().putInt("max", countOfRightAnswers).apply()
                }
            }
        }
        timer.start()

        val preferences = PreferenceManager.getDefaultSharedPreferences(this)

    }

    private fun getTime(millis: Long): String {
        var seconds = millis / 1000
        val minutes = seconds / 60
        seconds %= 60
        return String.format("%02d:%02d", minutes, seconds)
    }

    private fun playNext() {
        generateQuestion()
        for (i in options.indices) {
            if (i == rightAnswerPosition) {
                options[i].text = rightAnswer.toString()
            } else {
                options[i].text = generateWrongAnswer().toString()
            }
        }
        val score = String.format("%s / %s", countOfRightAnswers, countOfQuestion)
        tvScore.text = score
    }

    private fun generateQuestion() {
        val a = (Math.random() * (max - min + 1) + min).toInt()
        val b = (Math.random() * (max - min + 1) + min).toInt()
        val mark = (Math.random() * 2).toInt()
        isPositive = mark == 1
        if (isPositive) {
            rightAnswer = a + b
            question =  String.format("%s + %s", a, b)
        } else {
            rightAnswer = a - b
            question =  String.format("%s - %s", a, b)
        }
        tvQuestion.text = question
        rightAnswerPosition = (Math.random() * 4).toInt()
    }

    private fun generateWrongAnswer(): Int {
        var result = 0
        do {
            result = ((Math.random() * max * 2 + 1).toInt() - (max - min))
        } while (result == rightAnswer)
        return result
    }

    fun onClickAnswer(view: View) {
        if (!gameOver) {
            val textView = view as TextView
            val answer = textView.text.toString().toInt()
            if (answer == rightAnswer) {
                countOfRightAnswers++
                Toast.makeText(this@MainActivity, "Wright!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this@MainActivity, "Wrong!", Toast.LENGTH_SHORT).show()
            }
            countOfQuestion++
            playNext()
        }
        Toast.makeText(this@MainActivity , "Game Over!", Toast.LENGTH_SHORT).show()
    }
}