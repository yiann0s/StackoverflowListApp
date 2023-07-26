package com.example.myrncapp.presentation.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myrncapp.R
import com.example.myrncapp.networking.model.Question

class ListAdapter(
    private val context: Context,
    private val mQuestions: List<Question>
) : RecyclerView.Adapter<ListAdapter.QuestionViewHolder>() {

    private var onClickListener: OnClickListener? = null

    // onClickListener Interface
    interface OnClickListener {
        fun onClick(position: Int, question: Question)
    }

    fun setOnClickListener(onClickListener: OnClickListener) {
        this.onClickListener = onClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recyclerview_item_layout, parent, false)
        return QuestionViewHolder(view)
    }

    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
        holder.positionNumber.text =
            context.resources.getString(R.string.question_num, position + 1)
        holder.title.text =
            context.resources.getString(R.string.question_title, mQuestions[position].title)
        holder.link.text =
            context.resources.getString(R.string.question_link, mQuestions[position].link)

        holder.itemView.setOnClickListener {
            onClickListener?.onClick(position, mQuestions[position])
        }
    }

    override fun getItemCount(): Int {
        return mQuestions.size
    }

    class QuestionViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val positionNumber: TextView
        val title: TextView
        val link: TextView

        init {
            positionNumber = view.findViewById<View>(R.id.positionNumber) as TextView
            title = view.findViewById<View>(R.id.title) as TextView
            link = view.findViewById<View>(R.id.link) as TextView
        }
    }

}
