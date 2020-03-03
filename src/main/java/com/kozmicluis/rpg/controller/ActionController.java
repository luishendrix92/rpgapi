package com.kozmicluis.rpg.controller;

import com.kozmicluis.rpg.model.dto.ActionResponse;
import com.kozmicluis.rpg.model.dto.CharacterResponse;
import com.kozmicluis.rpg.service.ActionService;
import com.kozmicluis.rpg.service.PdfGenerator;
import com.lowagie.text.DocumentException;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.lang.reflect.Type;
import java.util.List;

@RestController
@RequestMapping(value = "/action")
public class ActionController {
  @Autowired
  private ActionService actionService;

  @Autowired
  private PdfGenerator pdfGenerator;

  @Autowired
  private ModelMapper modelMapper;

  @GetMapping
  public List<ActionResponse> allActions() {
    Type actionList = new TypeToken<List<ActionResponse>>() {}.getType();

    return modelMapper.map(actionService.getActions(), actionList);
  }

  @PutMapping(value = "/attack")
  public CharacterResponse attack(
    @RequestParam(required = true) long attackerId,
    @RequestParam(required = true) long victimId
  ) {
    return modelMapper.map(
      actionService.performAttack(attackerId, victimId),
      CharacterResponse.class
    );
  }

  @GetMapping(value = "/pdf")
  public ResponseEntity<ByteArrayResource> logReportPDF() throws DocumentException {
    ByteArrayOutputStream output = pdfGenerator.allEvents();
    ByteArrayResource pdfFileBytes = new ByteArrayResource(output.toByteArray());

    return ResponseEntity.ok()
      .contentType(MediaType.APPLICATION_PDF)
      .contentLength(pdfFileBytes.contentLength())
      .body(pdfFileBytes);
  }
}
