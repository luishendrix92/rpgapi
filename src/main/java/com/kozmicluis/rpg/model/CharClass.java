package com.kozmicluis.rpg.model;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "CharClass")
public class CharClass implements Serializable {
  private static final long serialVersionUID = 3L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @OneToMany(mappedBy = "charClass", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
  private List<Character> implementers = new ArrayList<Character>();

  @Column(nullable = false)
  private String name = "Generic Class";

  @Column(nullable = false)
  private String imgUrl = "defaultclass.svg";

  @Column(nullable = false)
  private String description = "Nothing special about it...";

  @Column(nullable = false)
  @Min(1)
  @Max(500)
  private int baseHp = 50;

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

  public List<Character> getImplementers() {
    return implementers;
  }

  public void setImplementers(List<Character> implementers) {
    this.implementers = implementers;
  }

  @PreRemove
  private void preRemove() {
    implementers.forEach(character -> character.setCharClass(null));
  }
}
