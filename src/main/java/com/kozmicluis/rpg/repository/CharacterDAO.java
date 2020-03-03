package com.kozmicluis.rpg.repository;

import com.kozmicluis.rpg.model.Character;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CharacterDAO extends CrudRepository<Character, Long> {
  @Override
  List<Character> findAll();
}
