package com.koen.exam.recycleAdapter.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.koen.exam.R;
import com.koen.exam.model.GroupInfo;
import com.koen.exam.views.Impl.EditFragment;
import com.koen.exam.views.Impl.MainFragment;
import com.koen.exam.views.Impl.NavigationActivity;
import com.koen.exam.views.dialogs.SheetClickOnEditRecycle;
import com.koen.exam.views.dialogs.SheetsCreateGroup;

import java.util.List;

public class GroupAdapter extends RecyclerView.Adapter<GroupAdapter.ViewHolder> {

    private List<GroupInfo> groupInfoList;
    FragmentActivity context;

    public GroupAdapter(List<GroupInfo> groupInfoList, FragmentActivity context) {
        this.context = context;
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
        holder.cardView.setOnClickListener(v -> {
            SheetClickOnEditRecycle sheetsCreateGroup = new SheetClickOnEditRecycle();
            sheetsCreateGroup.show(context.getSupportFragmentManager(), "TAG1");
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
