//package com.example.ivanyulincal;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import org.json.JSONArray;
//import org.json.JSONObject;
//
//class PlaylistsAdapter extends RecyclerView.Adapter<ItemSetter> {
//    private JSONArray playlistsList;
//
//    @NonNull
//    @Override
//    public ItemSetter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.playlist_item, parent, false);
//        return new ItemSetter(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ItemSetter holder, int position) {
//        try {
//            holder.setViews((JSONObject) playlistsList.get(position));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public int getItemCount() {
//        return playlistsList == null ? 0 : playlistsList.length();
//    }
//
//    void setList(JSONArray playlistsList) {
//        this.playlistsList = playlistsList;
//        notifyDataSetChanged();
//    }
//}
//
////note: changed to ExpandableListView