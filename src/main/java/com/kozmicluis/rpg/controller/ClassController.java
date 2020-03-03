package com.kozmicluis.rpg.controller;

import com.kozmicluis.rpg.model.dto.ClassResponse;
import com.kozmicluis.rpg.service.ClassService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.util.List;

@RestController
@RequestMapping(value = "/class")
public class ClassController {
  @Autowired
  private ClassService classService;

  @Autowired
  private ModelMapper modelMapper;

  @GetMapping
  public List<ClassResponse> list() {
    Type classList = new TypeToken<List<ClassResponse>>() {}.getType();

    return modelMapper.map(classService.getClasses(), classList);
  }

  @DeleteMapping(value = "/{classId}")
  public void remove(@PathVariable long classId) {
    classService.removeClass(classId);
  }
}
