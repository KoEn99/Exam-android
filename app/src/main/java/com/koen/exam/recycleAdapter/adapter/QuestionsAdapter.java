package com.koen.exam.recycleAdapter.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.koen.exam.R;
import com.koen.exam.model.QuestionData;
import com.koen.exam.views.dialogs.SheetClickOnQuestionCard;

import java.util.ArrayList;
import java.util.List;

public class QuestionsAdapter extends RecyclerView.Adapter<QuestionsAdapter.ViewHolder> {
    List<QuestionData> itemList;
    FragmentActivity context;
    public QuestionsAdapter(List<QuestionData> list, FragmentActivity context){
        this.context = context;
        this.itemList = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_question,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        QuestionData questionData = itemList.get(position);
        holder.questionText.setText(questionData.getQuestion());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SheetClickOnQuestionCard sheetClickOnQuestionCard =
                        new SheetClickOnQuestionCard(questionData.getExamId(),questionData.getQuestionType(),questionData);
                sheetClickOnQuestionCard.show(context.getSupportFragmentManager(),"sheetOnClickQuestionCard");
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView questionText;
        CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            questionText = itemView.findViewById(R.id.questionTextView);
            cardView = itemView.findViewById(R.id.cardQuestion);

        }
    }

}
