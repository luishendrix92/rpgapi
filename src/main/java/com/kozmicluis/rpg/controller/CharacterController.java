package com.kozmicluis.rpg.controller;

import com.kozmicluis.rpg.model.Character;
import com.kozmicluis.rpg.model.dto.CharacterResponse;
import com.kozmicluis.rpg.service.CharacterService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.util.List;

@RestController
@RequestMapping(value = "/character")
public class CharacterController {
  @Autowired
  private CharacterService characterService;

  @Autowired
  private ModelMapper modelMapper;

  @GetMapping
  public List<CharacterResponse> list() {
    Type characterList = new TypeToken<List<CharacterResponse>>() {}.getType();

    return modelMapper.map(characterService.getCharacters(), characterList);
  }

  @PostMapping
  public CharacterResponse create(@RequestBody Character character) {
    return modelMapper.map(
      characterService.createCharacter(character),
      CharacterResponse.class
    );
  }

  @GetMapping(value = "/{characterId}/change")
  public CharacterResponse change(
    @PathVariable long characterId,
    @RequestParam(required = false) Long classId,
    @RequestParam(required = false) Long weaponId
  ) {
    if (classId == null && weaponId == null) {
      throw new RuntimeException("There was nothing to change!");
    }

    Character character = null;

    if (classId != null) {
      character = characterService.changeClass(characterId, classId);
    }

    if (weaponId != null) {
      character = characterService.changeWeapon(characterId, weaponId);
    }

    return modelMapper.map(character, CharacterResponse.class);
  }

  @PostMapping(value = "/{characterId}")
  public void remove(@PathVariable long characterId) {
    characterService.removeCharacter(characterId);
  }
}
