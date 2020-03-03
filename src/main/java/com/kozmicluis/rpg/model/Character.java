package com.kozmicluis.rpg.model;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Character")
public class Character implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "fk_class")
  private CharClass charClass;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "fk_weapon")
  private Weapon weapon;

  @OneToMany(mappedBy = "subject", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private List<Action> actions = new ArrayList<Action>();

  @Column(nullable = false)
  private String name = "John Doe";

  @Column(nullable = false)
  @Min(0)
  @Max(100)
  private int level = 0;

  @Column(nullable = false)
  @Min(0)
  private int exp = 0;

  @Column(nullable = false)
  @Min(0)
  @Max(999)
  private int hp = 10; // Computed by the class' base HP

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

  public int getLevel() {
    return level;
  }

  public void setLevel(int level) {
    this.level = Math.min(level, 100);
  }

  public int getExp() {
    return exp;
  }

  public void setExp(int exp) {
    this.exp = exp;
    setLevel(Math.floorDiv(exp, 1000));
  }

  public int getHp() {
    return hp;
  }

  public void setHp(int hp) {
    this.hp = Math.max(hp, 0);
  }

  public CharClass getCharClass() {
    return charClass;
  }

  public void setCharClass(CharClass charClass) {
    this.charClass = charClass;

    if (charClass != null) {
      // TODO: Take level into consideration for HP boost
      setHp(charClass.getBaseHp());
    }
  }

  public Weapon getWeapon() {
    return weapon;
  }

  public void setWeapon(Weapon weapon) {
    this.weapon = weapon;
  }

  public List<Action> getActions() {
    return actions;
  }

  public void setActions(List<Action> actions) {
    this.actions = actions;
  }
}
