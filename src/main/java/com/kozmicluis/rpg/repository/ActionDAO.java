package com.kozmicluis.rpg.repository;

import com.kozmicluis.rpg.model.Action;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActionDAO extends CrudRepository<Action, Long> {
  @Override
  List<Action> findAll();
}
