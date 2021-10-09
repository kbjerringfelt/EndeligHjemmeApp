package dk.au.au339038.bachelorprojekt.endelighjemmeapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PsychAdapter extends  RecyclerView.Adapter<PsychAdapter.PsychViewHolder>{

    private ArrayList<IMHP> mhpList;
    private IPsychItemClickedListener psychListener;


    public PsychAdapter(IPsychItemClickedListener psychListener){
        this.psychListener = psychListener;
    }

    public void updateCouncellorList(ArrayList<IMHP> lists){
        mhpList = lists;
        notifyDataSetChanged();
    }



    @NonNull
    @Override
    public PsychViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.psych_item, parent, false);
        PsychViewHolder mvh = new PsychViewHolder(v, psychListener);
        return mvh;
    }

    @Override
    public void onBindViewHolder(@NonNull PsychViewHolder viewHolder, int position) {
        viewHolder.name.setText(mhpList.get(position).getName());
        viewHolder.specialty.setText("" + mhpList.get(position).getYear());
        viewHolder.insurance.setText("");
        viewHolder.zip.setText("");

    }

    @Override
    public int getItemCount() {
        return mhpList.size();
    }

    //Lavet ud fra PersonViewHolder i Lists and grids demoen
    public class PsychViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView name;
        TextView specialty;
        TextView zip;
        TextView insurance;

        IPsychItemClickedListener psychListener;

        public PsychViewHolder(@NonNull View itemView, IPsychItemClickedListener psychItemClickedListener) {
            super(itemView);

            name = itemView.findViewById(R.id.name_input);
            specialty = itemView.findViewById(R.id.specialty_input);
            zip = itemView.findViewById(R.id.zipcode_input);
            insurance = itemView.findViewById(R.id.insurance_input);

            psychListener = psychItemClickedListener;
            itemView.setOnClickListener(this);

        }
        @Override
        public void onClick(View view) {
            psychListener.onMovieClicked(getAdapterPosition());
        }
    }

    public static interface IPsychItemClickedListener {
        void onMovieClicked(int index);
    }
}
