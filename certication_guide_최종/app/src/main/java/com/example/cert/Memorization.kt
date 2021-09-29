package com.example.cert

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import androidx.viewpager2.widget.ViewPager2
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import org.json.JSONArray
import org.json.JSONObject

class Memorization : AppCompatActivity() {

    private val viewPager: ViewPager2 by lazy {
        findViewById(R.id.viewPager)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_memorization)

        initData()
    }

    private fun initData() {
        val remoteConfig = Firebase.remoteConfig
        remoteConfig.setConfigSettingsAsync(
            remoteConfigSettings {
                //앱을 실행할 때마다 fetch가 이루어짐
                minimumFetchIntervalInSeconds = 0
            }
        )
        remoteConfig.fetchAndActivate().addOnCompleteListener {
            if(it.isSuccessful) {
                // firebase remoteConfig에서 words,is_word_revealed key 값을 가진 json 데이터를 가져와 저장
                val words = parseWordsJson(remoteConfig.getString("words")).shuffled()
                //랜덤으로 단어 출력
                val isWordRevealed = remoteConfig.getBoolean("is_word_revealed")

                displayWordsPager(words,isWordRevealed)

            }
        }
    }

    private fun parseWordsJson(json: String): List<Words> {
        val jsonArray = JSONArray(json)
        var jsonList = emptyList<JSONObject>()
        for(index in 0 until jsonArray.length()) {
            val jsonObject = jsonArray.getJSONObject(index)
            jsonObject?.let {
                //jsonObject가 null이 아니면 jsonList에 이어 붙힘
                jsonList = jsonList + it
            }
        }
        return jsonList.map {
            Words(
                exp = it.getString("explanation"),
                word = it.getString("word")
            )

        }
    }

    private fun displayWordsPager(words: List<Words>, isNameRevealed: Boolean) {
        viewPager.adapter = WordsMemoryAdapter(
            words
        )
    }
}