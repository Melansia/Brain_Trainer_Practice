package com.example.braintrainer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.View
import android.widget.TextView

class ScoreActivity : AppCompatActivity() {

    private lateinit var tvResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score)

        tvResult = findViewById(R.id.tvResult)

        if (intent != null && intent.hasExtra("result")) {
            val result = intent.getIntExtra("result", 0)
            val preferences = PreferenceManager.getDefaultSharedPreferences(this@ScoreActivity)
            val max = preferences.getInt("max", 0)
            val score = String.format("Your result: %s\nMax score: %s", result, max)
            tvResult.text = score
        }

    }

    fun onClickStartNewGame(view: View) {
        val intent = Intent(this@ScoreActivity, MainActivity::class.java)
        startActivity(intent)
    }
}