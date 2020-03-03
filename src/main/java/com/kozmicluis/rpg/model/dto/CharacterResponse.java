package com.kozmicluis.rpg.model.dto;

public class CharacterResponse {
  private long id;
  private ClassResponse charClass;
  private WeaponResponse weapon;
  private String name;
  private int level;
  private int exp;
  private int hp;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public ClassResponse getCharClass() {
    return charClass;
  }

  public void setCharClass(ClassResponse charClass) {
    this.charClass = charClass;
  }

  public WeaponResponse getWeapon() {
    return weapon;
  }

  public void setWeapon(WeaponResponse weapon) {
    this.weapon = weapon;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getLevel() {
    return level;
  }

  public void setLevel(int level) {
    this.level = level;
  }

  public int getExp() {
    return exp;
  }

  public void setExp(int exp) {
    this.exp = exp;
  }

  public int getHp() {
    return hp;
  }

  public void setHp(int hp) {
    this.hp = hp;
  }
}
