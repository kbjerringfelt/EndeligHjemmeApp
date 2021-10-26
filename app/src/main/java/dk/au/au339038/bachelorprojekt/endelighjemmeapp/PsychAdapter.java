package dk.au.au339038.bachelorprojekt.endelighjemmeapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import dk.au.au339038.bachelorprojekt.endelighjemmeapp.DTO.IMHP;
import dk.au.au339038.bachelorprojekt.endelighjemmeapp.DTO.Psychologist;

public class PsychAdapter extends  RecyclerView.Adapter<PsychAdapter.PsychViewHolder>{

    private ArrayList<Psychologist> mhpList;
    private IPsychItemClickedListener psychListener;


    public PsychAdapter(IPsychItemClickedListener psychListener){
        this.psychListener = psychListener;
    }

    public void updateMHPList(ArrayList<Psychologist> lists){
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
        viewHolder.insurance.setText(mhpList.get(position).getInsurance());
        viewHolder.zip.setText("" + mhpList.get(position).getZip());
        viewHolder.specialty.setText(mhpList.get(position).getSpecialty());

    }

    @Override
    public int getItemCount() {
        if(mhpList == null){
            return 0;
        } else {
            return mhpList.size();
        }
    }

    //Lavet ud fra PersonViewHolder i Lists and grids demoen
    public class PsychViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView name, specialty, zip, insurance;

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
            psychListener.onPsychologistClicked(getAdapterPosition());
        }
    }

    public static interface IPsychItemClickedListener {
        void onPsychologistClicked(int index);
    }
}
