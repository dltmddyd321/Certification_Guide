package com.example.cert

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox

class StudyActivity : AppCompatActivity() {

    private val selectButton: Button by lazy {
        findViewById(R.id.selectButton)
    }

    private val cancelButton: Button by lazy {
        findViewById(R.id.cancelButton)
    }

    private val writtenTest: CheckBox by lazy {
        findViewById(R.id.writtenTest)
    }
    private val wordStudy: CheckBox by lazy {
        findViewById(R.id.wordStudy)
    }
    private val wordTest: CheckBox by lazy {
        findViewById(R.id.wordTest)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_study)

        writtenTest.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                wordStudy.isChecked = false
                wordTest.isChecked = false
            }
        }

        wordStudy.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                writtenTest.isChecked = false
                wordTest.isChecked = false
            }
        }

        wordTest.setOnCheckedChangeListener{ buttonView, isChecked ->
            if (isChecked) {
                writtenTest.isChecked = false
                wordStudy.isChecked = false
            }
        }

        selectButton.setOnClickListener {

            if (writtenTest.isChecked) {
//                val intent = Intent(this, WrittenTest::class.java)
//                startActivity(intent)
            }

            if (wordStudy.isChecked) {
//                val intent = Intent(this, WordStudy::class.java)
//                startActivity(intent)
            }

            if (wordTest.isChecked) {
//                val intent = Intent(this, WordTest::class.java)
//                startActivity(intent)
            }

        }

        cancelButton.setOnClickListener {
            writtenTest.isChecked = false
            wordStudy.isChecked = false
            wordTest.isChecked = false
        }

    }

}