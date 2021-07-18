package com.example.mytabview

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fourthfragment.*

class MainActivity : AppCompatActivity() {

    private lateinit var context: Context


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = PagerAdapter(supportFragmentManager)
        adapter.addFragment(MainFragment(), "1회")
        adapter.addFragment(SecondFragment(), "2회")
        adapter.addFragment(ThirdFragment(), "3회")
        adapter.addFragment(FourthFragment(),"시험 신청!")
        after_login_viewpager.adapter = adapter
        after_login_tablayout.setupWithViewPager(after_login_viewpager)


    }
}