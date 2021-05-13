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
import com.koen.exam.views.Impl.FragmentResultsGroup;
import com.koen.exam.views.dialogs.SheetOnClickExam;

import java.util.List;

public class AdapterExamListResults extends RecyclerView.Adapter<AdapterExamListResults.ViewHolder>{
    List<ExamModel> itemList;
    FragmentActivity context;
    String idCourse;
    private AdapterExamListResults.OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(AdapterExamListResults.OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public AdapterExamListResults(List<ExamModel> list, FragmentActivity context, String idCourse){
        this.itemList = list;
        this.context = context;
        this.idCourse = idCourse;
    }

    @NonNull
    @Override
    public AdapterExamListResults.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.exam_card,parent,false);
        return new AdapterExamListResults.ViewHolder(view, onItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterExamListResults.ViewHolder holder, int position) {
        ExamModel itemModel = itemList.get(position);
        holder.titleExam.setText(itemModel.getTitle());
        holder.descExam.setText(itemModel.getDescription());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataSingleton.getInstance().descTest = itemModel.getDescription();
                DataSingleton.getInstance().titleTest = itemModel.getTitle();
                DataSingleton.getInstance().idTest =  itemModel.getId();
                context.getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.scrim, new FragmentResultsGroup(idCourse,itemModel.getId()))
                        .addToBackStack(null)
                        .commit();
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
        public ViewHolder(@NonNull View itemView, final AdapterExamListResults.OnItemClickListener onItemClickListener) {
            super(itemView);
            titleExam = itemView.findViewById(R.id.examTitle);
            descExam = itemView.findViewById(R.id.examDesc);
            cardView = itemView.findViewById(R.id.cardExam);


        }
    }
}
