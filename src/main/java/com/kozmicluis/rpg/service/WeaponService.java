package com.kozmicluis.rpg.service;

import com.kozmicluis.rpg.model.Weapon;
import com.kozmicluis.rpg.repository.WeaponDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WeaponService {
  @Autowired
  private WeaponDAO weapons;

  public List<Weapon> getWeapons() {
    return weapons.findAll();
  }

  public void removeWeapon(long weaponId) {
    Weapon weapon = weapons.findById(weaponId)
      .orElseThrow(() -> new RuntimeException("Weapon with id of " + weaponId + " not found"));

    weapons.delete(weapon);
  }
}
