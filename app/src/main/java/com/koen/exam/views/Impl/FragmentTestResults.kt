package com.koen.exam.views.Impl

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.koen.exam.R
import com.koen.exam.model.ScoreModel

class FragmentTestResults(var score: ScoreModel) : Fragment(),View.OnClickListener {
    lateinit var resultText:TextView
    lateinit var backBtn:Button
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.fragment_test_results,container,false)
        resultText = view.findViewById(R.id.resultsTxt)
        backBtn = view.findViewById(R.id.backToStartBtn)
        resultText.text = "Ваш результат: ${score.score} из ${score.generalScore}"
        backBtn.setOnClickListener(this)

        return view
    }

    override fun onClick(v: View?) {
        if(v?.id == R.id.backToStartBtn){
            fragmentManager?.popBackStack()
            fragmentManager?.popBackStack()
            fragmentManager?.popBackStack()
        }
    }
}