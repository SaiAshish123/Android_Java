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

/**
 * @author Asish
 */
public class AlbumsDataAdapter
    extends RecyclerView.Adapter<AlbumsDataAdapter.EmployeeViewHolder> {

  SharedPreferences sharedPrefs;
  private ArrayList<Albums> albums;

  /**
   * Using contrext param shared preference are intialized
   * @param mainActivity
   */
  public AlbumsDataAdapter(MainActivity mainActivity) {
    sharedPrefs  = PreferenceManager.getDefaultSharedPreferences(mainActivity);



  }

  /**
   * Creating View Group Object for Custom layout
   * @param viewGroup
   * @param i
   * @return
   */
  @NonNull
  @Override
  public EmployeeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
    AlbumListItemBinding employeeListItemBinding =
        DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()),
            R.layout.album_list_item, viewGroup, false);
    return new EmployeeViewHolder(employeeListItemBinding);
  }

  /**
   * Creating OnBindView Holder which reference the Object position
   * @param employeeViewHolder
   * @param i
   */
  @Override
  public void onBindViewHolder(@NonNull EmployeeViewHolder employeeViewHolder, int i) {
    Albums currentStudent = albums.get(i);
    employeeViewHolder.employeeListItemBinding.setAlbums(currentStudent);
  }

  /**
   * Get the Item Count of Arraylist
   * @return
   */
  @Override
  public int getItemCount() {
    if (albums != null) {
      return albums.size();
    } else {
      return 0;
    }
  }

  /**
   *  Setting the Album list into Adapter
   * @param albums
   */
  public void setAlbumList(ArrayList<Albums> albums) {

    this.albums = albums;
    /**
     * Storing the list of Objects using Gson
     */
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
