package com.koen.exam.recycleAdapter.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.koen.exam.DataSingleton;
import com.koen.exam.R;
import com.koen.exam.model.ExamModel;
import com.koen.exam.views.Impl.FragmentPassingTest;
import com.koen.exam.views.dialogs.SheetOnClickExam;

import java.util.List;

public class SubsExamsAdapter extends RecyclerView.Adapter<SubsExamsAdapter.ViewHolder> {
    List<ExamModel> itemList;
    FragmentActivity context;
    private SubsExamsAdapter.OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(SubsExamsAdapter.OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public SubsExamsAdapter(List<ExamModel> list, FragmentActivity context){
        this.itemList = list;
        this.context = context;
    }

    @NonNull
    @Override
    public SubsExamsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.exam_card,parent,false);
        return new SubsExamsAdapter.ViewHolder(view, onItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull SubsExamsAdapter.ViewHolder holder, int position) {
        ExamModel itemModel = itemList.get(position);
        holder.titleExam.setText(itemModel.getTitle());
        holder.descExam.setText(itemModel.getDescription());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.scrim,new FragmentPassingTest((long)itemModel.getId())).commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleExam, descExam;
        CardView cardView;
        public ViewHolder(@NonNull View itemView, final SubsExamsAdapter.OnItemClickListener onItemClickListener) {
            super(itemView);
            titleExam = itemView.findViewById(R.id.examTitle);
            descExam = itemView.findViewById(R.id.examDesc);
            cardView = itemView.findViewById(R.id.cardExam);
        }
    }
}
