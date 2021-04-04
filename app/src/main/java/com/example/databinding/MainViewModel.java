package com.example.databinding;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.databinding.model.Albums;
import com.example.databinding.model.AlbumRepository;
import java.util.List;

/**
 * @author  Ashish
 * Thic class is sued to Get The Live data from Repositiory using Android View Model
 */
public class MainViewModel extends AndroidViewModel {
  private AlbumRepository albumRepository;

  /**
   * Passing the Application context to main View model in Constructor
   * @param application
   */
  public MainViewModel(@NonNull Application application) {
    super(application);
    albumRepository = new AlbumRepository();
  }

  /**
   * Getting all the  Live data objects from Mutable Live data using reositiory
   * @return
   */
  public LiveData<List<Albums>> getAllAlbums() {
    return albumRepository.getMutableLiveData();
  }
}
