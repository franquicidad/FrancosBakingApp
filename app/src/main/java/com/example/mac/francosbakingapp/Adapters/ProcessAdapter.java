package com.example.mac.francosbakingapp.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mac.francosbakingapp.Model.Process;
import com.example.mac.francosbakingapp.R;

import java.util.List;

public class ProcessAdapter extends RecyclerView.Adapter<ProcessAdapter.ProcessAdapterViewHolder> {

    private List<Process> mProcessList;
    Context mContext;
    onProcessAdapterClickHandler mProcessClickHandler;



    public interface onProcessAdapterClickHandler{
        void onProcessClicked(List<Process> processList,int position);
    }

    public ProcessAdapter(List<Process> mProcessList, onProcessAdapterClickHandler mProcessClickHandler) {
        this.mProcessList = mProcessList;
        this.mProcessClickHandler = mProcessClickHandler;
    }

    @NonNull
    @Override
    public ProcessAdapter.ProcessAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext=parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View itemView = inflater.inflate(R.layout.process_layout, parent, false);
        return new ProcessAdapter.ProcessAdapterViewHolder(itemView);

    }



    @Override
    public int getItemCount() {
        if (mProcessList == null) {
            return 0;
        } else {
            return mProcessList.size();

        }
    }

    public class ProcessAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView process_tv, process_recipe_step,txt_exo_player_null;

        public ProcessAdapterViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            process_tv=itemView.findViewById(R.id.process_textView);
            process_recipe_step=itemView.findViewById(R.id.process_number);
            txt_exo_player_null=itemView.findViewById(R.id.exoplayer_null);

        }

        @Override
        public void onClick(View view) {
            mProcessClickHandler.onProcessClicked(mProcessList ,getAdapterPosition());

        }
    }

    @Override
    public void onBindViewHolder(@NonNull ProcessAdapter.ProcessAdapterViewHolder holder, int position) {

        Process procObject=mProcessList.get(position);
        holder.process_tv.setText(procObject.getShortDescription());
        holder.process_recipe_step.setText(String.valueOf(procObject.getId()));


    }
}
