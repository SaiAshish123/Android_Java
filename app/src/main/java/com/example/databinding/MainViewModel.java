package com.example.databinding;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.databinding.model.Albums;
import com.example.databinding.model.AlbumRepository;
import java.util.List;

public class MainViewModel extends AndroidViewModel {
  private AlbumRepository albumRepository;

  public MainViewModel(@NonNull Application application) {
    super(application);
    albumRepository = new AlbumRepository();
  }

  public LiveData<List<Albums>> getAllEmployee() {
    return albumRepository.getMutableLiveData();
  }
}
