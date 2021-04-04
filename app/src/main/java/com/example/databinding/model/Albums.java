
package com.example.databinding.model;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Albums implements Parcelable {

  @SerializedName("userId")
  private Long userId;
  @SerializedName("id")
  private Long id;
  @SerializedName("title")
  private String title;
  public Albums(Parcel in) {
    userId = in.readLong();
    id = in.readLong();
    title = in.readString();

  }

  public static final Creator<Albums> CREATOR = new Creator<Albums>() {
    @Override
    public Albums createFromParcel(Parcel in) {
      return new Albums(in);
    }

    @Override
    public Albums[] newArray(int size) {
      return new Albums[size];
    }
  };

  public Albums(Long userId, Long id, String title) {
    this.title = title;
    this.id = id;
    this.userId = userId;
  }

  public Albums() {

  }

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

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(title);
    dest.writeLong(userId);
    dest.writeLong(1L);

  }

  @Override
  public int describeContents() {
    return 0;
  }
}
