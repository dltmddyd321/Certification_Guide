package com.example.cert

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.os.persistableBundleOf
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide


class TestPagerAdapter (
    private val itemList: List<Test>
): RecyclerView.Adapter<TestPagerAdapter.TestViewHolder>() {

    //Adapter가 적용될 Layout을 ViewHolder를 생성하여 적용
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        TestViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_test, parent, false)
        )


    //담길 아이템의 개수는 itemList의 크기로 치환
    override fun getItemCount() = itemList.size


    override fun onBindViewHolder(holder: TestViewHolder, position: Int) {
        //Glide: 안드로이드에서 이미지 파일을 가져오고 등록하기 위한 라이브러리 선언
        Glide.with(holder.itemView)
            .load(itemList[position].img) //itemList의 img 항목을 로드
            .into(holder.img)

        //itemList의 각 항목을 바인딩
        holder.bind(itemList[position])
    }

    class TestViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val solve: TextView = itemView.findViewById(R.id.question)
        private val res1: RadioButton = itemView.findViewById(R.id.firstAnswer)
        private val res2: RadioButton = itemView.findViewById(R.id.secondAnswer)
        private val res3: RadioButton = itemView.findViewById(R.id.thirdAnswer)
        private val res4: RadioButton = itemView.findViewById(R.id.fourthAnswer)
        private val dap: TextView = itemView.findViewById(R.id.correctAnswer)
        private val radioGroup: RadioGroup = itemView.findViewById(R.id.radioGroup)
        private val correctBtn: Button = itemView.findViewById(R.id.correctButton)
        val img: ImageView = itemView.findViewById(R.id.testImage)



        fun bind(test: Test) {
            //바인딩으로 지정한 요소와 실제 Layout의 요소들을 연결            solve.text = test.solve
            res1.text = test.res1
            res2.text = test.res2
            res3.text = test.res3
            res4.text = test.res4
            dap.text = test.dap


            correctBtn.setOnClickListener {
                try {
                    val radioButton: RadioButton = itemView.findViewById(radioGroup.checkedRadioButtonId)
                        if (dap.text.toString().equals(radioButton.text.toString())) {
                            //dap의 텍스트 값과 라디오 버튼의 텍스트 값이 일치하면 정답 AlertDialog 생성
                            //체크 아이콘을 가진 정답임을 알려주는 AlertDialog 빌드
                            androidx.appcompat.app.AlertDialog.Builder(itemView.context)
                                .setIcon(R.drawable.ic_done)
                                .setTitle("정답")
                                .setMessage("정답입니다!!")
                                .setPositiveButton("확인",null)
                                .create()
                                .show()
                        } else {
                            //dap의 텍스트 값과 라디오 버튼의 텍스트 값이 일치하면 오답 AlertDialog 생성
                            //X 아이콘을 가진 오답임을 알려주는 AlertDialog 빌드
                            androidx.appcompat.app.AlertDialog.Builder(itemView.context)
                                .setIcon(R.drawable.ic_baseline_clear_24)
                                .setTitle("오답")
                                .setMessage("오답입니다!!")
                                .setPositiveButton("확인",null)
                                .create()
                                .show()
                        }
                    }catch(e: NullPointerException){ //NullException 오류를 배체하기 위한 예외처리
                        //라디오 버튼의 어떠한 요소도 선택되지 않았을 경우 Toast Message 출
                        Toast.makeText(itemView.context, "정답을 선택해 주세요!!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}