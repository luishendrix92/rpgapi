package com.kozmicluis.rpg.model;

public enum ActionType {
  ATTACK("Character attacked"),
  WEP_CHANGE("Weapon change"),
  CLASS_CHANGE("Class change"),
  CHAR_CREATION("Character created"),
  CHAR_KO("Knock-out"),
  NO_ACTION("Test action");

  public final String label;

  ActionType(String label) {
    this.label = label;
  }
}
