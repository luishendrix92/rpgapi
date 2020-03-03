package com.kozmicluis.rpg.repository;

import com.kozmicluis.rpg.model.Weapon;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WeaponDAO extends CrudRepository<Weapon, Long> {
  @Override
  List<Weapon> findAll();
}
