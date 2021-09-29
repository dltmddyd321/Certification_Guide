package com.example.cert

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import org.json.JSONArray
import org.json.JSONObject
import kotlin.math.absoluteValue

class WordStudy: AppCompatActivity() {

    private val viewPager: ViewPager2 by lazy{
        findViewById(R.id.viewPager)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.word_study)

        initData()
    }



    private  fun initData(){
        //파이어베이스 remoteConfig 사용을 위한 선언
        val remoteConfig = Firebase.remoteConfig

        remoteConfig.setConfigSettingsAsync(
            remoteConfigSettings {
                //앱 실행될때마다 즉각적으로 패치 진행
                minimumFetchIntervalInSeconds = 0
            }
        )
        remoteConfig.fetchAndActivate().addOnCompleteListener {
            //remoteConfig를 통한 데이터 호출 및 패치에 성공한다면
            if(it.isSuccessful){

                //단어 데이터를 Json 파싱하여 섞어 호출 후 변수에 저장
                val words = parseWordsJson(remoteConfig.getString("words")).shuffled()

                //시각화 유무를 위한 상태 변수 데이터 값도 호출 후 변수에 저장
                val isWordRevealed = remoteConfig.getBoolean("is_word_revealed")

                //함수를 통해 가져온 데이터 값을 화면에 배치
                displayWordsPager(words,isWordRevealed)

            }
        }
    }

    private fun parseWordsJson(json: String): List<Word> {
        //JSON 데이터 파싱
        val jsonArray = JSONArray(json)
        var jsonList = emptyList<JSONObject>()

        //파싱한 JSON 배열의 크기 만큼 인덱스 순회
        for(index in 0 until jsonArray.length()){
            val jsonObject = jsonArray.getJSONObject(index)
            jsonObject?.let {
                //JsonObject 값이 Null이 아니라면 jsonList에 데이터 값이 연속적 추가
                jsonList = jsonList + it
            }
        }

        return jsonList.map {
            Word(
                //파이어베이스 remoteConfig의 데이터를 문자열 형태로 가져온 뒤, Word 데이터 타입에 맞게 변환
                explanation = it.getString("explanation"),
                word = it.getString("word"))
        }
    }

    private fun displayWordsPager(words:List<Word>, isWordReVealed: Boolean){
        //ViewPager를 통해 보이게 하기 위한 Adapter 연결
        viewPager.adapter = WordsPagerAdapter(
            words
        )
    }
}