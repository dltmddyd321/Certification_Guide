package com.example.cert

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class TestInfo : AppCompatActivity() {

    private val useBtn: Button by lazy {
        findViewById(R.id.useBtn)
    }

    private val exam1: Button by lazy {
        findViewById(R.id.exam1)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.test_info)

        //자격증에 사용 용도에 대한 설명으로 이동
        useBtn.setOnClickListener {
            val intent = Intent(this,WhereUsing::class.java)
            startActivity(intent)
        }

        //Q-net의 시험 접수 사이트로 이동
        exam1.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("http://www.q-net.or.kr/man001.do?gSite=Q"))
            startActivity(intent)
        }
    }
    override fun onBackPressed() {
        //뒤로 가기 버튼을 누르면 무조건 Main으로 복귀
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}