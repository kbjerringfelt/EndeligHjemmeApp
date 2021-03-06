package dk.au.au339038.bachelorprojekt.endelighjemmeapp.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import dk.au.au339038.bachelorprojekt.endelighjemmeapp.DTO.Group;
import dk.au.au339038.bachelorprojekt.endelighjemmeapp.R;


//adapter til recyclerview for grupper
public class GroupAdapter extends  RecyclerView.Adapter<GroupAdapter.GroupViewHolder>{
    private ArrayList<Group> groupList;
    private GroupAdapter.IGroupItemClickedListener groupListener;


    public GroupAdapter(GroupAdapter.IGroupItemClickedListener groupListener){
        this.groupListener = groupListener;
    }

    //opdaterer listen i recyclerview
    public void updateGroupList(ArrayList<Group> lists){
        groupList = lists;
        notifyDataSetChanged();
    }



    //Sætter layout til item i recyclerview
    @NonNull
    @Override
    public GroupAdapter.GroupViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.group_item, parent, false);
        GroupAdapter.GroupViewHolder gvh = new GroupAdapter.GroupViewHolder(v, groupListener);
        return gvh;
    }

    //Putter data fra listen i layout
    @Override
    public void onBindViewHolder(@NonNull GroupAdapter.GroupViewHolder viewHolder, int position) {
        viewHolder.title.setText(groupList.get(position).getTitle());
        viewHolder.bdate.setText(groupList.get(position).getBdate());
    }

    //Antal i listen
    @Override
    public int getItemCount() {
        if(groupList == null){
            return 0;
        } else {
            return groupList.size();
        }
    }

    //Binder widgets til layout og siger hvad der skal ske ved klik
    public class GroupViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView title, bdate;

        GroupAdapter.IGroupItemClickedListener groupListener;

        public GroupViewHolder(@NonNull View itemView, GroupAdapter.IGroupItemClickedListener groupItemClickedListener) {
            super(itemView);

            title = itemView.findViewById(R.id.group_txt);
            bdate = itemView.findViewById(R.id.bdate_input);


            groupListener = groupItemClickedListener;
            itemView.setOnClickListener(this);

        }
        @Override
        public void onClick(View view) {
            groupListener.onGroupClicked(getAdapterPosition());
        }
    }

    public static interface IGroupItemClickedListener {
        void onGroupClicked(int index);
    }
}
