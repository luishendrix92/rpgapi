package com.kozmicluis.rpg.service;

import com.kozmicluis.rpg.model.*;
import com.kozmicluis.rpg.model.Character;
import com.kozmicluis.rpg.repository.CharacterDAO;
import com.kozmicluis.rpg.repository.ClassDao;
import com.kozmicluis.rpg.repository.WeaponDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CharacterService {
  @Autowired
  private CharacterDAO characters;

  @Autowired
  private WeaponDAO weapons;

  @Autowired
  private ClassDao classes;

  /**
   * Obtains a list of all the RPG characters in the database.
   */
  public List<Character> getCharacters() {
    return characters.findAll();
  }

  /**
   * Creates a brand new RPG character without a weapon and a class.
   */
  public Character createCharacter(Character character) {
    String message = "Character " + "'" + character.getName() + "' was created.";

    character.getActions().add(
      new Action(ActionType.CHAR_CREATION, character, message)
    );

    return characters.save(character);
  }

  /**
   * Changes the character's class.
   */
  public Character changeClass(long characterId, Long classId) {
    Character character = characters.findById(characterId)
      .orElseThrow(() -> new RuntimeException("Character with id of " + characterId + " not found"));
    CharClass charClass = classes.findById(classId)
      .orElseThrow(() -> new RuntimeException("Weapon with id of " + classId + " not found"));
    String message = character.getCharClass() == null
      ? character.getName() + " is now a: " + charClass.getName() + "."
      : character.getName() + " switched classes: " +
        character.getCharClass().getName() +
        " -> " + charClass.getName() + ".";

    character.getActions().add(
      new Action(ActionType.CLASS_CHANGE, character, message)
    );
    character.setCharClass(charClass);

    return characters.save(character);
  }

  /**
   * Changes the character's weapon.
   */
  public Character changeWeapon(long characterId, Long weaponId) {
    Character character = characters.findById(characterId)
      .orElseThrow(() -> new RuntimeException("Character with id of " + characterId + " not found"));
    Weapon weapon = weapons.findById(weaponId)
      .orElseThrow(() -> new RuntimeException("Weapon with id of " + weaponId + " not found"));
    String message = character.getWeapon() == null
      ? character.getName() + " assigned their first weapon: " + weapon.getName() + "."
      : character.getName() + " switched weapons: " +
        character.getWeapon().getName() +
        " -> " + weapon.getName() + ".";

    character.getActions().add(
      new Action(ActionType.WEP_CHANGE, character, message)
    );
    character.setWeapon(weapon);

    return characters.save(character);
  }

  public void removeCharacter(long characterId) {
    Character character = characters.findById(characterId)
      .orElseThrow(() -> new RuntimeException("Character with id of " + characterId + " not found"));

    characters.delete(character);
  }
}
