package com.example.braintrainer

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.preference.PreferenceManager
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

    private var gameOver = false

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

    }
}