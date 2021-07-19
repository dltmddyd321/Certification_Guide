package com.example.cert

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val dDayButton: Button = findViewById(R.id.dDaySetting)
        val dDayText = findViewById<TextView>(R.id.dDay)

        val certiInfoBtn = findViewById<ImageButton>(R.id.inforBtn)
        val scheduleBtn: ImageButton = findViewById(R.id.schedule)
        val channelBtn: ImageButton = findViewById(R.id.youtube)
        val studyBtn: ImageButton = findViewById(R.id.study)

        //  intent를 통해 넘어온 문자열을 difDate 변수에 담음
        val difDate = intent.getStringExtra("difDate")

        dDayText.text = difDate

        // D-Day설정 버튼을 눌러 설정창으로 이동
        dDayButton.setOnClickListener {
            val intent = Intent(this, DdayActivity::class.java)
            startActivity(intent)
        }

        certiInfoBtn.setOnClickListener {
            val intent = Intent(this,WhatCerti::class.java)
            startActivity(intent)
        }

        scheduleBtn.setOnClickListener {
            val intent = Intent(this,ScheduleCerti::class.java)
            startActivity(intent)
        }

    }
}