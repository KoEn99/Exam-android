package com.koen.exam.recycleAdapter.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.koen.exam.R;
import com.koen.exam.model.GroupInfo;

import java.util.List;

public class GroupAdapter extends RecyclerView.Adapter<GroupAdapter.ViewHolder> {

    private List<GroupInfo> groupInfoList;

    public GroupAdapter(List<GroupInfo> groupInfoList) {
        this.groupInfoList = groupInfoList;
    }
    public void dataChanged(List<GroupInfo> groupInfoList){
        this.groupInfoList = groupInfoList;
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
        GroupInfo groupInfo = groupInfoList.get(position);
        holder.titleTextView.setText(groupInfo.getTitle());
        holder.descTextView.setText(groupInfo.getDescription());
    }

    @Override
    public int getItemCount() {
        return groupInfoList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder  {
        TextView titleTextView, descTextView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = (TextView)itemView.findViewById(R.id.titleTextViewItemGroup);
            descTextView = (TextView)itemView.findViewById(R.id.descTextViewGroup);
        }
    }
}
