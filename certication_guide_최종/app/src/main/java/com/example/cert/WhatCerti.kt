package com.example.cert

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class WhatCerti : AppCompatActivity() {

    private val useBtn: Button by lazy {
        findViewById(R.id.whereUse)
    }

    private val exam1: Button by lazy {
        findViewById(R.id.exam1)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_what_certi)

        //버튼 클릭 시 자격증 쓰임 정보에 대한 화면으로 이동
        useBtn.setOnClickListener {
            val intent = Intent(this,WhereCerti::class.java)
            startActivity(intent)
        }

        //버튼 클릭 시 Q-net 자격증 신청 사이트로 이동
        exam1.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("http://www.q-net.or.kr/man001.do?gSite=Q"))
            startActivity(intent)
        }

    }
}