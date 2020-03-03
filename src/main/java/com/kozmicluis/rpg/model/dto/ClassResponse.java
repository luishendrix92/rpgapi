package com.kozmicluis.rpg.model.dto;

public class ClassResponse {
  private long id;
  private String name;
  private String imgUrl;
  private String description;
  private int baseHp;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getImgUrl() {
    return imgUrl;
  }

  public void setImgUrl(String imgUrl) {
    this.imgUrl = imgUrl;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public int getBaseHp() {
    return baseHp;
  }

  public void setBaseHp(int baseHp) {
    this.baseHp = baseHp;
  }
}
