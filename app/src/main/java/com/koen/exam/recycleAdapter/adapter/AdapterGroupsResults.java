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
import com.koen.exam.views.Impl.FragmentStudentsGroupResults;
import com.koen.exam.views.Impl.FragmentSubsExams;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class AdapterGroupsResults extends RecyclerView.Adapter<AdapterGroupsResults.ViewHolder> {
    private List<GroupInfo> groupInfoList;
    FragmentActivity context;
    String courseId;
    int examId;

    public AdapterGroupsResults(List<GroupInfo> groupInfoList, FragmentActivity context, String courseId, int examId) {
        this.context = context;
        this.groupInfoList = groupInfoList;
        this.courseId = courseId;
        this.examId = examId;

    }
    public void dataChanged(List<GroupInfo> groupInfoList){
        this.groupInfoList = groupInfoList;

    }

    @NonNull
    @Override
    public AdapterGroupsResults.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.edit_item, parent, false);
        return new AdapterGroupsResults.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterGroupsResults.ViewHolder holder, int position) {
        GroupInfo groupInfo = groupInfoList.get(position);
        try {
            holder.titleTextView.setText(groupInfo.getName());
            holder.descTextView.setText("Имя группы в поиске " + groupInfo.getId().substring(0,4));
        }
        catch (Exception e){

        }

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.getSupportFragmentManager().beginTransaction()
                        .addToBackStack(null).replace(R.id.scrim,new FragmentStudentsGroupResults(groupInfo.getId(),examId))
                        .commit();
            }
        });

    }

    @Override
    public int getItemCount() {
        return groupInfoList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder  {
        TextView titleTextView, descTextView;
        CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = (CardView)itemView.findViewById(R.id.card);
            titleTextView = (TextView)itemView.findViewById(R.id.titleTextViewItemGroup);
            descTextView = (TextView)itemView.findViewById(R.id.descTextViewGroup);
        }
    }
}
