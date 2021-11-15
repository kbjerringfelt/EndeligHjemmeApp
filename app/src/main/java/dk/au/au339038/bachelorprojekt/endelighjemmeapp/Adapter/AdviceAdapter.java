package dk.au.au339038.bachelorprojekt.endelighjemmeapp.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import dk.au.au339038.bachelorprojekt.endelighjemmeapp.DTO.Advice;
import dk.au.au339038.bachelorprojekt.endelighjemmeapp.R;

public class AdviceAdapter extends  RecyclerView.Adapter<AdviceAdapter.AdviceViewHolder>{

    private ArrayList<Advice> adviceList;
    private AdviceAdapter.IAdviceItemClickedListener adviceListener;


    public AdviceAdapter(AdviceAdapter.IAdviceItemClickedListener adviceListener){
        this.adviceListener = adviceListener;
    }

    public void updateAdviceList(ArrayList<Advice> lists){
        adviceList = lists;
        notifyDataSetChanged();
    }



    @NonNull
    @Override
    public AdviceAdapter.AdviceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.advice_item, parent, false);
        AdviceAdapter.AdviceViewHolder avh = new AdviceAdapter.AdviceViewHolder(v, adviceListener);
        return avh;
    }

    @Override
    public void onBindViewHolder(@NonNull AdviceAdapter.AdviceViewHolder viewHolder, int position) {
        viewHolder.title.setText(adviceList.get(position).getTitle());

    }

    @Override
    public int getItemCount() {
        if(adviceList == null){
            return 0;
        } else {
            return adviceList.size();
        }
    }

    //Lavet ud fra PersonViewHolder i Lists and grids demoen
    public class AdviceViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView title;

        AdviceAdapter.IAdviceItemClickedListener adviceListener;

        public AdviceViewHolder(@NonNull View itemView, AdviceAdapter.IAdviceItemClickedListener adviceItemClickedListener) {
            super(itemView);

            title = itemView.findViewById(R.id.title_advice);

            adviceListener = adviceItemClickedListener;
            itemView.setOnClickListener(this);

        }
        @Override
        public void onClick(View view) {
            adviceListener.onAdviceClicked(getAdapterPosition());
        }
    }

    public static interface IAdviceItemClickedListener {
        void onAdviceClicked(int index);
    }
}
