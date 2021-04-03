
package com.example.databinding.model;


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


}
