package hu.bme.aut.filmesadatbazis.adapter;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import hu.bme.aut.filmesadatbazis.AllOwnListActivity;
import hu.bme.aut.filmesadatbazis.R;
import hu.bme.aut.filmesadatbazis.data.OwnList;

public class OwnListAdapter extends RecyclerView.Adapter<OwnListAdapter.OwnListViewHolder> {

    private final List<OwnList> ownLists;

    private OwnListClickListener listener;

    public OwnListAdapter(OwnListClickListener listener) {
        this.listener = listener;
        ownLists = new ArrayList<>();
    }

    public interface OwnListClickListener{
        void onDataClicked(OwnList ownList);
        void onItemDeleted(OwnList ownList);
    }

    @NonNull
    @Override
    public OwnListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_own_list, parent, false);
        return new OwnListViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull OwnListViewHolder holder, int position) {
        final OwnList item = ownLists.get(position);
        holder.nameTextView.setText(item.name);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onDataClicked(item); }
        });

        holder.item=item;
    }

    @Override
    public int getItemCount() {
        return ownLists.size();
    }

    class OwnListViewHolder extends RecyclerView.ViewHolder{

        TextView nameTextView;
        ImageButton removeButton;

        OwnList item;

        OwnListViewHolder(View ownListView) {
            super(ownListView);

            nameTextView = itemView.findViewById(R.id.OwnListItemNameTextView);
            removeButton = itemView.findViewById(R.id.OwnListItemRemoveButton);

            //TODO:FILMLISTA BUTTON

            removeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    deleteItem(getLayoutPosition());
                }
            });


        }
    }

    public void update(List<OwnList> ownListList) {
        ownLists.clear();
        ownLists.addAll(ownListList);
        notifyDataSetChanged();
    }

    public void deleteItem(int position) {
        OwnList toRemove = ownLists.remove(position);
        notifyItemRemoved(position);
        listener.onItemDeleted(toRemove);
    }
}
