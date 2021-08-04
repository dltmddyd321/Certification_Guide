package com.example.apptest1

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    private val useBtn: Button by lazy {
        findViewById(R.id.useBtn)
    }

    private val exam1: Button by lazy {
        findViewById(R.id.exam1)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        useBtn.setOnClickListener {
            val intent = Intent(this,WhereUsing::class.java)
            startActivity(intent)
        }

        exam1.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("http://www.q-net.or.kr/man001.do?gSite=Q"))
            startActivity(intent)
        }
    }
}