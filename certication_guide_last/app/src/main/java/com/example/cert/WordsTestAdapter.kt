package com.example.cert

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class WordsTestAdapter (
    private val words: List<Words>
): RecyclerView.Adapter<WordsTestAdapter.WordTestViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        WordTestViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_word_test,parent,false)
        )

    override fun onBindViewHolder(holder: WordTestViewHolder, position: Int) {
        holder.bind(words[position])
    }

    override fun getItemCount() = words.size

    class WordTestViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val wordTestView: TextView = itemView.findViewById(R.id.wordTestView)
        private val expTestView: TextView = itemView.findViewById(R.id.expTestView)

        private val gradingBtn: Button = itemView.findViewById(R.id.gradingBtn)
        private val submissionBtn: Button = itemView.findViewById(R.id.submissionBtn)
        private val answerWordEdt: EditText = itemView.findViewById(R.id.wordAnswerEdt)

        fun bind(words: Words) {
            //바인딩되는 요소와 실제 Layout의 요소를 연결
            wordTestView.text = words.word
            expTestView.text = words.exp

            //초기에는 문제풀이에서 답을 볼 수 없도록 TextView 비시각화
            wordTestView.visibility = View.GONE

            submissionBtn.setOnClickListener {
                wordTestView.visibility = View.VISIBLE
                when {
                    words.word.indexOf(answerWordEdt.text.toString()) > -1 -> {
                        //indexOf의 값이 -1보다 크게 설정함으로서 사용자가 입력한 답이 정담 범위에 포함된다는 것을 구현
                        when {
                            answerWordEdt.text.toString().equals("") -> {
                                //EditText의 값이 빈 문자열이라면 정답을 입력하라는 Toast Message 출력
                                Toast.makeText(itemView.context,"정답을 입력해 주세요!!", Toast.LENGTH_LONG).show()
                                wordTestView.visibility = View.GONE
                            }
                            //정답이 맞다면 정답 내용과 AlertDialog 출력
                            else -> AlertDialog.Builder(itemView.context)
                                .setTitle("정답 확인")
                                .setMessage("정답입니다!!\n 정답: ${words.word}")
                                .setPositiveButton("확인") { _, _ -> }
                                .create()
                                .show()
                        }
                    }
                    //오답이라면 정답 내용과 AlertDialog 출력
                    else -> {
                        AlertDialog.Builder(itemView.context)
                            .setTitle("정답 확인")
                            .setMessage("오답입니다!!\n 정답: ${words.word}")
                            .setPositiveButton("확인") { _, _ -> }
                            .create()
                            .show()
                    }
                }
            }

            gradingBtn.setOnClickListener {
                //버튼을 누르면 정답을 볼 수 있게 TextView 시각화
                wordTestView.visibility = View.VISIBLE
            }
        }

    }

}