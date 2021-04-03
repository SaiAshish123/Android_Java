package com.example.databinding.model;

import android.content.SharedPreferences;

import androidx.lifecycle.MutableLiveData;
import com.example.databinding.network.AlbumsDataService;
import com.example.databinding.network.RetrofitClient;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlbumRepository {
  private static final String TAG = "EmployeeRepository";
  private ArrayList<Albums> albums = new ArrayList<>();
  private MutableLiveData<List<Albums>> mutableLiveData = new MutableLiveData<>();


  public AlbumRepository() {
  }

  public MutableLiveData<List<Albums>> getMutableLiveData() {

    final AlbumsDataService userDataService = RetrofitClient.getService();

    Call<List<Albums>> call = userDataService.getEmployees();
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
