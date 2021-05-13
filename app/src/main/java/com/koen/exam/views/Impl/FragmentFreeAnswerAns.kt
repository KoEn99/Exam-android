package com.koen.exam.views.Impl

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.koen.exam.DataSingleton
import com.koen.exam.R

class FragmentFreeAnswerAns : Fragment {
    lateinit var questionTxt:TextView
    lateinit var answer:EditText
    var dataPosition:Int
    constructor(dataPosition:Int){
        this.dataPosition = dataPosition
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_free_answer_ans,container,false)
        questionTxt = view.findViewById(R.id.questionFreeTextView)
        answer = view.findViewById(R.id.answerFreeTxtAns)
        questionTxt.text = DataSingleton.getInstance().questionDataList[dataPosition].question

        answer.addTextChangedListener(object:TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                if(answer.text.toString() != "") {
                    DataSingleton.getInstance().questionDataList[dataPosition].answers[0].answer =
                        answer.text.toString()
                }
            }

        })





        return view
    }

    override fun onStop() {
        super.onStop()

    }
}