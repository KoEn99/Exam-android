package com.koen.exam.recycleAdapter.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.koen.exam.R;
import com.koen.exam.model.OneAnsInfo;
import com.koen.exam.model.QuestionData;

import java.util.List;

public class AnswersAdapter extends RecyclerView.Adapter<AnswersAdapter.ViewHolder> {
    private List<OneAnsInfo> oneAnsInfos;

    FragmentActivity context;
    private OnItemClickListener mListener;
    private OnItemChangeListener itemChangeListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        mListener = onItemClickListener;
    }
    public void setInItemChangedListener(OnItemChangeListener onItemChangeListener){
        this.itemChangeListener = onItemChangeListener;
    }

    public interface OnItemChangeListener{

        void onEditTextChangeListener(int position, String text);
    }
    public interface OnItemClickListener{

        void onCheckBoxClick(int position);
    }
    public AnswersAdapter(List<OneAnsInfo> oneAnsInfos, FragmentActivity context){
        this.oneAnsInfos = oneAnsInfos;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder  {
        EditText editAns;
        CheckBox trueAns;
        public ViewHolder(@NonNull View itemView, final OnItemClickListener onItemClickListener, final OnItemChangeListener onItemChangeListener) {
            super(itemView);
            editAns = itemView.findViewById(R.id.editAns);
            trueAns = itemView.findViewById(R.id.checkBoxAns);

            trueAns.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onItemClickListener != null){
                        int position = getAdapterPosition();
                        if(position!=RecyclerView.NO_POSITION){
                            onItemClickListener.onCheckBoxClick(position);
                        }
                    }
                }
            });
            editAns.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    if(onItemChangeListener != null){
                        int position = getAdapterPosition();
                        String text = editAns.getText().toString();
                        if(position!=RecyclerView.NO_POSITION){
                            onItemChangeListener.onEditTextChangeListener(position, text);
                        }
                    }
                }
            });
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.one_ans_card_rec,parent,false);
        return new ViewHolder(view, mListener,itemChangeListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        OneAnsInfo oneAnsInfo = oneAnsInfos.get(position);
        holder.editAns.setText(oneAnsInfo.getAnswer());
        holder.trueAns.setChecked(oneAnsInfo.getTrueAns());
    }


    @Override
    public int getItemCount() {
        return oneAnsInfos.size();
    }
}
