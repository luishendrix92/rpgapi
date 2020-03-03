package com.kozmicluis.rpg.service;

import com.kozmicluis.rpg.model.Action;
import com.kozmicluis.rpg.model.ActionType;
import com.kozmicluis.rpg.model.Character;
import com.kozmicluis.rpg.repository.ActionDAO;
import com.kozmicluis.rpg.repository.CharacterDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ActionService {
  @Autowired
  private ActionDAO actions;

  @Autowired
  private CharacterDAO characters;

  public List<Action> getActions() {
    return actions.findAll();
  }

  public Character performAttack(long attackerId, long victimId) {
    Character attacker = characters.findById(attackerId)
      .orElseThrow(() -> new RuntimeException("Attacker with id of " + attackerId + " not found"));
    Character victim = characters.findById(victimId)
      .orElseThrow(() -> new RuntimeException("Victim with id of " + victimId + " not found"));

    if (attacker.getHp() == 0) {
      throw new RuntimeException("Knocked out characters can't attack.");
    } else if (victim.getHp() == 0) {
      throw new RuntimeException("Can't attack a knocked out character.");
    }

    int damage = Optional.ofNullable(attacker.getWeapon())
      .map(weapon -> weapon.getPower() * Math.max(attacker.getLevel() / 10, 1))
      .orElse(50 * (attacker.getLevel() / 100) + 1);
    String attackMessage = attacker.getName() + " did " + damage +
      "hp of damage to " + victim.getName() + " with a " +
      (attacker.getWeapon() == null ? "hand" : attacker.getWeapon().getName()) + ".";

    victim.setHp(victim.getHp() - damage);

    if (victim.getHp() == 0) {
      String koMessage = attacker.getName() + " knocked out " + victim.getName() + ".";

      attacker.setExp(attacker.getExp() + (100 - (attacker.getLevel())));
      actions.save(new Action(ActionType.CHAR_KO, attacker, koMessage));
    }

    actions.save(new Action(ActionType.ATTACK, attacker, attackMessage));

    return characters.save(victim);
  }
}
