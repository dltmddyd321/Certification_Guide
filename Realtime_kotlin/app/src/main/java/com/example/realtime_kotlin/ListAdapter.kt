package com.example.realtime_kotlin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.w3c.dom.Text

class ListAdapter(
    private val itemList: List<ListLayout>
    ): RecyclerView.Adapter<ListAdapter.TestViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        TestViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item, parent, false)
        )


    override fun getItemCount() = itemList.size


    override fun onBindViewHolder(holder: TestViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    class TestViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
       //private val name: TextView = itemView.findViewById(R.id.list_tv_name)
        private val solve: TextView = itemView.findViewById(R.id.question)
        private val res1: RadioButton = itemView.findViewById(R.id.firstAnswer)
        private val res2: RadioButton = itemView.findViewById(R.id.secondAnswer)
        private val res3: RadioButton = itemView.findViewById(R.id.thirdAnswer)
        private val res4: RadioButton = itemView.findViewById(R.id.fourthAnswer)
        private val dap: TextView = itemView.findViewById(R.id.dapNoSee)
        private val nextBtn: Button = itemView.findViewById(R.id.nextButton)

        fun bind(listLayout: ListLayout){
            solve.text = listLayout.solve
            res1.text = listLayout.res1
            res2.text = listLayout.res2
            res3.text = listLayout.res3
            res4.text = listLayout.res4
            dap.text = listLayout.dap

            nextBtn.setOnClickListener {
                when {
                    res1.isChecked -> when (res1.text) {
                        dap.text -> {
                            Toast.makeText(itemView.context, "정답!", Toast.LENGTH_SHORT).show()
                        }
                        else -> {
                            Toast.makeText(itemView.context, "오답!", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
                when {
                    res2.isChecked -> when (res2.text) {
                        dap.text -> {
                            Toast.makeText(itemView.context, "정답!", Toast.LENGTH_SHORT).show()
                        }
                        else -> {
                            Toast.makeText(itemView.context, "오답!", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
                when {
                    res3.isChecked -> when (res3.text) {
                        dap.text -> {
                            Toast.makeText(itemView.context, "정답!", Toast.LENGTH_SHORT).show()
                        }
                        else -> {
                            Toast.makeText(itemView.context, "오답!", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
                when {
                    res4.isChecked -> when (res4.text) {
                        dap.text -> {
                            Toast.makeText(itemView.context, "정답!", Toast.LENGTH_SHORT).show()
                        }
                        else -> {
                            Toast.makeText(itemView.context, "오답!", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }
}