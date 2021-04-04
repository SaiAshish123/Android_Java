package com.example.databinding;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.databinding.adapter.AlbumsDataAdapter;
import com.example.databinding.databinding.ActivityMainBinding;
import com.example.databinding.model.Albums;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Ashish
 */
public class MainActivity extends AppCompatActivity {
  private MainViewModel mainViewModel;
  private AlbumsDataAdapter employeeDataAdapter;
  boolean connected = false;

  /**
   * Oncreate Started
   * @param savedInstanceState
   */
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ActivityMainBinding activityMainBinding =
        DataBindingUtil.setContentView(this, R.layout.activity_main);

    // bind RecyclerView
    RecyclerView recyclerView = activityMainBinding.viewEmployees;
    recyclerView.setLayoutManager(new LinearLayoutManager(this));
    recyclerView.setHasFixedSize(true);

    /**
     * Passing the Context to the View Model Providers
     */
    mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
    /**
     * Setting the Adapter
     */
    employeeDataAdapter = new AlbumsDataAdapter(MainActivity.this);
    recyclerView.setAdapter(employeeDataAdapter);

    /**
     * Checking the Internet Conmnectivity
     */
    ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
    if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
            connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
      //we are connected to a network
      connected = true;
    }
    else
      connected = false;
    /**
     * Getting data from Offline LST Object using shared preference
     */

    SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
    Gson gson = new Gson();
    String json = sharedPrefs.getString("LST", "");
    Type type = new TypeToken<List<Albums>>() {}.getType();
    List<Albums> albums = gson.fromJson(json, type);
    if (!connected){
      Toast.makeText(this, "No Internet Connection Available !!!", Toast.LENGTH_SHORT).show();
      employeeDataAdapter.setAlbumList((ArrayList<Albums>) albums);
    }else{
      /**
       * If Network Available Call the APi
       */
      getAllAlbums();
    }

  }

  /**
   * Updating the Albums Arraylist into Adapter using view model Object
   */
  private void getAllAlbums() {
    mainViewModel.getAllAlbums().observe(this, new Observer<List<Albums>>() {
      @Override
      public void onChanged(@Nullable List<Albums> albums) {

        setArraylist(albums);
      }
    });


  }

  /**
   * Sorting the Array list using Java 8 Comparator with lambda xpression for title Object
   * @param albums
   */
  public void setArraylist(List<Albums> albums) {
    Collections.sort(albums,(o1, o2) -> o1.getTitle().compareTo(o2.getTitle()));
    employeeDataAdapter.setAlbumList((ArrayList<Albums>) albums);
  }


}
