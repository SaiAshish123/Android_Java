package com.example.databinding.network;

import com.example.databinding.model.Albums;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Call the Method to get the list of Albums
 */
public interface AlbumsDataService {
  @GET("albums")
  Call<List<Albums>> getAlbums();
}
