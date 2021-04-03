package com.example.databinding;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProviders;

import com.example.databinding.model.AlbumRepository;
import com.example.databinding.model.Albums;
import com.example.databinding.network.AlbumsDataService;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;

public class MainViewModelTest {


    @Mock
    MainViewModel mainViewModel;
    @Mock
    AlbumsDataService albumsDataService;
    @Mock
    AlbumRepository albumRepository;
    @Mock
    MutableLiveData<List<Albums>> mutableLiveData ;
    @Mock
    Application application;
    @Mock
    MainActivity mainActivity;
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

 mainViewModel = new MainViewModel(application);

    }
    @Test
    public void getAllAlbums() throws Exception{

        List<Albums> albumsList = new ArrayList<>();
        Albums albums1 = new Albums();
        albums1.setId(1L);
        albums1.setTitle("Test");
        albums1.setUserId(2L);
      albumsList .add(albums1);

        mutableLiveData.setValue(albumsList);
        LiveData<List<Albums>> albums =   albumRepository.getMutableLiveData();


        Mockito.when(mainViewModel.getAllAlbums()).thenReturn(albums);
       // assertEquals("Test",mutableLiveData.getValue().get(0).getTitle());


    }

    @Test
    public void getSortedTest() throws Exception{

        List<Albums> albumsList = new ArrayList<>();
        Albums albums1 = new Albums();
        albums1.setId(1L);
        albums1.setTitle("Test");
        albums1.setUserId(2L);
        albumsList .add(albums1);
        Albums albums2 = new Albums();
        albums2.setId(1L);
        albums2.setTitle("Alex");
        albums2.setUserId(2L);
        albumsList .add(albums2);

         mainActivity.setArraylist(albumsList);
        // assertEquals("Test",albums.getValue().get(0).getTitle());


    }
}