package com.tiagokihara.domain.entities;

import io.quarkus.hibernate.reactive.panache.PanacheEntity;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Cacheable
public class Fruit extends PanacheEntity {
  
  @Column(unique = true)
  public String name;
}
