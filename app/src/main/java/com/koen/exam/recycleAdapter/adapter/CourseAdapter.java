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
import com.koen.exam.model.CourseInfo;
import com.koen.exam.views.dialogs.SheetClickOnEditRecycle;

import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.ViewHolder> {

    private List<CourseInfo> courseInfoList;
    FragmentActivity context;

    public CourseAdapter(List<CourseInfo> courseInfoList, FragmentActivity context) {
        this.context = context;
        this.courseInfoList = courseInfoList;
    }
    public void dataChanged(List<CourseInfo> courseInfoList){
        this.courseInfoList = courseInfoList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.edit_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CourseInfo courseInfo = courseInfoList.get(position);
        holder.titleTextView.setText(courseInfo.getTitle());
        holder.descTextView.setText(courseInfo.getDescription());
        holder.cardView.setOnClickListener(v -> {
            SheetClickOnEditRecycle sheetsCreateGroup = new SheetClickOnEditRecycle();
            sheetsCreateGroup.show(context.getSupportFragmentManager(), "TAG1");
        });
    }

    @Override
    public int getItemCount() {
        return courseInfoList.size();
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
