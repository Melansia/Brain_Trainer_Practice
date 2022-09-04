package com.example.braintrainer

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.datastore.preferences.core.Preferences
import com.github.jinatonic.confetti.CommonConfetti
import com.google.android.material.snackbar.Snackbar

class ScoreActivity : AppCompatActivity() {

    private lateinit var clRoot: ConstraintLayout

    private lateinit var tvResult: TextView
    private lateinit var btnReset: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score)
        clRoot = findViewById(R.id.clRoot)

        tvResult = findViewById(R.id.tvResult)
        btnReset = findViewById(R.id.btnReset)
//        Snackbar.make(clRoot, "You won! Congratulations.", Snackbar.LENGTH_LONG).show()
        if (intent != null && intent.hasExtra("result")) {
            val result = intent.getIntExtra("result", 0)
            val preferences = PreferenceManager.getDefaultSharedPreferences(this@ScoreActivity)
            val max = preferences.getInt("max", 0)
            val score = String.format("Your result: %s\nMax score: %s", result, max)
            tvResult.text = score
        }
//        CommonConfetti.rainingConfetti(clRoot, intArrayOf(Color.RED, Color.GREEN, Color.BLUE)).oneShot()
    }


    fun onClickStartNewGame(view: View) {
        val intent = Intent(this@ScoreActivity, MainActivity::class.java)
        startActivity(intent)
    }

    fun onClickResetScore(view: View) {
        val preferences = PreferenceManager.getDefaultSharedPreferences(this@ScoreActivity)
        preferences.edit().clear().apply()
        val intent = Intent(this@ScoreActivity, MainActivity::class.java)
        startActivity(intent)
    }


}