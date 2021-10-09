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

    private ArrayList<ICounsellor> counsellorList;
    private IPsychItemClickedListener psychListener;


    public PsychAdapter(IPsychItemClickedListener psychListener){
        this.movieListener = movieListener;
    }

    public void updateMovieList(ArrayList<ICounsellor> lists){
        counsellorList = lists;
        notifyDataSetChanged();
    }



    @NonNull
    @Override
    public PsychViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rcv_item, parent, false);
        PsychViewHolder mvh = new PsychViewHolder(v, psychListener);
        return mvh;
    }

    @Override
    public void onBindViewHolder(@NonNull PsychViewHolder viewHolder, int position) {
        viewHolder.title.setText(movieList.get(position).getTitle());
        viewHolder.year.setText("" + movieList.get(position).getYear());
        viewHolder.rating.setText("" + movieList.get(position).getImdbRating());


        if(movieList.get(position).getGenre() == "Action"){
            viewHolder.itemImage.setImageResource(R.drawable.ic_action);

        }
        else if(movieList.get(position).getGenre() == "Comedy"){
            viewHolder.itemImage.setImageResource(R.drawable.ic_comedy);
        }
        else if(movieList.get(position).getGenre() == "Drama"){
            viewHolder.itemImage.setImageResource(R.drawable.ic_drama);
        }
        else if(movieList.get(position).getGenre() == "Horror"){
            viewHolder.itemImage.setImageResource(R.drawable.ic_horror);
        }
        else if(movieList.get(position).getGenre() == "Romance"){
            viewHolder.itemImage.setImageResource(R.drawable.ic_romance);
        }
        else if(movieList.get(position).getGenre() == "Western"){
            viewHolder.itemImage.setImageResource(R.drawable.ic_western);
        }
        else {
            viewHolder.itemImage.setImageResource(R.drawable.ic_resource_default);
        }
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    //Lavet ud fra PersonViewHolder i Lists and grids demoen
    public class PsychViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView itemImage;
        TextView title;
        TextView year;
        TextView rating;
        IMovieItemClickedListener movieListener;

        public PsychViewHolder(@NonNull View itemView, IMovieItemClickedListener movieItemClickedListener) {
            super(itemView);

            itemImage = itemView.findViewById(R.id.item_image);
            title = itemView.findViewById(R.id.item_title);
            year = itemView.findViewById(R.id.item_year);
            rating = itemView.findViewById(R.id.item_rate);

            movieListener = movieItemClickedListener;
            itemView.setOnClickListener(this);

        }
        @Override
        public void onClick(View view) {
            movieListener.onMovieClicked(getAdapterPosition());
        }
    }

    public static interface IMovieItemClickedListener {
        void onMovieClicked(int index);
    }
}
