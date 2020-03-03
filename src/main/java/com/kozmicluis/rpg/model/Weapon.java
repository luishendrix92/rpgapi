package com.kozmicluis.rpg.model;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Weapon")
public class Weapon implements Serializable {
  private static final long serialVersionUID = 2L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @OneToMany(mappedBy = "weapon", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
  private List<Character> wielders = new ArrayList<Character>();

  @Column(nullable = false)
  private String name = "Nameless Weapon";

  @Column(nullable = false)
  private String imgUrl = "defaultwep.svg";

  @Column(nullable = false)
  private String description = "A mysterious weapon!";

  @Column(nullable = false)
  @Min(1)
  @Max(100)
  private int power = 1;

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

  public int getPower() {
    return power;
  }

  public void setPower(int power) {
    this.power = power;
  }

  public List<Character> getWielders() {
    return wielders;
  }

  public void setWielders(List<Character> wielders) {
    this.wielders = wielders;
  }

  @PreRemove
  private void preRemove() {
    wielders.forEach(character -> character.setWeapon(null));
  }
}
