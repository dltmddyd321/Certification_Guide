package com.example.cert

import android.app.SearchManager
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class Tip : Fragment()
{   //Tab View에 등록할 Fragment를 생성하고 해당하는 Layout과 연결
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?
    {
        return inflater.inflate(R.layout.tip, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val writeBtn = view.findViewById<ImageButton>(R.id.writeBtn)
        val practicalBtn = view.findViewById<ImageButton>(R.id.practiceBtn)
        val searchBtn = view.findViewById<ImageButton>(R.id.searchBtn)

        //버튼 클릭 시 자격증 필기 정보 관련 유튜브 채널로 이동
        writeBtn.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=iGpD7gGTFqQ&t=54s"))
            startActivity(intent)
        }

        //버튼 클릭 시 자격증 실기 정보 관련 유튜브 채널로 이동
        practicalBtn.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=1_ldM4fY1Xo"))
            startActivity(intent)
        }

        //정보처리산업기사 공부법이 검색된 결과의 Web으로 이동
        searchBtn.setOnClickListener {
            val intent = Intent(Intent.ACTION_WEB_SEARCH)
            intent.putExtra(SearchManager.QUERY, "정보처리산업기사 공부법")
            startActivity(intent)
        }
    }
}