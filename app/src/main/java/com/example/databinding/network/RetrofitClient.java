package com.example.databinding.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author  Ashish
 * This class is used to Call the End point Url using retrofit instance object
 */
public class RetrofitClient {
  private static Retrofit retrofit;
  private static final String BASE_URL = "https://jsonplaceholder.typicode.com/";

  /**
   * Creating Single ton Static Class Object
   * @return
   */
  public static AlbumsDataService getService() {
    if (retrofit == null) {
      retrofit = new Retrofit
          .Builder()
          .baseUrl(BASE_URL)
          .addConverterFactory(GsonConverterFactory.create())
          .build();
    }

    return retrofit.create(AlbumsDataService.class);
  }
}