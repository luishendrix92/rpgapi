package com.kozmicluis.rpg.service;

import com.kozmicluis.rpg.model.Action;
import com.kozmicluis.rpg.repository.ActionDAO;
import com.kozmicluis.rpg.repository.CharacterDAO;
import com.lowagie.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

@Service
public class PdfGenerator {
  @Autowired
  private CharacterDAO characters;

  @Autowired
  private ActionDAO actions;

  public ByteArrayOutputStream allEvents() throws DocumentException {
    return makePdf(actions.findAll());
  }

  public ByteArrayOutputStream byCharacter(long characterId) {
    return null;
  }

  private ByteArrayOutputStream makePdf(List<Action> actions) throws DocumentException {
    ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
    templateResolver.setSuffix(".html");
    templateResolver.setTemplateMode("HTML");

    TemplateEngine templateEngine = new TemplateEngine();
    templateEngine.setTemplateResolver(templateResolver);

    Context context = new Context();
    context.setVariable("date", new Date().toLocaleString());
    context.setVariable("actions", actions);

    String html = templateEngine.process("templates/report.html", context);

    ByteArrayOutputStream fileStream = new ByteArrayOutputStream();
    ITextRenderer renderer = new ITextRenderer();
    renderer.setDocumentFromString(html);
    renderer.layout();
    renderer.createPDF(fileStream, false);
    renderer.finishPDF();

    return fileStream;
  }
}
