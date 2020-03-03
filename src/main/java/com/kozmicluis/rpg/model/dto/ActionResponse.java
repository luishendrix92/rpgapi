package com.kozmicluis.rpg.model.dto;

import com.kozmicluis.rpg.model.ActionType;

import java.util.Date;

public class ActionResponse {
  private long id;
  private CharacterResponse subject;
  private Date created;
  private ActionType actionType;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public CharacterResponse getSubject() {
    return subject;
  }

  public void setSubject(CharacterResponse subject) {
    this.subject = subject;
  }

  public Date getCreated() {
    return created;
  }

  public void setCreated(Date created) {
    this.created = created;
  }

  public ActionType getActionType() {
    return actionType;
  }

  public void setActionType(ActionType actionType) {
    this.actionType = actionType;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  private String content;
}
