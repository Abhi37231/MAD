package com.halil.ozel.catchthefruits

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Looper
import androidx.appcompat.app.AlertDialog
import android.view.View
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import com.halil.ozel.catchthefruits.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var score = 0
    private val imageArray = ArrayList<ImageView>()
    private val handler = android.os.Handler(Looper.getMainLooper())
    private lateinit var runnable: Runnable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.catchFruits = this
        binding.score = getString(R.string.score_0)
        score = 0
        imageArray.addAll(
            listOf(
                binding.ivApple, binding.ivBanana, binding.ivCherry,
                binding.ivGrapes, binding.ivKiwi, binding.ivOrange,
                binding.ivPear, binding.ivStrawberry, binding.ivWatermelon
            )
        )
        hideImages()
        playAndRestart()
    }

    private fun hideImages() {
        runnable = Runnable {
            imageArray.forEach { it.visibility = View.INVISIBLE }
            imageArray[Random().nextInt(9)].visibility = View.VISIBLE
            handler.postDelayed(runnable, 500)
        }
        handler.post(runnable)
    }

    @SuppressLint("SetTextI18n")
    fun increaseScore() {
        binding.score = String.format(getString(R.string.score_format), ++score)
    }

    @SuppressLint("SetTextI18n")
    fun playAndRestart() {
        score = 0
        binding.score = "Score : 0"
        hideImages()
        binding.time = "Time : 10"
        imageArray.forEach { it.visibility = View.INVISIBLE }

        object : CountDownTimer(10000, 1000) {
            @SuppressLint("SetTextI18n")
            override fun onFinish() {
                binding.time = getString(R.string.time_up)
                handler.removeCallbacks(runnable)

                AlertDialog.Builder(this@MainActivity).apply {
                    setCancelable(false)
                    setTitle(getString(R.string.game_name))
                    setMessage("Your score : $score\nWould you like to play again?")
                    setPositiveButton(getString(R.string.yes)) { _, _ -> playAndRestart() }
                    setNegativeButton(getString(R.string.no)) { _, _ ->
                        score = 0
                        binding.score = getString(R.string.score_0)
                        binding.time = getString(R.string.time_0)
                        imageArray.forEach { it.visibility = View.INVISIBLE }
                        finish()
                    }
                }.create().show()
            }

            @SuppressLint("SetTextI18n")
            override fun onTick(tick: Long) {
                binding.time = String.format(getString(R.string.time), (tick / 1000).toInt())
            }
        }.start()
    }
}