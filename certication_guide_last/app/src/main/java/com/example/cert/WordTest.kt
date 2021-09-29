package com.example.cert

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import org.json.JSONArray
import org.json.JSONObject

class WordTest: AppCompatActivity() {

    private val viewPagerWordTest: ViewPager2 by lazy {
        findViewById(R.id.viewPagerWordTest)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.word_test)

        initData()
    }

    private fun initData() {
        //파이어베이스 remoteConfig 사용을 위한 선언
        val remoteConfig = Firebase.remoteConfig
        remoteConfig.setConfigSettingsAsync(
            remoteConfigSettings {
                //앱 실행될때마다 즉각적으로 패치 진행
                minimumFetchIntervalInSeconds = 0
            }
        )
        remoteConfig.fetchAndActivate().addOnCompleteListener {
            if(it.isSuccessful) {
                //remoteConfig를 통한 데이터 호출 및 패치에 성공한다면
                val words = parseWordsJson(remoteConfig.getString("words")).shuffled()
                //랜덤으로 단어 출력

                //시각화 유무를 위한 상태 변수 데이터 값도 호출 후 변수에 저장
                val isWordRevealed = remoteConfig.getBoolean("is_word_revealed")

                displayWordsPager(words,isWordRevealed)

            }
        }
    }

    private fun parseWordsJson(json: String): List<Words> {
        //JSON 데이터 파싱
        val jsonArray = JSONArray(json)
        var jsonList = emptyList<JSONObject>()
        for(index in 0 until jsonArray.length()) {
            val jsonObject = jsonArray.getJSONObject(index)
            jsonObject?.let {
                jsonList = jsonList + it
            }
        }
        return jsonList.map {
            //파이어베이스 remoteConfig의 데이터를 문자열 형태로 가져온 뒤, Word 데이터 타입에 맞게 변환
            Words(
                exp = it.getString("explanation"),
                word = it.getString("word")
            )

        }
    }

    private fun displayWordsPager(words: List<Words>, isNameRevealed: Boolean) {
        viewPagerWordTest.adapter = WordsTestAdapter(
            words
        )
    }
}