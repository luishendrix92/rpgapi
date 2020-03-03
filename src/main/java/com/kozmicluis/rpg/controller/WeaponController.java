package com.kozmicluis.rpg.controller;

import com.kozmicluis.rpg.model.dto.WeaponResponse;
import com.kozmicluis.rpg.service.WeaponService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.util.List;

@RestController
@RequestMapping(value = "/weapon")
public class WeaponController {
  @Autowired
  private WeaponService weaponService;

  @Autowired
  private ModelMapper modelMapper;

  @GetMapping
  public List<WeaponResponse> list() {
    Type weaponList = new TypeToken<List<WeaponResponse>>() {}.getType();

    return modelMapper.map(weaponService.getWeapons(), weaponList);
  }

  @DeleteMapping(value = "/{weaponId}")
  public void remove(@PathVariable long weaponId) {
    weaponService.removeWeapon(weaponId);
  }
}
