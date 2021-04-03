package com.example.databinding.adapter;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.databinding.MainActivity;
import com.example.databinding.R;
import com.example.databinding.databinding.AlbumListItemBinding;

import com.example.databinding.model.Albums;
import com.google.gson.Gson;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class AlbumsDataAdapter
    extends RecyclerView.Adapter<AlbumsDataAdapter.EmployeeViewHolder> {

  SharedPreferences sharedPrefs;
  private ArrayList<Albums> albums;

  public AlbumsDataAdapter(MainActivity mainActivity) {
    sharedPrefs  = PreferenceManager.getDefaultSharedPreferences(mainActivity);



  }

  @NonNull
  @Override
  public EmployeeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
    AlbumListItemBinding employeeListItemBinding =
        DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()),
            R.layout.album_list_item, viewGroup, false);
    return new EmployeeViewHolder(employeeListItemBinding);
  }

  @Override
  public void onBindViewHolder(@NonNull EmployeeViewHolder employeeViewHolder, int i) {
    Albums currentStudent = albums.get(i);
    employeeViewHolder.employeeListItemBinding.setAlbums(currentStudent);
  }

  @Override
  public int getItemCount() {
    if (albums != null) {
      return albums.size();
    } else {
      return 0;
    }
  }

  public void setEmployeeList(ArrayList<Albums> albums) {

    this.albums = albums;
    SharedPreferences.Editor editor = sharedPrefs.edit();
    Gson gson = new Gson();
    String json = gson.toJson(albums);

    editor.putString("LST", json);
    editor.commit();
    notifyDataSetChanged();
  }

  class EmployeeViewHolder extends RecyclerView.ViewHolder {

    private AlbumListItemBinding employeeListItemBinding;

    public EmployeeViewHolder(@NonNull AlbumListItemBinding employeetListItemBinding) {
      super(employeetListItemBinding.getRoot());

      this.employeeListItemBinding = employeetListItemBinding;
    }
  }
}
