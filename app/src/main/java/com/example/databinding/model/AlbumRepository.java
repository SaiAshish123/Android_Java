package com.example.databinding.model;

import androidx.lifecycle.MutableLiveData;
import com.example.databinding.network.AlbumsDataService;
import com.example.databinding.network.RetrofitClient;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author Asish
 */
public class AlbumRepository {

  /**
   * Declaring the Variables
   */
  private ArrayList<Albums> albums = new ArrayList<>();
  private MutableLiveData<List<Albums>> mutableLiveData = new MutableLiveData<>();


  /**
   * Creating default Constructor
   */
  public AlbumRepository() {
  }

  /**
   * Get Mutable Live data from  API thread using call back retrofit function
   * @return
   */
  public MutableLiveData<List<Albums>> getMutableLiveData() {

    final AlbumsDataService userDataService = RetrofitClient.getService();

    Call<List<Albums>> call = userDataService.getAlbums();
    call.enqueue(new Callback<List<Albums>>() {
      @Override
      public void onResponse(Call<List<Albums>> call, Response<List<Albums>> response) {
        List<Albums> albumsDBResponse = response.body();
        if (albumsDBResponse != null) {
          albums = (ArrayList<Albums>) albumsDBResponse;
          mutableLiveData.setValue(albums);
        }
      }

      @Override
      public void onFailure(Call<List<Albums>> call, Throwable t) {
      }
    });

    return mutableLiveData;
  }
}
