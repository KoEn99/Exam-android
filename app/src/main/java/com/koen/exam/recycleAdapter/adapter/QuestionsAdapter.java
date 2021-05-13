package com.koen.exam.recycleAdapter.adapter;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.koen.exam.R;
import com.koen.exam.drawable.CircleView;
import com.koen.exam.model.ColorQuestion;

import com.koen.exam.model.QuestionData;
import com.koen.exam.views.dialogs.SheetClickOnQuestionCard;

import java.security.AccessControlContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.security.AccessController.getContext;

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
        switch (questionData.getAnalysisDto().getColor()){
            case "GREEN":{
                holder.circleView.setCircleColor(Color.GREEN);
            }
            case "RED":{
                holder.circleView.setCircleColor(Color.RED);
            }
            case "YELLOW":{
                holder.circleView.setCircleColor(Color.YELLOW);
            }
        }

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SheetClickOnQuestionCard sheetClickOnQuestionCard =
                        new SheetClickOnQuestionCard(questionData.getExamId(),questionData.getQuestionType(),questionData);
                sheetClickOnQuestionCard.show(context.getSupportFragmentManager(),"sheetOnClickQuestionCard");
            }
        });
        holder.circleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MaterialAlertDialogBuilder(context)
                        .setTitle("Статистика вопроса")
                        .setMessage(questionData.getAnalysisDto().getMessage())
                        .setPositiveButton("Редактировать", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .setNegativeButton("Удалить", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        })
                        .show();
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
        CircleView circleView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            questionText = itemView.findViewById(R.id.questionTextView);
            cardView = itemView.findViewById(R.id.cardQuestion);
            circleView = itemView.findViewById(R.id.circleView);

        }
    }

}
