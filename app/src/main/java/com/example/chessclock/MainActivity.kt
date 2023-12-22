package com.example.chessclock

import android.media.MediaPlayer
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    lateinit var countDownTimer: CountDownTimer
    lateinit var countDownTimer1: CountDownTimer
    private var duration=1*60*1000
    private var duration1=1*60*1000
    private var isTimerRunning = false
    private var isTimerRunning1 = false
    lateinit var white_time:TextView
    lateinit var black_time:TextView
    var white_moves_count=0
    var black_moves_count=0




    private var mediaPlayer: MediaPlayer?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        white_time=findViewById(R.id.sec)
        black_time=findViewById(R.id.sec1)
        val white:ImageView=findViewById(R.id.imageView)
        val black:ImageView=findViewById(R.id.imageView1)
        val w_m:TextView=findViewById(R.id.black_moves)
        val b_m:TextView=findViewById(R.id.white_moves1)
        var k=-1

        mediaPlayer=MediaPlayer.create(this,R.raw.mouse)
        white.setOnClickListener {
            if(k!=1) {
                white_moves_count++
                k = 1
                b_m.text = white_moves_count.toString()
                black.setImageResource(R.drawable.non_clicked)
                white.setImageResource(R.drawable.clicked)
                if (isTimerRunning==true){
                    countDownTimer.cancel()
                    isTimerRunning=false
                }
                isTimerRunning1=true
                startTimer1()
                if (!mediaPlayer!!.isPlaying) {
                    mediaPlayer!!.start()
                }
            }
        }
        black.setOnClickListener {
            if(k!=0) {
                black_moves_count++
                k = 0
                w_m.text = black_moves_count.toString()
                white.setImageResource(R.drawable.non_clicked)
                black.setImageResource(R.drawable.clicked)
                if (isTimerRunning1==true){
                    countDownTimer1.cancel()
                    isTimerRunning1=false
                }
                isTimerRunning=true
                startTimer()
                if (!mediaPlayer!!.isPlaying) {
                    mediaPlayer!!.start()
                }
            }
        }
    }

    private fun startTimer() {
        countDownTimer= object:CountDownTimer(duration.toLong(),1000){
            override fun onTick(millisUntilFinished: Long) {
                duration = millisUntilFinished.toInt()
                updateTimerUI(duration.toLong())
            }

            override fun onFinish() {
                isTimerRunning=false
            }

        }.start()
    }

    private fun startTimer1() {
        countDownTimer1=object :CountDownTimer(duration1.toLong(),1000){
            override fun onTick(millisUntilFinished: Long) {
                duration1=millisUntilFinished.toInt()
                updateTimerUI1(duration1.toLong())
            }

            override fun onFinish() {
                isTimerRunning1=false
            }

        }.start()
    }

    private fun updateTimerUI(timeinMilli: Long) {
        val seconds = timeinMilli / 1000 % 60
        runOnUiThread {
            black_time.text = seconds.toString()
        }
    }

    private fun updateTimerUI1(timeinMilli: Long) {
        val seconds = timeinMilli / 1000 % 60
        runOnUiThread {
            white_time.text = seconds.toString()
        }
    }


}