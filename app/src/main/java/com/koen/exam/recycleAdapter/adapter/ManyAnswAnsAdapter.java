package com.koen.exam.recycleAdapter.adapter;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.koen.exam.R;
import com.koen.exam.model.OneAnsInfo;

import java.util.List;

public class ManyAnswAnsAdapter extends RecyclerView.Adapter<ManyAnswAnsAdapter.ViewHolder> {
    private List<OneAnsInfo> oneAnsInfos;

    FragmentActivity context;
    private ManyAnswAnsAdapter.OnItemClickListener mListener;
    private ManyAnswAnsAdapter.OnItemChangeListener itemChangeListener;

    public void setOnItemClickListener(ManyAnswAnsAdapter.OnItemClickListener onItemClickListener){
        mListener = onItemClickListener;
    }
    public void setInItemChangedListener(ManyAnswAnsAdapter.OnItemChangeListener onItemChangeListener){
        this.itemChangeListener = onItemChangeListener;
    }

    public interface OnItemChangeListener{

        void onEditTextChangeListener(int position, String text);
    }
    public interface OnItemClickListener{

        void onCheckBoxClick(int position);
    }
    public ManyAnswAnsAdapter(List<OneAnsInfo> oneAnsInfos, FragmentActivity context){
        this.oneAnsInfos = oneAnsInfos;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder  {
        TextView textViewAns;
        CheckBox trueAns;
        public ViewHolder(@NonNull View itemView, final ManyAnswAnsAdapter.OnItemClickListener onItemClickListener, final ManyAnswAnsAdapter.OnItemChangeListener onItemChangeListener) {
            super(itemView);
            textViewAns = itemView.findViewById(R.id.textViewAns);
            trueAns = itemView.findViewById(R.id.checkBoxAnsAnsw);

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
        }
    }

    @NonNull
    @Override
    public ManyAnswAnsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_many_answ_ans,parent,false);
        return new ManyAnswAnsAdapter.ViewHolder(view, mListener,itemChangeListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ManyAnswAnsAdapter.ViewHolder holder, int position) {
        OneAnsInfo oneAnsInfo = oneAnsInfos.get(position);
        holder.textViewAns.setText(oneAnsInfo.getAnswer());
        if(oneAnsInfo.getTrueAns()!=null) {
            holder.trueAns.setChecked(oneAnsInfo.getTrueAns());
        }


    }


    @Override
    public int getItemCount() {
        return oneAnsInfos.size();
    }
}
