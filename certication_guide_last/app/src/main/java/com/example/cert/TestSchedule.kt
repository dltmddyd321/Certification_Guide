package com.example.cert

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.viewpager.widget.PagerAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fourthfragment.*
import kotlinx.android.synthetic.main.test_schedule.*

class TestSchedule : AppCompatActivity() {

    private lateinit var context: Context


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.test_schedule)

        val adapter = PagerAdapter(supportFragmentManager)

        //Tab View의 각 요소가 되는 Fragment를 등록
        adapter.addFragment(MainFragment(), "1회")
        adapter.addFragment(SecondFragment(), "2회")
        adapter.addFragment(ThirdFragment(), "3회")
        adapter.addFragment(FourthFragment(),"시험 신청!")

        //View pager 연결
        after_login_viewpager.adapter = adapter
        after_login_tablayout.setupWithViewPager(after_login_viewpager)

    }
}