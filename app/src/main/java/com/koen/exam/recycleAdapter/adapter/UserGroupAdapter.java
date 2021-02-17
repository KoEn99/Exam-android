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
import com.koen.exam.model.UserGroup;
import com.koen.exam.views.Impl.ExamListFragment;
import com.koen.exam.views.dialogs.SheetClickOnEditRecycle;

import java.util.List;

public class UserGroupAdapter extends RecyclerView.Adapter<UserGroupAdapter.ViewHolder>{
    private List<UserGroup> userGroups;
    FragmentActivity context;

    public UserGroupAdapter(List<UserGroup> groupInfoList, FragmentActivity context) {
        this.context = context;
        this.userGroups = groupInfoList;
    }
    public void dataChanged(List<UserGroup> groupInfoList){
        this.userGroups = groupInfoList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserGroupAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_group_item, parent, false);
        return new UserGroupAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserGroupAdapter.ViewHolder holder, int position) {
        UserGroup userGroup = userGroups.get(position);
        holder.titleTextView.setText(userGroup.getLastName() + " "
                + userGroup.getFirstName() + " "
                + userGroup.getMiddleName());
        holder.descTextView.setText(userGroup.getEmail());
        holder.cardView.setOnClickListener(v -> {

        });
    }

    @Override
    public int getItemCount() {
        return userGroups.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder  {
        TextView titleTextView, descTextView;
        CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = (CardView)itemView.findViewById(R.id.card);
            titleTextView = (TextView)itemView.findViewById(R.id.nameUserGroupTextView);
            descTextView = (TextView)itemView.findViewById(R.id.emailUserGroupTextView);
        }
    }
}
