package com.crud.mongo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "tutorial")
public class Tutorial {
  @Id
  private String id;

  private String title;
  private String description;
  private String by_user;

  public Tutorial() {

  }

  public String getBy_user() {
	return by_user;
}

public void setBy_user(String by_user) {
	this.by_user = by_user;
}

public Tutorial(String title, String description, String by_user) {
    this.title = title;
    this.description = description;
    this.by_user = by_user;
  }

  public String getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }


  @Override
  public String toString() {
    return "Tutorial [id=" + id + ", title=" + title + ", desc=" + description + ", by_user=" + by_user + "]";
  }
}