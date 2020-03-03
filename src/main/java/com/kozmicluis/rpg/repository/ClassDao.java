package com.kozmicluis.rpg.repository;

import com.kozmicluis.rpg.model.CharClass;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassDao extends CrudRepository<CharClass, Long> {
  @Override
  List<CharClass> findAll();
}
