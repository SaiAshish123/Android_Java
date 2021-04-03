
package com.example.databinding.model;

import android.widget.ImageView;
import androidx.databinding.BindingAdapter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.databinding.R;
import com.google.gson.annotations.SerializedName;

public class Albums {

  @SerializedName("userId")
  private Long userId;
  @SerializedName("id")
  private Long id;
  @SerializedName("title")
  private String title;

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  // important code for loading image here
  @BindingAdapter({ "avatar" })
  public static void loadImage(ImageView imageView, String imageURL) {

    Glide.with(imageView.getContext())
        .setDefaultRequestOptions(new RequestOptions()
            .circleCrop())
        .load(imageURL)
        .placeholder(R.drawable.loading)
        .into(imageView);
  }
}
