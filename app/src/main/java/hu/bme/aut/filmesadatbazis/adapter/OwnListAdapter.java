package hu.bme.aut.filmesadatbazis.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import hu.bme.aut.filmesadatbazis.R;
import hu.bme.aut.filmesadatbazis.data.Movie;
import hu.bme.aut.filmesadatbazis.data.OwnList;

public class OwnListAdapter extends RecyclerView.Adapter<OwnListAdapter.OwnListViewHolder> {

    private final List<OwnList> ownLists;

    private OwnListClickListener listener;

    public OwnListAdapter(OwnListClickListener listener) {
        this.listener = listener;
        ownLists = new ArrayList<>();
    }

    @NonNull
    @Override
    public OwnListAdapter.OwnListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_own_list, parent, false);
        return new OwnListAdapter.OwnListViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull OwnListAdapter.OwnListViewHolder ownListViewHolder, int i) {
        //TODO: ide kellenek az adatbázisbol a dolgok

    }

    @Override
    public int getItemCount() {
        return ownLists.size();
    }

    public interface MovieClickListener{
        void onItemChanged(Movie movie);
    }

    public interface OwnListClickListener {
        void onItemChanged(OwnList ownList);
    }

    class OwnListViewHolder extends RecyclerView.ViewHolder{
        OwnListViewHolder(View ownListView) {
            super(ownListView);
        }

        OwnList item;
    }

    public void addItem(OwnList item) {
        ownLists.add(item);
        notifyItemInserted(ownLists.size() - 1);
    }

    public void update(List<OwnList> ownListList) {
        ownLists.clear();
        ownLists.addAll(ownListList);
        notifyDataSetChanged();
    }
}
