package com.kozmicluis.rpg.service;

import com.kozmicluis.rpg.model.CharClass;
import com.kozmicluis.rpg.repository.ClassDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassService {
  @Autowired
  private ClassDao classes;

  public List<CharClass> getClasses() {
    return classes.findAll();
  }

  public void removeClass(long classId) {
    CharClass weapon = classes.findById(classId)
      .orElseThrow(() -> new RuntimeException("Class with id of " + classId + " not found"));

    classes.delete(weapon);
  }
}
