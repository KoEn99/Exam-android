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
import com.koen.exam.model.GroupInfo;
import com.koen.exam.model.ResultsOfStudents;

import java.util.List;

public class StudentsGroupResultsAdapter extends RecyclerView.Adapter<StudentsGroupResultsAdapter.ViewHolder> {
    private List<ResultsOfStudents> resultsOfStudents;
    FragmentActivity context;

    public StudentsGroupResultsAdapter(List<ResultsOfStudents> resultsOfStudents, FragmentActivity context) {
        this.context = context;
        this.resultsOfStudents = resultsOfStudents;
    }
    public void dataChanged(List<ResultsOfStudents> resultsOfStudents){
        this.resultsOfStudents = resultsOfStudents;

    }

    @NonNull
    @Override
    public StudentsGroupResultsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_students_results_card, parent, false);
        return new StudentsGroupResultsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentsGroupResultsAdapter.ViewHolder holder, int position) {
        ResultsOfStudents studentResult = resultsOfStudents.get(position);
        holder.tryCount.setText("Количество попыток: " + String.valueOf(studentResult.getTryCount()));
        holder.fioTextView.setText(studentResult.getFio());
        holder.generalScore.setText("Максимальный результат: " + String.valueOf(studentResult.getGeneralScore()));
        holder.scoreTextView.setText("Результат: "+String.valueOf(studentResult.getCountCorrectAnswer()));
    }

    @Override
    public int getItemCount() {
        return resultsOfStudents.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder  {
        TextView fioTextView, scoreTextView, generalScore, tryCount;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            fioTextView = itemView.findViewById(R.id.fioStudentTxt);
            scoreTextView = itemView.findViewById(R.id.studentScore);
            generalScore = itemView.findViewById(R.id.maxScoreTxt);
            tryCount = itemView.findViewById(R.id.tryCountStudentTxt);
        }
    }
}
